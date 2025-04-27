package name.liuyi.mifreeformenhance.xposed.hook.systemui

import de.robv.android.xposed.XC_MethodHook
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookSystemUI
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookSystemUI::class])
object HideTopCaption : BaseHook() {

    private val mUnhooks: MutableList<XC_MethodHook.Unhook> = mutableListOf()

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)

        if (prefs.getBoolean("hide_top_caption", false)) {
            startHook()
        }
        prefs.registerOnSharedPreferenceChangeListener { prefs, key ->
            when (key) {
                null -> clean()
                "hide_top_caption" -> if (prefs.getBoolean(key, false)) startHook() else clean()
            }
        }
    }

    override fun clean() {
        super.clean()
        mUnhooks.forEach { it.unhook() }
        mUnhooks.clear()
    }

    private fun startHook() {
        mUnhooks.forEach { it.unhook() }
        mUnhooks.clear()

        ("com.android.wm.shell.multitasking.miuimultiwinswitch.miuiwindowdecor.MiuiTopDecoration".toClassOrNull()
            ?: "com.android.wm.shell.miuimultiwinswitch.miuiwindowdecor.MiuiBaseWindowDecoration".toClassOrNull())?.let {
            it("needTopCaption") alwaysReturn false
        }?.also {
            mUnhooks.add(it)
        } ?: "com.android.wm.shell.miuimultiwinswitch.miuiwindowdecor.MiuiBaseWindowDecoration".toClass().run {
            declaredMethods.filter {
                it.name == "getBooleanProperty" && it.parameterTypes.run { isNotEmpty() && get(0) == String::class.java }
            }.forEach {
                mUnhooks += it before {
                    if (args[0] == "miui.window.DOT_ENABLED") result = false
                }
            }
        }
    }
}