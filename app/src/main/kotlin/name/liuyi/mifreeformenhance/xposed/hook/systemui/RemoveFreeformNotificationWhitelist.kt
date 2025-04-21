package name.liuyi.mifreeformenhance.xposed.hook.systemui

import android.content.Context
import de.robv.android.xposed.XC_MethodHook
import name.liuyi.mifreeformenhance.common.Reflection.get
import name.liuyi.mifreeformenhance.common.Reflection.invoke
import name.liuyi.mifreeformenhance.xposed.app.HookSystemUI
import name.liuyi.mifreeformenhance.xposed.base.BaseApp
import name.liuyi.mifreeformenhance.xposed.base.BaseHook
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry

@HookEntry(scope = [HookSystemUI::class])
object RemoveFreeformNotificationWhitelist : BaseHook() {

    private val MiuiExpandableNotificationRow by lazy {
        "com.android.systemui.statusbar.notification.row.MiuiExpandableNotificationRow".toClass()
    }

    private val CloudDataManager_Companion by lazy {
        "com.miui.systemui.CloudDataManager\$Companion".toClass()
    }

    private val getMAppMiniWindowManager by lazy {
        MiuiExpandableNotificationRow("getMAppMiniWindowManager")
    }

    private val notificationSettingsManager by lazy {
        getMAppMiniWindowManager.returnType["notificationSettingsManager"]
    }

    private val mAllowNotificationSlide by lazy {
        notificationSettingsManager.type["mAllowNotificationSlide"]
    }

    private var updateMiniWindowBarUnhook: XC_MethodHook.Unhook? = null
    private var getCloudDataStringUnhook: XC_MethodHook.Unhook? = null

    private class StubList<T>(val base: List<T>) : List<T> by base {
        override fun contains(element: T): Boolean {
            return updateMiniWindowBarUnhook != null || base.contains(element)
        }
    }

    override fun onStartup(app: BaseApp) {
        super.onStartup(app)

        if (prefs.getBoolean("remove_freeform_notification_whitelist", false)) {
            updateMiniWindowBarHook()
        }
        if (prefs.getBoolean("forbid_freeform_notification_whitelist_cloud", false)) {
            getCloudDataStringHook()
        }
        prefs.registerOnSharedPreferenceChangeListener { prefs, key ->
            when (key) {
                null -> clean()
                "remove_freeform_notification_whitelist" -> {
                    if (prefs.getBoolean(key, false))
                        updateMiniWindowBarHook()
                    else updateMiniWindowBarUnhook?.unhook()
                        ?.also {
                            d("remove unhook: updateMiniWindowBarUnhook")
                            updateMiniWindowBarUnhook = null
                        }
                }

                "forbid_freeform_notification_whitelist_cloud" -> {
                    if (prefs.getBoolean(key, false))
                        getCloudDataStringHook()
                    else getCloudDataStringUnhook?.unhook()
                        ?.also {
                            d("remove unhook: getCloudDataStringUnhook")
                            getCloudDataStringUnhook = null
                        }
                }
            }
        }
    }

    private fun updateMiniWindowBarHook() {
        d("hook: updateMiniWindowBar")
        updateMiniWindowBarUnhook?.unhook()
        updateMiniWindowBarUnhook = MiuiExpandableNotificationRow("updateMiniWindowBar") before {
            d("injected StubList")
            val windowManager = getMAppMiniWindowManager(thisObject)
            val settingsManager = notificationSettingsManager[windowManager]
            val slide = mAllowNotificationSlide[settingsManager] as List<*>
            if (slide !is StubList) {
                mAllowNotificationSlide[settingsManager] = StubList(slide)
            }
        }
    }

    private fun getCloudDataStringHook() {
        d("hook: getCloudDataString")
        getCloudDataStringUnhook?.unhook()
        getCloudDataStringUnhook = CloudDataManager_Companion(
            "getCloudDataString",
            Context::class.java,
            String::class.java,
            String::class.java
        ) before {
            if (args[2] == "small_window_notification_whitelist") {
                d("forbid freeform notification whitelist from cloud")
                result = null
            }
        }
    }

    override fun clean() {
        super.clean()
        updateMiniWindowBarUnhook?.unhook()
        updateMiniWindowBarUnhook = null

        getCloudDataStringUnhook?.unhook()
        getCloudDataStringUnhook = null
    }

}