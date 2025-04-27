package name.liuyi.mifreeformenhance.xposed.hook.system

import android.app.ActivityThread
import android.content.pm.PackageManager
import name.liuyi.mifreeformenhance.BuildConfig
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookSystem
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry


@HookEntry(scope = [HookSystem::class])
object AllowPermission : BaseHook() {

    private val ModuleUid by lazy { ActivityThread.getPackageManager().getPackageUid(BuildConfig.APPLICATION_ID, 0, 0) }

    private val PermissionManagerService by lazy {
        "com.android.server.pm.permission.PermissionManagerService".toClass()
    }

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)
        PermissionManagerService.runCatching {
            getMethod("checkUidPermission", Int::class.java, String::class.java, Int::class.java)
        }.getOrElse {
            PermissionManagerService("checkUidPermission", Int::class.java, String::class.java)
        } before {
            if (args[0] == ModuleUid) {
                result = PackageManager.PERMISSION_GRANTED
            }
        }

        PermissionManagerService.runCatching {
            getMethod("checkPermission", String::class.java, String::class.java, String::class.java, Int::class.java)
        }.getOrElse {
            PermissionManagerService("checkPermission", String::class.java, String::class.java, Int::class.java)
        } before {
            if (args[0] == BuildConfig.APPLICATION_ID && args.last() == 0) {
                result = PackageManager.PERMISSION_GRANTED
            }
        }
    }
}