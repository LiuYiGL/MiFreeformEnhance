package name.liuyi.mifreeformenhance.xposed.hook.common

import android.app.Application
import android.content.Context
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.provider.RemotePreferences
import name.liuyi.mifreeformenhance.xposed.app.HookMiuiHome
import name.liuyi.mifreeformenhance.xposed.app.HookSystem
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookMiuiHome::class, HookSystem::class])
object RemotePreferenceRegister : BaseHook() {

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)
        Application::class.java("attach", Context::class.java) after {
            d("register RemotePreferences in ${app.lpparam.packageName}")
            val application = thisObject as Application
            if (prefs is RemotePreferences) (prefs as RemotePreferences).attachContentResolver(application.contentResolver)
        }
    }
}