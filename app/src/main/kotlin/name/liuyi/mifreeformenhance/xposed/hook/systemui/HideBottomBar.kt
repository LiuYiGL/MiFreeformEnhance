package name.liuyi.mifreeformenhance.xposed.hook.systemui

import android.graphics.Canvas
import de.robv.android.xposed.XC_MethodHook
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookSystemUI
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookSystemUI::class])
object HideBottomBar : BaseHook() {

    private val mUnhooks: MutableList<XC_MethodHook.Unhook> = mutableListOf()

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)

        if (prefs.getBoolean("hide_bottom_bar", false)) {
            startHook()
        }

        prefs.registerOnSharedPreferenceChangeListener { prefs, key ->
            when (key) {
                null -> clean()
                "hide_bottom_bar" -> if (prefs.getBoolean(key, false)) startHook() else clean()
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

        mUnhooks += ("com.android.wm.shell.multitasking.miuimultiwinswitch.miuiwindowdecor.MiuiBottomBarView".toClassOrNull()
            ?: "com.android.wm.shell.miuimultiwinswitch.miuiwindowdecor.MiuiBottomBarView".toClass())(
            "onDraw",
            Canvas::class.java
        ) alwaysReturn null
    }
}