package name.liuyi.mifreeformenhance.xposed.hook.system

import de.robv.android.xposed.XC_MethodHook
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookSystem
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookSystem::class])
object EdgeFreeformKeepForeground : BaseHook() {

    private val mUnhooks: MutableList<XC_MethodHook.Unhook> = mutableListOf()

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)

        if (prefs.getBoolean("edge_freeform_keep_foreground", false)) {
            startHook()
        }
        prefs.registerOnSharedPreferenceChangeListener { prefs, key ->
            when (key) {
                null -> clean()
                "edge_freeform_keep_foreground" -> if (prefs.getBoolean(key, false)) startHook() else clean()
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

        mUnhooks += "com.android.server.wm.MiuiFreeFormGestureController".toClass()(
            "needForegroundPin",
            "com.android.server.wm.MiuiFreeFormActivityStack".toClass()
        ) alwaysReturn true
    }
}