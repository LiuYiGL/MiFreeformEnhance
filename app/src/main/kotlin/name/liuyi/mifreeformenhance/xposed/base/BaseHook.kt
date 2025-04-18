package name.liuyi.mifreeformenhance.xposed.base

import android.content.SharedPreferences
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import name.liuyi.mifreeformenhance.common.XLogManager
import name.liuyi.mifreeformenhance.xposed.base.interfaces.IBaseHook
import java.lang.reflect.Member

open class BaseHook : IBaseHook, XLogManager.LogScope {

    private lateinit var _app: BaseApp

    val startupParam: IXposedHookZygoteInit.StartupParam
        get() = _app.startupParam

    val lpparam: XC_LoadPackage.LoadPackageParam
        get() = _app.lpparam

    val prefs: SharedPreferences
        get() = _app.prefs

    override fun onStartup(app: BaseApp) {
        d("onStartup")
        _app = app
    }

    override fun clean() {
        d("clean")
    }

    protected fun String.toClass(): Class<*> = Class.forName(this, false, _app.lpparam.classLoader)

    protected fun String.toClassOrNull(): Class<*>? = runCatching {
        Class.forName(this, false, _app.lpparam.classLoader)
    }.onFailure { e ->
        w(e)
    }.getOrNull()

    protected infix fun Member.before(callback: XC_MethodHook.MethodHookParam.() -> Unit): XC_MethodHook.Unhook {
        return XposedBridge.hookMethod(this, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                runCatching {
                    callback(param)
                }.onFailure {
                    w("hook member throw an exception before ${this@before}", it)
                }
            }
        }).also {
            d("hook member before ${this@before} success")
        }
    }

    protected infix fun Member.after(callback: XC_MethodHook.MethodHookParam.() -> Unit): XC_MethodHook.Unhook {
        return XposedBridge.hookMethod(this, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                runCatching {
                    callback(param)
                }.onFailure {
                    w("hook member throw an exception after ${this@after}", it)
                }
            }
        }).also {
            d("hook member after ${this@after} success")
        }
    }

    protected infix fun Member.alwaysReturn(returnValue: Any?): XC_MethodHook.Unhook {
        return XposedBridge.hookMethod(this, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                param.result = returnValue
            }
        }).also {
            d("hook member alwaysReturn ${this@alwaysReturn} by $returnValue success")
        }
    }
}