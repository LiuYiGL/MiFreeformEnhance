package name.liuyi.mifreeformenhance.xposed.base.interfaces

import name.liuyi.mifreeformenhance.xposed.base.BaseApp

interface IBaseHook {
    fun onStartup(app: BaseApp)

    fun clean() {}
}