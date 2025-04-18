package name.liuyi.mifreeformenhance.xposed

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage
import name.liuyi.mifreeformenhance.common.XLogManager
import name.liuyi.mifreeformenhance.xposed.app.HookMiuiHome
import name.liuyi.mifreeformenhance.xposed.app.HookOther
import name.liuyi.mifreeformenhance.xposed.app.HookSystem
import name.liuyi.mifreeformenhance.xposed.app.HookSystemUI
import name.liuyi.mifreeformenhance.xposed.utils.print

class XposedInit : IXposedHookZygoteInit, IXposedHookLoadPackage, XLogManager.LogScope {

    private lateinit var _startupParam: IXposedHookZygoteInit.StartupParam
    private lateinit var _lpparam: XC_LoadPackage.LoadPackageParam

    init {
        XLogManager.init()
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        i(startupParam.print())
        _startupParam = startupParam
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        i(lpparam.print())
        _lpparam = lpparam
        when (lpparam.packageName) {
            "android" -> HookSystem.attachParam(_startupParam, _lpparam)
            "com.android.systemui" -> HookSystemUI.attachParam(_startupParam, _lpparam)
            "com.miui.home" -> HookMiuiHome.attachParam(_startupParam, _lpparam)
            else -> HookOther.attachParam(_startupParam, _lpparam)
        }
    }
}