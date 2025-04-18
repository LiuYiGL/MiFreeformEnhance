package name.liuyi.mifreeformenhance.xposed.hook.miuihome

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.ActivityThread
import android.content.ComponentName
import android.content.Intent
import android.os.UserHandle
import android.util.MiuiMultiWindowUtils
import android.view.View
import de.robv.android.xposed.XC_MethodHook
import miui.app.MiuiFreeFormManager
import miui.os.Build
import name.liuyi.mifreeformenhance.common.Reflection.get
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookMiuiHome
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookMiuiHome::class])
@SuppressLint("UseCompatLoadingForDrawables")
object FreeformShortcutMenu : BaseHook() {

    private val Res get() = ActivityThread.currentApplication().resources
    private val FreeformDrawableId
        get() = Res.getIdentifier(
            if (Build.IS_TABLET) "shortcut_menu_small_window_icon" else "ic_task_small_window",
            "drawable",
            lpparam.packageName
        )
    private val MultiWindowDrawableId get() = Res.getIdentifier("ic_task_add_pair", "drawable", lpparam.packageName)
    private val FreeformDrawable get() = ActivityThread.currentApplication().getDrawable(FreeformDrawableId)
    private val MultiWindowIconDrawable get() = ActivityThread.currentApplication().getDrawable(MultiWindowDrawableId)

    private val AppDetailsShortcutMenuItemClass by lazy {
        "com.miui.home.launcher.shortcuts.SystemShortcutMenuItem\$AppDetailsShortcutMenuItem".toClass()
    }
    private val SmallWindowShortcutMenuItemClass by lazy {
        "com.miui.home.launcher.shortcuts.SystemShortcutMenuItem\$SmallWindowShortcutMenuItem".toClassOrNull()
    }
    private val ShortcutMenuItemClass by lazy { "com.miui.home.launcher.shortcuts.ShortcutMenuItem".toClass() }
    private val SystemShortcutMenuItemClass by lazy { "com.miui.home.launcher.shortcuts.SystemShortcutMenuItem".toClass() }

    private val setForceLaunchNewTask by lazy { ActivityOptions::class.java("setForceLaunchNewTask") }
    private val getComponentName by lazy { ShortcutMenuItemClass("getComponentName") }
    private val getUserHandle by lazy { ShortcutMenuItemClass("getUserHandle") }
    private val createAllSystemShortcutMenuItems by lazy { SystemShortcutMenuItemClass("createAllSystemShortcutMenuItems") }

    private val mShortTitle by lazy { ShortcutMenuItemClass["mShortTitle"] }
    private val sAllSystemShortcutMenuItems by lazy { SystemShortcutMenuItemClass["sAllSystemShortcutMenuItems"] }

    private object ShortcutSign : CharSequence by tag

    private val FreeformMenuItem by lazy {
        AppDetailsShortcutMenuItemClass.newInstance().apply { mShortTitle[this] = ShortcutSign }
    }

    private val mUnhooks: MutableList<XC_MethodHook.Unhook> = mutableListOf()

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)

        if (prefs.getBoolean("home_freeform_shortcut", false)) {
            startHook()
        }
        prefs.registerOnSharedPreferenceChangeListener { prefs, key ->
            if (key == null || !prefs.getBoolean(key, false)) {
                clean()
            } else {
                startHook()
            }
            createAllSystemShortcutMenuItems(null)
        }
    }

    override fun clean() {
        super.clean()
        mUnhooks.forEach { it.unhook() }
        mUnhooks.clear()
    }

    private fun startHook() {
        d("Start hook")

        mUnhooks.forEach { it.unhook() }
        mUnhooks.clear()

        mUnhooks += AppDetailsShortcutMenuItemClass("getOnClickListener") before {
            if (mShortTitle[thisObject] !is ShortcutSign) return@before
            result = View.OnClickListener { view ->
                val context = view.context
                val componentName = getComponentName(thisObject) as ComponentName
                val intent = Intent.makeMainActivity(componentName).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val options = MiuiMultiWindowUtils.getActivityOptions(context, componentName.packageName, true)
                if (options != null) {
                    if (isStackExists(thisObject)) {
                        setForceLaunchNewTask(options)
                    }
                    context.startActivity(intent, options.toBundle())
                } else {
                    w("Component name: $componentName unsupported freeform mode")
                    context.startActivity(intent)
                }
            }
        }

        mUnhooks += AppDetailsShortcutMenuItemClass("isValid") after {
            if (mShortTitle[thisObject] !is ShortcutSign) return@after
            val componentName = getComponentName(thisObject) as ComponentName
            result = MiuiMultiWindowUtils.packageSupportFreeform(
                ActivityThread.currentApplication(),
                componentName.packageName
            )
        }

        mUnhooks += ShortcutMenuItemClass("getShortTitle") before {
            if (mShortTitle[thisObject] !is ShortcutSign) return@before
            result = if (isStackExists(thisObject)) "多开" else "小窗"
        }

        mUnhooks += ShortcutMenuItemClass("getIconDrawable") before {
            if (mShortTitle[thisObject] !is ShortcutSign) return@before
            result = if (isStackExists(thisObject)) MultiWindowIconDrawable else FreeformDrawable
        }

        mUnhooks += createAllSystemShortcutMenuItems after {
            val itemsBackup = sAllSystemShortcutMenuItems[null] as List<Any?>
            val items = itemsBackup.toMutableList()
            val indexOfFirst = if (Build.IS_TABLET) items.indexOfFirst {
                it?.javaClass == SmallWindowShortcutMenuItemClass
            } else -1

            items.add(indexOfFirst + 1, FreeformMenuItem)
            sAllSystemShortcutMenuItems[null] = items
        }
    }

    private fun isStackExists(item: Any): Boolean {
        val componentName = getComponentName(item) as ComponentName
        val userHandle = getUserHandle(item) as UserHandle
        val infos = MiuiFreeFormManager.getAllFreeFormStackInfosOnDisplay(-1)
        return infos.any { it.packageName == componentName.packageName && it.userId == userHandle.identifier }
    }
}