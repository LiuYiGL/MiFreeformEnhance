package name.liuyi.mifreeformenhance.xposed.hook.systemui

import android.graphics.Canvas
import de.robv.android.xposed.XC_MethodHook
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookSystemUI
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookSystemUI::class])
object HideTopDots : BaseHook() {

    private val mUnhooks: MutableList<XC_MethodHook.Unhook> = mutableListOf()

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)

        if (prefs.getBoolean("hide_top_dots", false)) {
            startHook()
        }
        prefs.registerOnSharedPreferenceChangeListener { prefs, key ->
            when (key) {
                null -> clean()
                "hide_top_dots" -> if (prefs.getBoolean(key, false)) startHook() else clean()
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

        mUnhooks += ("com.android.wm.shell.multitasking.miuimultiwinswitch.miuiwindowdecor.MiuiDotView".toClassOrNull()
            ?: "com.android.wm.shell.miuimultiwinswitch.miuiwindowdecor.MiuiDotView".toClass())(
            "onDraw",
            Canvas::class.java
        ) alwaysReturn null
    }
}