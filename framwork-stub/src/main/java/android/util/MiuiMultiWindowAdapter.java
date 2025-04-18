package android.util;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.Application;
import android.app.WindowConfiguration;
import android.app.usage.UsageStats;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.SensorEvent;
import android.os.Build;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.SurfaceControl;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.android.internal.policy.DecorView;
import com.android.internal.policy.PhoneWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MiuiMultiWindowAdapter {
    public static List<String> ABNORMAL_FREEFORM_BLACK_LIST = null;
    public static List<String> ABNORMAL_FREEFORM_WHITE_LIST = null;
    public static final String ABNORMAL_PAD_SMALL_WINDOW_BLACKLIST = "abnormal_pad_small_window_blacklist";
    public static final String ABNORMAL_PAD_SMALL_WINDOW_WHITELIST = "abnormal_pad_small_window_whitelist";
    public static final String ABNORMAL_SMALL_WINDOW_BLACKLIST = "abnormal_small_window_blacklist";
    public static final String ABNORMAL_SMALL_WINDOW_WHITELIST = "abnormal_small_window_whitelist";
    private static List<String> ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS = null;
    public static final String ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APP_LIST = "additional_freeform_aspect_ratio_1_packages";
    private static List<String> ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS = null;
    public static final String ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APP_LIST = "additional_freeform_aspect_ratio_2_packages";
    public static final String ALIPAY_LAUNCHER_ACTIVITY_ALIAS = "com.eg.android.AlipayGphone/com.alipay.mobile.quinox.LauncherActivity.alias";
    public static final String ALIPAY_NEBULA_MAIN_ACTIVITY = "com.eg.android.AlipayGphone/com.alipay.mobile.nebulax.integration.mpaas.activity.NebulaActivity$Main";
    public static final String ALIPAY_SCHEME_LAUNCHER_ACTIVITY = "com.eg.android.AlipayGphone/com.alipay.mobile.quinox.SchemeLauncherActivity";
    public static final String ALIPAY_SCHEME_LAUNCHER_ACTIVITY_CLASS_NAME = "com.alipay.mobile.quinox.SchemeLauncherActivity";
    public static final String ALIPAY_XRIVER_ACTIVITY = "com.eg.android.AlipayGphone/com.alipay.mobile.nebulax.xriver.activity.XRiverActivity";
    public static List<String> APPLICATION_LOCK_ACTIVITY = null;
    public static final String APPLICATION_LOCK_NAME = "com.miui.securitycenter/com.miui.applicationlock.ConfirmAccessControl";
    public static List<String> AUDIO_FOREGROUND_PIN_APP_LIST = null;
    public static final String CLASS_NAME_QIYI_LITE = "com.qiyi.video.lite/com.qiyi.video.lite.videoplayer.activity.PlayerV2Activity";
    public static final String CLASS_NAME_QQLIVE = "com.tencent.qqlive/com.tencent.qqlive.ona.activity.VideoDetailActivity";
    public static final String CLASS_NAME_QQ_HB = "com.tencent.mobileqq.qwallet.hb.send.impl.SendHbActivity";
    public static final String CLASS_NAME_QQ_LOGIN = "com.tencent.mobileqq.activity.LoginActivity";
    public static final String CLASS_NAME_YOUKU_DETAILACTIVITY = "com.youku.ui.activity.DetailActivity";
    private static final int COLOR_BORDER_IMAGE_BLACK_FOCUS = 872415231;
    private static final int COLOR_BORDER_IMAGE_BLACK_LOSE_FOCUS = 872415231;
    private static final int COLOR_BORDER_IMAGE_LIGHT_FOCUS = 503316480;
    private static final int COLOR_BORDER_IMAGE_LIGHT_LOSE_FOCUS = 167772160;
    private static final int COLOR_DECOR_CAPTION_VIEW_TRANSLUCENT = 0;
    private static final int COLOR_TRANSLUCENT_MASK = -16777216;
    public static List<String> CVW_UNSUPPORTED_FREEFORM_WHITE_LIST = null;
    private static boolean DEBUG_DISABLE_REVERT_LOCATION_FOR_FREEFORM = false;
    public static List<String> DESKTOP_FREEFORM_WHITE_LIST = null;
    public static final String DISABLE_SPLASH_SCREEN_IN_FREEFORM_TASK_SWITCH = "com.tencent.qqlive/com.tencent.tauth.AuthActivity";
    private static final String DISPLAY_FRAME_TENCENT_SNSCOMMENTDETAILUI = "com.tencent.mm.plugin.sns.ui.SnsCommentDetailUI";
    private static final String DISPLAY_FRAME_TENCENT_SNSTIMELINEUI = "com.tencent.mm.plugin.sns.ui.SnsTimeLineUI";
    public static final String DKT_FREEFORM_WHITE_LIST = "dkt_freeform_white_list";
    public static boolean ENABLE_FOREGROUND_PIN = false;
    public static final String Enable_DEBUG_MODE_FOR_ABNORMAL_FREEFORM = "enable_debug_mode_for_abnormal_freeform";
    public static List<String> FORCE_LANDSCAPE_APPLICATION = null;
    public static List<String> FOREGROUND_PIN_APP_BLACK_LIST = null;
    public static List<String> FOREGROUND_PIN_APP_WHITE_LIST = null;
    public static List<String> FREEFORM_BLACK_LIST = null;
    public static List<String> FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST = null;
    public static List<String> FREEFORM_RESIZEABLE_WHITE_LIST = null;
    public static List<String> GET_FIXED_ROTATION_APP_LIST = null;
    public static List<String> GET_ROTATION_FROM_DISPLAY_APP = null;
    public static final String HANJU_TENCENT_ASSIST_ACTIVITY = "com.babycloud.hanju/com.tencent.connect.common.AssistActivity";
    public static final String HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST = "hide_self_if_new_freeform_task_white_list_activity";
    public static List<String> HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY = null;
    public static List<String> LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM = null;
    public static List<String> LIST_ABOUT_LOCK_MODE_ACTIVITY = null;
    public static List<String> LIST_ABOUT_NEED_RELUNCH = null;
    public static List<String> LIST_ABOUT_NO_NEED_IN_FREEFORM = null;
    public static List<String> LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO = null;
    public static List<String> LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM = null;
    public static List<String> LIST_DISABLE_OVERLAY_WITH_APP_CONTENT = null;
    public static List<String> LIST_LAUNCH_IN_TASK = null;
    private static List<String> LIST_REVERT_LOCATION_IN_FREEFORM = null;
    public static List<String> LIST_TOP_GAME = null;
    public static List<String> LIST_TOP_VIDEO = null;
    public static List<String> NOT_AVOID_LAUNCH_OTHER_FREEFORM_LIST = null;
    public static final String PACKAGE_NAME_QQ = "com.tencent.mobileqq";
    public static final String PAD_SMALL_WINDOW_BLACK_LIST = "pad_small_window_blacklist";
    public static final String PAYMENT_TRANSLUCENTACTIVITY = "com.xiaomi.payment/com.mipay.common.ui.TranslucentActivity";
    public static final String QQ_AGENT_ACTIVITY = "com.tencent.mobileqq/com.tencent.open.agent.AgentActivity";
    public static final String QQ_JUMP_ACTIVITY = "com.tencent.mobileqq/.activity.JumpActivity";
    public static final String QQ_LOGIN_ACTIVITY = "com.tencent.mobileqq/.activity.LoginActivity";
    public static final String QQ_WALLET_TOOL_FRAGMENT_ACTIVITY = "com.tencent.mobileqq/cooperation.qwallet.plugin.QWalletToolFragmentActivity";
    public static final String QQ_ZONE_SHARE_ACTIVITY = "com.tencent.mobileqq/cooperation.qzone.share.QZoneShareActivity";
    public static List<String> SENSOR_DISABLE_LIST = null;
    public static List<String> SENSOR_DISABLE_WHITE_LIST = null;
    public static final String SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST = "show_hidden_task_if_finished_white_list_activity";
    public static List<String> SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY = null;
    public static final String SMALL_WINDOW_ABOUT_IGNORE_REQUEST_ORIENTATION_LIST = "list_about_ignore_request_orientation_in_freeform";
    public static final String SMALL_WINDOW_APPLICATION_LOCK_BLACK_LIST = "small_window_application_lock_blacklist";
    public static final String SMALL_WINDOW_AUDIO_FOREGROUND_PIN_APP_LIST = "audio_foreground_pin_app_list";
    public static final String SMALL_WINDOW_BLACK_LIST = "small_window_blacklist";
    public static final String SMALL_WINDOW_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM = "list_can_start_activity_from_fullscreen_to_freefrom";
    public static final String SMALL_WINDOW_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST = "freeform_caption_insets_height_to_zero_list";
    public static final String SMALL_WINDOW_CVW_UNSUPPORTED_WHITE_LIST = "small_window_cvw_unsupported_whitelist";
    public static final String SMALL_WINDOW_DISABLE_OVERLAY_WITH_APP_CONTENT_LIST = "list_disable_overlay_with_app_content";
    public static final String SMALL_WINDOW_ENABLE_FOREGROUND_PIN = "enable_foreground_pin";
    public static final String SMALL_WINDOW_FORCE_LANDSCAPE_APPLICATION_LIST = "force_landscape_application";
    public static final String SMALL_WINDOW_FOREGROUND_PIN_APP_BLACK_LIST = "foreground_pin_app_black_list";
    public static final String SMALL_WINDOW_FOREGROUND_PIN_APP_WHITE_LIST = "foreground_pin_app_white_list";
    public static final String SMALL_WINDOW_FREEFORM_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP = "list_about_dkt_mode_launch_freeform_ignore_translucent_app";
    public static final String SMALL_WINDOW_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP = "list_about_dkt_mode_launch_fullscreen_app";
    public static final String SMALL_WINDOW_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_NOT_HIDE_OTHER_APP = "list_about_dkt_mode_launch_fullscreen_not_hide_other_app";
    public static final String SMALL_WINDOW_FREEFORM_RECOMMEND_MAP_APPLICATION = "list_about_freeform_recommend_map_application";
    public static final String SMALL_WINDOW_FREEFORM_RECOMMEND_WAITING_APPLICATION = "list_about_freeform_recommend_waiting_application";
    public static final String SMALL_WINDOW_GET_FIXED_ROTATION_APP_LIST = "fixed_rotation_app_list";
    public static final String SMALL_WINDOW_GET_ROTATION_FROM_DISPLAY_APP_LIST = "rotation_from_display_app_list";
    public static final String SMALL_WINDOW_LAUNCH_IN_TASK_LIST = "list_about_launch_in_task";
    public static final String SMALL_WINDOW_LOCK_MODE_ACTIVITY_LIST = "list_about_lock_mode_activity";
    public static final String SMALL_WINDOW_MODULE_NAME = "MiuiFreeform";
    public static final String SMALL_WINDOW_NEED_RELUNCH_LIST = "list_about_need_relunch";
    public static final String SMALL_WINDOW_RESIZE_WHITE_LIST = "small_window_resizeable_whitelist";
    public static final String SMALL_WINDOW_SENSOR_DISABLE_LIST = "sensor_disable_list";
    public static final String SMALL_WINDOW_SENSOR_DISABLE_WHITE_LIST = "sensor_disable_whitelist";
    public static final String SMALL_WINDOW_START_FROM_FREEFORM_BLACK_LIST = "start_from_freeform_black_list_activity";
    public static final String SMALL_WINDOW_SUPPORT_CVW_LEVEL_FULL = "list_about_support_cvw_level_full";
    public static final String SMALL_WINDOW_SUPPORT_CVW_LEVEL_HORIZONTAL = "list_about_support_cvw_level_horizontal";
    public static final String SMALL_WINDOW_SUPPORT_CVW_LEVEL_VERTICAL = "list_about_support_cvw_level_vertical";
    public static final String SMALL_WINDOW_TOP_GAME_LIST = "top_game_list";
    public static final String SMALL_WINDOW_TOP_VIDEO_LIST = "top_video_list";
    public static final String SMALL_WINDOW_UNSUPPORT_CVW_LEVEL_FULL = "list_about_unsupport_cvw_level_full";
    public static final String SMALL_WINDOW_USE_DEFAULT_CAMERA_PIPELINE_APP_LIST = "use_default_camera_pipeline_app_list";
    public static final String SMALL_WINDOW_VIDEO_WHITE_LIST = "small_window_video_whitelist";
    public static List<String> START_FROM_FREEFORM_BLACK_LIST_ACTIVITY = null;
    public static List<String> SUPPORT_FREEFORM_MULTI_TASK_LIST = null;
    private static final String TAG = "MiuiMultiWindowAdapter";
    public static final String URI_NEED_TO_OBSERVE = "content://com.android.settings.cloud.CloudSettings/cloud_all_data/notify";
    public static List<String> USE_DEFAULT_CAMERA_PIPELINE_APP = null;
    public static final String WEIBO_COMPOSER_DISPATCH_ACTIVITY = "com.sina.weibo/.composerinde.ComposerDispatchActivity";
    public static final String WPS_PRE_START_ACTIVITY = "cn.wps.moffice_eng/cn.wps.moffice.documentmanager.PreStartActivity";
    public static final String WPS_START_PUBLIC_ACTIVITY = "cn.wps.moffice_eng/cn.wps.moffice.main.StartPublicActivity";
    public static final String WPS_SUB_ENTRY_ACTIVITY = "cn.wps.moffice_eng/cn.wps.moffice.main.local.SubEntryActivity";
    public static final String YOUKU_SHARE_TRANS_ACTIVITY = "com.youku.phone/com.sina.weibo.sdk.share.ShareTransActivity";
    private static final Object lockForEnableAbnormalFreeFormDebug;
    private static final List<String> sEmptyList;
    public static volatile Integer sEnableAbnormalFreeFormDebug;
    public static volatile Boolean sEnableBlackListAppFreeFormDebug;
    private static Map<String, Long> sFreeformPackageNotSupport;
    private static final boolean IS_DEBUG_BUILD = Build.TYPE.equals("userdebug");
    private static ArrayMap<String, Integer> MAP_ABOUT_ADJUST_WINDOW_VISIBLE_DISPLAY_FRAME = new ArrayMap<>();
    private static final ArgbEvaluator mTopFocusColorInterpolator = new ArgbEvaluator();
    private static boolean mSensorDisableListUpdated = false;
    private static ArrayList<String> sSmallWindowWhiteList = new ArrayList<>();
    private static ArrayList<String> sSmallWindowBlackList = new ArrayList<>();
    public static List<String> LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL = new ArrayList(Arrays.asList());
    public static List<String> LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL = new ArrayList(Arrays.asList());

    static {
        ArrayList arrayList = new ArrayList(Arrays.asList());
        LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO = arrayList;
        LIST_ABOUT_NO_NEED_IN_FREEFORM = arrayList;
        LIST_ABOUT_NEED_RELUNCH = new ArrayList(Arrays.asList());
        START_FROM_FREEFORM_BLACK_LIST_ACTIVITY = new ArrayList(Arrays.asList());
        HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY = new ArrayList(Arrays.asList());
        SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY = new ArrayList(Arrays.asList());
        FREEFORM_BLACK_LIST = miui.os.Build.IS_TABLET ? new ArrayList(Arrays.asList()) : new ArrayList(Arrays.asList());
        ABNORMAL_FREEFORM_BLACK_LIST = miui.os.Build.IS_TABLET ? new ArrayList() : new ArrayList();
        ABNORMAL_FREEFORM_WHITE_LIST = miui.os.Build.IS_TABLET ? new ArrayList() : new ArrayList();
        FREEFORM_RESIZEABLE_WHITE_LIST = new ArrayList(Arrays.asList());
        CVW_UNSUPPORTED_FREEFORM_WHITE_LIST = new ArrayList(Arrays.asList());
        DESKTOP_FREEFORM_WHITE_LIST = new ArrayList(Arrays.asList());
        APPLICATION_LOCK_ACTIVITY = new ArrayList(Arrays.asList());
        FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST = new ArrayList(Arrays.asList());
        FORCE_LANDSCAPE_APPLICATION = new ArrayList(Arrays.asList());
        LIST_TOP_GAME = new ArrayList(Arrays.asList());
        LIST_TOP_VIDEO = new ArrayList(Arrays.asList());
        ENABLE_FOREGROUND_PIN = false;
        AUDIO_FOREGROUND_PIN_APP_LIST = new ArrayList(Arrays.asList());
        FOREGROUND_PIN_APP_WHITE_LIST = new ArrayList(Arrays.asList());
        FOREGROUND_PIN_APP_BLACK_LIST = new ArrayList(Arrays.asList());
        GET_FIXED_ROTATION_APP_LIST = new ArrayList(Arrays.asList());
        USE_DEFAULT_CAMERA_PIPELINE_APP = new ArrayList(Arrays.asList());
        GET_ROTATION_FROM_DISPLAY_APP = new ArrayList(Arrays.asList());
        SENSOR_DISABLE_WHITE_LIST = new ArrayList(Arrays.asList());
        SENSOR_DISABLE_LIST = new ArrayList(Arrays.asList());
        LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM = new ArrayList(Arrays.asList());
        LIST_DISABLE_OVERLAY_WITH_APP_CONTENT = new ArrayList(Arrays.asList());
        MAP_ABOUT_ADJUST_WINDOW_VISIBLE_DISPLAY_FRAME.put(DISPLAY_FRAME_TENCENT_SNSTIMELINEUI, 30);
        MAP_ABOUT_ADJUST_WINDOW_VISIBLE_DISPLAY_FRAME.put(DISPLAY_FRAME_TENCENT_SNSCOMMENTDETAILUI, 30);
        LIST_LAUNCH_IN_TASK = new ArrayList(Arrays.asList());
        LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM = new ArrayList(Arrays.asList());
        SUPPORT_FREEFORM_MULTI_TASK_LIST = new ArrayList(Arrays.asList());
        ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS = new ArrayList(Arrays.asList());
        ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS = new ArrayList(Arrays.asList());
        NOT_AVOID_LAUNCH_OTHER_FREEFORM_LIST = new ArrayList(Arrays.asList());
        LIST_ABOUT_LOCK_MODE_ACTIVITY = new ArrayList(Arrays.asList());
        LIST_REVERT_LOCATION_IN_FREEFORM = new ArrayList(Arrays.asList());
        DEBUG_DISABLE_REVERT_LOCATION_FOR_FREEFORM = SystemProperties.getInt(MiuiMultiWindowUtils.DISABLE_REVERT_LOCATION_FOR_FREEFORM, 0) == 1;
        sEnableBlackListAppFreeFormDebug = null;
        sEnableAbnormalFreeFormDebug = null;
        lockForEnableAbnormalFreeFormDebug = new Object();
        sEmptyList = new ArrayList();
        sFreeformPackageNotSupport = new HashMap();
    }

    public static boolean isSupportFreeFormMultiTask(String packageName) {
        List<String> list;
        if (packageName == null || (list = SUPPORT_FREEFORM_MULTI_TASK_LIST) == null) {
            return false;
        }
        return list.contains(packageName);
    }

    public static void setFreeformRecommendMapApplicationList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION) {
                LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION.clear();
                LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformRecommendMapApplicationList::list = " + list);
    }

    public static List<String> getFreeformRecommendMapApplicationList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformRecommendMapApplicationList::LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION = " + LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION);
        return LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION;
    }

    public static List<String> getFreeformRecommendMapApplicationListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FREEFORM_RECOMMEND_MAP_APPLICATION, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformRecommendMapApplicationListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_FREEFORM_RECOMMEND_MAP_APPLICATION);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformRecommendMapApplicationListFromCloud::launchInTaskList = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformRecommendMapApplicationListFromCloud :", e2);
        }
        return list;
    }

    public static void setFreeformRecommendWaitingApplicationList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION) {
                LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION.clear();
                LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformRecommendWaitingApplicationList::list = " + list);
    }

    public static List<String> getFreeformRecommendWaitingApplicationList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformRecommendWaitingApplicationList::LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION = " + LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION);
        return LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION;
    }

    public static List<String> getFreeformRecommendWaitingApplicationListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FREEFORM_RECOMMEND_WAITING_APPLICATION, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformRecommendWaitingApplicationListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_FREEFORM_RECOMMEND_WAITING_APPLICATION);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformRecommendWaitingApplicationListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformRecommendWaitingApplicationListFromCloud :", e2);
        }
        return list;
    }

    public static void setSupportCvwLevelFullList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL) {
                LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL.clear();
                LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setSupportCvwLevelFullList::list = " + list);
    }

    public static List<String> getSupportCvwLevelFullList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelFullList::LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL = " + LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL);
        return LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL;
    }

    public static List<String> getSupportCvwLevelFullListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_SUPPORT_CVW_LEVEL_FULL, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelFullListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_SUPPORT_CVW_LEVEL_FULL);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelFullListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getSupportCvwLevelFullListFromCloud :", e2);
        }
        return list;
    }

    public static void setSupportCvwLevelVerticalList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL) {
                LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL.clear();
                LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setSupportCvwLevelVerticalList::list = " + list);
    }

    public static List<String> getSupportCvwLevelVerticalList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelVerticalList::LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL = " + LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL);
        return LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL;
    }

    public static List<String> getSupportCvwLevelVerticalListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_SUPPORT_CVW_LEVEL_VERTICAL, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelVerticalListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_SUPPORT_CVW_LEVEL_VERTICAL);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelVerticalListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getSupportCvwLevelVerticalListFromCloud :", e2);
        }
        return list;
    }

    public static void setSupportCvwLevelHorizontalList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL) {
                LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL.clear();
                LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setSupportCvwLevelHorizontalList::list = " + list);
    }

    public static List<String> getSupportCvwLevelHorizontalList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelHorizontalList::LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL = " + LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL);
        return LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL;
    }

    public static List<String> getSupportCvwLevelHorizontalListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_SUPPORT_CVW_LEVEL_HORIZONTAL, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::SMALL_WINDOW_SUPPORT_CVW_LEVEL_HORIZONTAL::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_SUPPORT_CVW_LEVEL_HORIZONTAL);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getSupportCvwLevelHorizontalListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getSupportCvwLevelHorizontalListFromCloud :", e2);
        }
        return list;
    }

    public static void setUnsupportCvwLevelFullList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL) {
                LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL.clear();
                LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setUnsupportCvwLevelFullList::list = " + list);
    }

    public static List<String> getUnsupportCvwLevelFullList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getUnsupportCvwLevelFullList::LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL = " + LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL);
        return LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL;
    }

    public static List<String> getUnsupportCvwLevelFullListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_UNSUPPORT_CVW_LEVEL_FULL, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::SMALL_WINDOW_UNSUPPORT_CVW_LEVEL_FULL::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_UNSUPPORT_CVW_LEVEL_FULL);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getUnsupportCvwLevelFullListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getUnsupportCvwLevelFullListFromCloud :", e2);
        }
        return list;
    }

    public static void setAboutLockModeActivityList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_LOCK_MODE_ACTIVITY) {
                LIST_ABOUT_LOCK_MODE_ACTIVITY.clear();
                LIST_ABOUT_LOCK_MODE_ACTIVITY.addAll(list);
            }
        }
    }

    public static List<String> getAboutLockModeActivityList() {
        return LIST_ABOUT_LOCK_MODE_ACTIVITY;
    }

    public static List<String> getAboutLockModeActivityListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_LOCK_MODE_ACTIVITY_LIST, null);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_LOCK_MODE_ACTIVITY);
//            }
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getAboutLockModeActivityListFromCloud :", e2);
        }
        return list;
    }

    public static void setDesktopModeLaunchFreeformIgnoreTranslucentAppList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP) {
                LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP.clear();
                LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setDesktopModeLaunchFreeformIgnoreTranslucentAppList::list = " + list);
    }

    public static List<String> getDesktopModeLaunchFreeformIgnoreTranslucentAppList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFreeformIgnoreTranslucentAppList::LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP = " + LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP);
        return LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP;
    }

    public static List<String> getDesktopModeLaunchFreeformIgnoreTranslucentAppListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FREEFORM_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFreeformIgnoreTranslucentAppListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_DESKTOP_MODE_LAUNCH_FREEFORM_IGNORE_TRANSLUCENT_APP);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFreeformIgnoreTranslucentAppListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getDesktopModeLaunchFreeformIgnoreTranslucentAppListFromCloud :", e2);
        }
        return list;
    }

    public static void setDesktopModeLaunchFullscreenAppList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP) {
                LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP.clear();
                LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setDesktopModeLaunchFullscreenAppList::list = " + list);
    }

    public static List<String> getDesktopModeLaunchFullscreenAppList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenAppList::LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP = " + LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP);
        return LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP;
    }

    public static List<String> getDesktopModeLaunchFullscreenAppListInSystem() {
//        LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP = ActivityManager.getDesktopModeLaunchFullscreenAppList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenAppListInSystem::LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP = " + LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP);
        return LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP;
    }

    public static List<String> getDesktopModeLaunchFullscreenAppListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenAppListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_APP);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenAppListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getDesktopModeLaunchFullscreenAppListFromCloud :", e2);
        }
        return list;
    }

    public static void setDesktopModeLaunchFullscreenNotHideOtherAppList(List<String> list) {
        if (list != null) {
            synchronized (LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS) {
                LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS.clear();
                LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setDesktopModeLaunchFullscreenNotHideOtherAppList::list = " + list);
    }

    public static List<String> getDesktopModeLaunchFullscreenNotHideOtherAppList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenNotHideOtherAppList::LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS = " + LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS);
        return LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS;
    }

    public static List<String> getDesktopModeLaunchFullscreenNotHideOtherAppListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FREEFORM_DESKTOP_MODE_LAUNCH_FULLSCREEN_NOT_HIDE_OTHER_APP, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenNotHideOtherAppListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_ABOUT_FREEFORM_DESKTOP_MODE_LAUNCHER_FULLSCREEN_APP_NOT_HIDE_OTHERS);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopModeLaunchFullscreenNotHideOtherAppListFromCloud::list = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getDesktopModeLaunchFullscreenNotHideOtherAppListFromCloud :", e2);
        }
        return list;
    }

    public static void setCanStartActivityFromFullscreenToFreefromList(List<String> list) {
        if (list != null) {
            synchronized (LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM) {
                LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM.clear();
                LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setCanStartActivityFromFullscreenToFreefromList::list = " + list);
    }

    public static List<String> getCanStartActivityFromFullscreenToFreefromList() {
        List<String> list;
        synchronized (LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getCanStartActivityFromFullscreenToFreefromList::LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM = " + LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM);
            list = LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM;
        }
        return list;
    }

    public static List<String> getCanStartActivityFromFullscreenToFreefromListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getCanStartActivityFromFullscreenToFreefromListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(LIST_CAN_START_ACTIVITY_FROM_FULLSCREEN_TO_FREEFROM);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getCanStartActivityFromFullscreenToFreefromListFromCloud::launchInTaskList = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getCanStartActivityFromFullscreenToFreefromListFromCloud :", e2);
        }
        return list;
    }

    public static boolean isWpsSameActivity(ComponentName componentName, ComponentName otherComponentName) {
        if (componentName == null || otherComponentName == null) {
            return false;
        }
        return (WPS_START_PUBLIC_ACTIVITY.equals(componentName.flattenToShortString()) && WPS_SUB_ENTRY_ACTIVITY.equals(otherComponentName.flattenToShortString())) || (WPS_SUB_ENTRY_ACTIVITY.equals(componentName.flattenToShortString()) && WPS_START_PUBLIC_ACTIVITY.equals(otherComponentName.flattenToShortString()));
    }

    public static boolean isAppLockActivity(ComponentName componentName, ComponentName cls) {
        return componentName != null && cls != null && "com.miui.securitycenter/com.miui.applicationlock.ConfirmAccessControl".equals(componentName.flattenToString()) && "com.miui.securitycenter/com.miui.applicationlock.ConfirmAccessControl".equals(cls.flattenToString());
    }

    public static void updateCaptionColor(Context context, View topCaption, View bottomCaption, PhoneWindow phoneWindow) {
        updateCaptionColor(context, topCaption, bottomCaption, null, null, null, phoneWindow, true, 1.0f);
    }

    public static void updateCaptionColor(Context context, View topCaption, View bottomCaption, Drawable topImage, Drawable bottomImage, Drawable borderImage, PhoneWindow phoneWindow) {
        updateCaptionColor(context, topCaption, bottomCaption, topImage, bottomImage, borderImage, phoneWindow, true, 1.0f);
    }

    public static void updateCaptionColor(Context context, View topCaption, View bottomCaption, Drawable topImage, Drawable bottomImage, Drawable borderImage, PhoneWindow phoneWindow, boolean isFocus, float fraction) {
        int windowingMode = getWindowModeFromSystem(phoneWindow, context.getResources().getConfiguration().windowConfiguration);
        int orientation = context.getResources().getConfiguration().orientation;
        context.getResources().getConfiguration().windowConfiguration.getBounds();
        boolean isDarkMode = MiuiMultiWindowUtils.isNightMode(context);
        DecorView decorView = (DecorView) phoneWindow.getDecorView();
        if (borderImage != null) {
            borderImage.setAlpha(isDarkMode ? 255 : 0);
        }
        if ((windowingMode == 5 && orientation == 2) || isDarkMode) {
            if (isDarkMode || isInTopGameListInSystem(context.getPackageName()) || isInTopVideoListInSystem(context.getPackageName())) {
                if (topCaption != null) {
//                    if (decorView.getAttachedActivity() != null && decorView.getAttachedActivity().getComponentName() != null && CLASS_NAME_YOUKU_DETAILACTIVITY.equals(decorView.getAttachedActivity().getComponentName().getClassName())) {
//                        topCaption.setBackgroundColor(-1);
//                    } else {
//                        topCaption.setBackgroundColor(0);
//                    }
                }
                if (bottomCaption != null) {
                    bottomCaption.setBackgroundColor(-16777216);
                    return;
                }
                return;
            }
            int semiTransparentBarColor = context.getResources().getColor(com.android.internal.R.color.system_bar_background_semi_transparent, null);
//            int statusBarColor = DecorView.calculateBarColor(phoneWindow.getAttributes().flags, 67108864, semiTransparentBarColor, phoneWindow.getStatusBarColor(), decorView.getWindowSystemUiVisibility(), 8192, phoneWindow.isStatusBarContrastEnforced());
//            if (topCaption != null) {
//                if (decorView.getAttachedActivity() != null && decorView.getAttachedActivity().getComponentName() != null && CLASS_NAME_QQ_LOGIN.equals(decorView.getAttachedActivity().getComponentName().getClassName())) {
//                    topCaption.setBackgroundColor(0);
//                } else {
//                    topCaption.setBackgroundColor(statusBarColor);
//                }
//            }
            if (bottomCaption != null) {
                if ((phoneWindow.getNavigationBarColor() & (-16777216)) != -16777216) {
                    bottomCaption.setBackgroundColor(-1);
                    return;
                } else {
                    bottomCaption.setBackgroundColor(phoneWindow.getNavigationBarColor());
                    return;
                }
            }
            return;
        }
        if (windowingMode == 5 && orientation == 1) {
            int semiTransparentBarColor2 = context.getResources().getColor(com.android.internal.R.color.system_bar_background_semi_transparent, null);
//            int statusBarColor2 = DecorView.calculateBarColor(phoneWindow.getAttributes().flags, 67108864, semiTransparentBarColor2, phoneWindow.getStatusBarColor(), decorView.getWindowSystemUiVisibility(), 8192, phoneWindow.isStatusBarContrastEnforced());
            if (topCaption != null) {
//                if (decorView.getAttachedActivity() != null && decorView.getAttachedActivity().getComponentName() != null && CLASS_NAME_QQ_LOGIN.equals(decorView.getAttachedActivity().getComponentName().getClassName())) {
//                    topCaption.setBackgroundColor(0);
//                } else {
//                    topCaption.setBackgroundColor(statusBarColor2);
//                }
            }
            if (bottomCaption != null) {
                if ((phoneWindow.getNavigationBarColor() & (-16777216)) != -16777216) {
                    bottomCaption.setBackgroundColor(-1);
                } else {
                    bottomCaption.setBackgroundColor(phoneWindow.getNavigationBarColor());
                }
            }
        }
    }

    public static void updateFreeformConfiguration(Configuration config, String packageName, Application application) {
        LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO = getFreeformVideoWhiteListInSystem();
        if (config != null && config.windowConfiguration.getWindowingMode() == 5) {
            DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
            Rect rect = config.windowConfiguration.getBounds();
            if (LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO.contains(packageName)) {
                displayMetrics.widthPixels = rect.width();
                displayMetrics.heightPixels = rect.height();
                application.getResources().updateConfiguration(config, displayMetrics);
            }
        }
    }

    public static void updateApplicationContext(Context context, Application application, Configuration config) {
    }

    public static void getDisplayMetrics(DisplayMetrics metrics, Configuration config) {
        if (config != null && config.windowConfiguration.getWindowingMode() == 5) {
            Rect rect = config.windowConfiguration.getBounds();
            metrics.widthPixels = rect.width();
            metrics.heightPixels = rect.height();
        }
    }

    public static void getMetrics(Resources resources, DisplayMetrics outMetrics) {
        if (resources != null && resources.getConfiguration().windowConfiguration.getWindowingMode() == 5) {
            Rect rect = resources.getConfiguration().windowConfiguration.getBounds();
            outMetrics.widthPixels = rect.width();
            outMetrics.heightPixels = rect.height();
            Log.d(TAG, "getMetrics::outMetrics = " + outMetrics);
        }
    }

    public static void getSize(Resources resources, Point outSize) {
        if (resources != null && resources.getConfiguration().windowConfiguration.getWindowingMode() == 5) {
            Rect rect = resources.getConfiguration().windowConfiguration.getBounds();
//            outSize.f23x = rect.width();
//            outSize.f24y = rect.height();
//            Log.d(TAG, "getSize::outSize.x = " + outSize.f23x + "  outSize.y = " + outSize.f24y);
        }
    }

    public static int getWidth(Resources resources, int cachedAppWidthCompat) {
        if (resources != null && resources.getConfiguration().windowConfiguration.getWindowingMode() == 5) {
            Rect rect = resources.getConfiguration().windowConfiguration.getBounds();
            Log.d(TAG, "getWidth::cachedAppWidthCompat = " + cachedAppWidthCompat);
            return rect.width();
        }
        return cachedAppWidthCompat;
    }

    public static int getHeight(Resources resources, int cachedAppHeightCompat) {
        if (resources != null && resources.getConfiguration().windowConfiguration.getWindowingMode() == 5) {
            Rect rect = resources.getConfiguration().windowConfiguration.getBounds();
            Log.d(TAG, "getHeight::cachedAppHeightCompat = " + cachedAppHeightCompat);
            return rect.height();
        }
        return cachedAppHeightCompat;
    }

    public static void getWindowVisibleDisplayFrame(Window phoneWindow, Context context, Rect outRect, ComponentName componentName) {
        if (componentName == null || !MAP_ABOUT_ADJUST_WINDOW_VISIBLE_DISPLAY_FRAME.containsKey(componentName.getClassName())) {
            return;
        }
        outRect.left = 0;
        outRect.top = 0;
        outRect.right = outRect.width();
//        View contentView = phoneWindow.findViewById(16908290);
//        if (contentView != null) {
//            outRect.bottom = contentView.getHeight();
//        } else {
//            outRect.bottom = outRect.height() + MAP_ABOUT_ADJUST_WINDOW_VISIBLE_DISPLAY_FRAME.get(componentName.getClassName()).intValue();
//        }
    }

    public static boolean hasSmallFreeWindow() {
        return false;
    }

    public static boolean isInTopGameList(String packageName) {
        return LIST_TOP_GAME.contains(packageName);
    }

    public static boolean isInTopGameListInSystem(String packageName) {
        return getTopGameListInSystem().contains(packageName);
    }

    public static boolean isInTopVideoList(String packageName) {
        return LIST_TOP_VIDEO.contains(packageName);
    }

    public static boolean isInTopVideoListInSystem(String packageName) {
        return getTopVideoListInSystem().contains(packageName);
    }

    public static int getWindowModeFromSystem(PhoneWindow phoneWindow, WindowConfiguration winConfig) {
        Window.WindowControllerCallback windowControllerCallback = phoneWindow.getWindowControllerCallback();
        if (windowControllerCallback != null) {
//            int windowmode = windowControllerCallback.getWindowingMode();
//            return windowmode;
        }
        if (winConfig == null) {
            return 0;
        }
        int windowmode2 = winConfig.getWindowingMode();
        return windowmode2;
    }

    public static boolean needModifyTopInsets(View rootView) {
        return false;
    }

    public static boolean isPrevent(View view, int visibility, ComponentName compName, int windowMode) {
        if (compName != null && "com.tencent.mobileqq".equals(compName.getPackageName()) && windowMode == 5 && !(view instanceof ViewGroup) && (view.getParent() instanceof DecorView)) {
            if ((visibility == 8 || visibility == 4) && !CLASS_NAME_QQ_HB.equals(compName.getClassName())) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void getSurfaceViewBackgroundVisibility(SurfaceView surfaceView, SurfaceControl.Transaction surfaceUpdateTransaction, SurfaceControl backgroundControl, Resources resources, int subLayer, boolean disableBackgroundLayer, String className) {
        if (subLayer < 0) {
            if ((CLASS_NAME_QQLIVE.equals(className) || CLASS_NAME_QIYI_LITE.equals(className)) && surfaceView.getVisibility() == View.VISIBLE && !disableBackgroundLayer) {
                surfaceUpdateTransaction.show(backgroundControl);
            }
        }
    }

    public static boolean needRelunchFreeform(String packageName, Configuration tempConfig, Configuration fullConfig) {
        if (tempConfig != null && fullConfig != null && !LIST_ABOUT_NEED_RELUNCH.contains(packageName)) {
            if (tempConfig.windowConfiguration.getWindowingMode() == 5 || fullConfig.windowConfiguration.getWindowingMode() == 5) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static boolean notNeedRelunchFreeform(String packageName, Configuration tempConfig, Configuration fullConfig) {
        if (tempConfig != null && fullConfig != null && !LIST_ABOUT_NEED_RELUNCH.contains(packageName)) {
            if (tempConfig.windowConfiguration.getWindowingMode() == 5 || fullConfig.windowConfiguration.getWindowingMode() == 5) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean hasSmallFreeformFeature() {
        return MiuiMultiWindowUtils.supportFreeform();
    }

    public static void setFreeformBlackList(List<String> blackList) {
        if (blackList != null) {
            synchronized (sSmallWindowBlackList) {
                sSmallWindowBlackList.clear();
                sSmallWindowBlackList.addAll(blackList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformBlackList::blackList = " + blackList);
    }

    public static Map<String, Integer> calFreeformSuggestionList(Context context, List<String> freeformBlackList, HashMap<String, LinkedList<Long>> allTimestamps) {
        PackageManager packageManager;
        Context context2 = context;
        Map<String, Integer> freeformSuggestionList = new HashMap<>();
        PackageManager packageManager2 = context.getPackageManager();
        @SuppressLint("WrongConstant") SecurityManager securityManager = (SecurityManager) context2.getSystemService("security");
//        List<String> privacyApps = securityManager.getAllPrivacyApps(UserHandle.myUserId());
        List<String> privacyApps = new ArrayList<>();
        boolean isForceResizeable = MiuiMultiWindowUtils.isForceResizeable();
        List<UsageStats> usageStats = MiuiMultiWindowUtils.queryUsageStats(context);
        getFreeformResizeableWhiteListInSystem();
        List<String> abnormalFreeformBlackList = getAbnormalFreeformBlackList(false);
        List<String> abnormalFreeformWhiteList = getAbnormalFreeformWhiteList(false);
        int enableAbnormalFreeform = getEnableAbnormalFreeFormDebug(false);
        for (UsageStats usageStat : usageStats) {
            String packageName = usageStat.getPackageName();
            long packageVersion = 0;
            SecurityManager securityManager2 = securityManager;
            try {
                PackageInfo packageInfo = packageManager2.getPackageInfo(packageName, 0);
                packageVersion = packageInfo.getLongVersionCode();
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
            Integer score = Integer.valueOf(usageStat.getAppLaunchCount());
            LinkedList<Long> freeformTimestamps = allTimestamps.get(packageName);
            if (freeformTimestamps != null) {
                score = Integer.valueOf(score.intValue() + (freeformTimestamps.size() * 10));
            }
            if ((!privacyApps.contains(packageName) && !freeformBlackList.contains(packageName) && ((!sFreeformPackageNotSupport.containsKey(packageName) || packageVersion != sFreeformPackageNotSupport.get(packageName).longValue()) && MiuiMultiWindowUtils.pkgHasIcon(context2, packageName) && (isForceResizeable || MiuiMultiWindowUtils.isPkgMainActivityResizeable(context2, packageName)))) || inFreeformWhiteListInSystem(packageName) || ((MiuiMultiWindowUtils.isLandscapeGameApp(packageName, context2) || MiuiMultiWindowUtils.isPadScreen(context)) && MiuiMultiWindowUtils.isEnableAbnormalFreeform(packageName, context2, abnormalFreeformBlackList, abnormalFreeformWhiteList, enableAbnormalFreeform))) {
                freeformSuggestionList.put(packageName, score);
            }
            securityManager = securityManager2;
        }
        List<PackageInfo> appList = packageManager2.getInstalledPackages(64);
        for (PackageInfo pkg : appList) {
            long packageVersion2 = pkg.getLongVersionCode();
            List<PackageInfo> appList2 = appList;
            if (freeformSuggestionList.containsKey(pkg.packageName)) {
                packageManager = packageManager2;
            } else if (privacyApps.contains(pkg.packageName)) {
                packageManager = packageManager2;
            } else {
                packageManager = packageManager2;
                if (!sFreeformPackageNotSupport.containsKey(pkg.packageName) || packageVersion2 != sFreeformPackageNotSupport.get(pkg.packageName).longValue()) {
                    if ((MiuiMultiWindowUtils.pkgHasIcon(context2, pkg.packageName) && !freeformBlackList.contains(pkg.packageName) && (isForceResizeable || MiuiMultiWindowUtils.isPkgMainActivityResizeable(context2, pkg.packageName))) || inFreeformWhiteListInSystem(pkg.packageName) || ((MiuiMultiWindowUtils.isLandscapeGameApp(pkg.packageName, context2) || MiuiMultiWindowUtils.isPadScreen(context)) && MiuiMultiWindowUtils.isEnableAbnormalFreeform(pkg.packageName, context2, abnormalFreeformBlackList, abnormalFreeformWhiteList, enableAbnormalFreeform))) {
                        freeformSuggestionList.put(pkg.packageName, 0);
                    } else {
                        sFreeformPackageNotSupport.put(pkg.packageName, Long.valueOf(packageVersion2));
                    }
                }
            }
            context2 = context;
            appList = appList2;
            packageManager2 = packageManager;
        }
        return freeformSuggestionList;
    }

    public static boolean updateList(ActivityManager activityManager, Context context, List<String> freeformBlackList) {
        boolean update_success = updateFreeformListFromCloud(context);
        Slog.i(TAG, "updateList   update_success=" + update_success);
        if (!update_success) {
            return update_success;
        }
        List<String> canStartActivityFormFullscreenToFreefromList = getCanStartActivityFromFullscreenToFreefromListFromCloud(context);
        List<String> blackListFromCloud = getFreeformBlackListFromCloud(context);
        List<String> abnormalBlackListFromCloud = getAbnormalFreeformBlackListFromCloud(context);
        List<String> abnormalWhiteListFromCloud = getAbnormalFreeformWhiteListFromCloud(context);
        int enableAbnormalFreeFormDebugFromCloud = getEnableAbnormalFreeFormDebugFromCloud(context);
        List<String> whiteLisFromCloud = getFreeformVideoWhiteListFromCloud(context);
        List<String> disableOverlayListFromCloud = getFreeformDisableOverlayListFromCloud(context);
        List<String> ignoreRequestOrientationListFromCloud = getFreeformIgnoreRequestOrientationListFromCloud(context);
        List<String> freeformRecommendMapApplicationListFromCloud = getFreeformRecommendMapApplicationListFromCloud(context);
        List<String> supportCvwLevelFullListFromCloud = getSupportCvwLevelFullListFromCloud(context);
        List<String> supportCvwLevelVerticalListFromCloud = getSupportCvwLevelVerticalListFromCloud(context);
        List<String> supportCvwLevelHorizontalListFromCloud = getSupportCvwLevelHorizontalListFromCloud(context);
        List<String> unsupportCvwLevelFullListFromCloud = getUnsupportCvwLevelFullListFromCloud(context);
        List<String> freeformRecommendWaitingApplicationListFromCloud = getFreeformRecommendWaitingApplicationListFromCloud(context);
        List<String> desktopModeLaunchFullscreenAppList = getDesktopModeLaunchFullscreenAppListFromCloud(context);
        List<String> desktopModeLaunchFullscreenNotHideOtherAppList = getDesktopModeLaunchFullscreenNotHideOtherAppListFromCloud(context);
        List<String> desktopModeLaunchFreeformIgnoreTranslucentAppList = getDesktopModeLaunchFreeformIgnoreTranslucentAppListFromCloud(context);
        List<String> needRelunchListFromCloud = getFreeformNeedRelunchListFromCloud(context);
        List<String> startFromFreeformBlackListFromCloud = getStartFromFreeformBlackListFromCloud(context);
        List<String> hideSelfIfNewFreeformTaskWhiteListFromCloud = getHideSelfIfNewFreeformTaskWhiteListFromCloud(context);
        List<String> showHiddenTaskIfFinishedWhiteListFromCloud = getShowHiddenTaskIfFinishedWhiteListFromCloud(context);
        List<String> resizeableWhiteListFromCloud = getFreeformResizeableWhiteListFromCloud(context);
        List<String> cvwUnsupportedWhiteListFromCloud = getCvwUnsupportedFreeformWhiteListFromCloud(context);
        List<String> applicationLockBlackListFromCloud = getApplicationLockActivityListFromCloud(context);
        List<String> freeformCaptionInsetsHeightToZeroListFromCloud = getFreeformCaptionInsetsHeightToZeroListFromCloud(context);
        List<String> forceLandscapeApplicationFromCloud = getForceLandscapeApplicationFromCloud(context);
        List<String> additionalFreeformAspectRatio1AppsFromCloud = getAdditionalFreeformAspectRatio1AppsFromCloud(context);
        List<String> additionalFreeformAspectRatio2AppsFromCloud = getAdditionalFreeformAspectRatio2AppsFromCloud(context);
        List<String> desktopFreeformWhiteListFromCloud = getDesktopFreeformWhiteListFromCloud(context);
        List<String> topGameListFromCloud = getTopGameListFromCloud(context);
        List<String> topVideoListFromCloud = getTopVideoListFromCloud(context);
        boolean enableForeground = getEnableForegroundPinFromCloud(context);
        List<String> audioForegroundPinAppListFromCloud = getAudioForegroundPinAppListFromCloud(context);
        List<String> foregroundPinAppWhiteListFromCloud = getForegroundPinAppWhiteListFromCloud(context);
        List<String> foregroundPinAppBlackListFromCloud = getForegroundPinAppBlackListFromCloud(context);
        List<String> fixedRotationAppListFromCloud = getFixedRotationAppListFromCloud(context);
        List<String> rotationFromDisplayAppFromCloud = getRotationFromDisplayAppFromCloud(context);
        List<String> useDefaultCameraPipelineAppFromCloud = getUseDefaultCameraPipelineAppFromCloud(context);
        List<String> sensorDisableWhiteListFromCloud = getSensorDisableWhiteListFromCloud(context);
        List<String> launchInTaskListFromCloud = getLaunchInTaskListFromCloud(context);
        List<String> sensorDisableListFromCloud = getSensorDisableListFromCloud(context);
        List<String> lockModeActivityListFromCloud = getAboutLockModeActivityListFromCloud(context);
//        activityManager.setCanStartActivityFromFullscreenToFreefromList(canStartActivityFormFullscreenToFreefromList);
//        activityManager.setAbnormalFreeformBlackList(abnormalBlackListFromCloud);
//        activityManager.setAbnormalFreeformWhiteList(abnormalWhiteListFromCloud);
//        activityManager.setEnableAbnormalFreeFormDebug(enableAbnormalFreeFormDebugFromCloud);
//        activityManager.setFreeformBlackList(blackListFromCloud);
//        activityManager.setFreeformVideoWhiteList(whiteLisFromCloud);
//        activityManager.setFreeformDisableOverlayList(disableOverlayListFromCloud);
//        activityManager.setFreeformRecommendMapApplicationList(freeformRecommendMapApplicationListFromCloud);
//        activityManager.setFreeformRecommendWaitingApplicationList(freeformRecommendWaitingApplicationListFromCloud);
//        activityManager.setSupportCvwLevelFullList(supportCvwLevelFullListFromCloud);
//        activityManager.setSupportCvwLevelVerticalList(supportCvwLevelVerticalListFromCloud);
//        activityManager.setSupportCvwLevelHorizontalList(supportCvwLevelHorizontalListFromCloud);
//        activityManager.setUnsupportCvwLevelFullList(unsupportCvwLevelFullListFromCloud);
//        activityManager.setDesktopModeLaunchFullscreenAppList(desktopModeLaunchFullscreenAppList);
//        activityManager.setDesktopModeLaunchFullscreenNotHideOtherAppList(desktopModeLaunchFullscreenNotHideOtherAppList);
//        activityManager.setDesktopModeLaunchFreeformIgnoreTranslucentAppList(desktopModeLaunchFreeformIgnoreTranslucentAppList);
//        activityManager.setFreeformIgnoreRequestOrientationList(ignoreRequestOrientationListFromCloud);
//        activityManager.setFreeformNeedRelunchList(needRelunchListFromCloud);
//        activityManager.setStartFromFreeformBlackList(startFromFreeformBlackListFromCloud);
//        activityManager.setHideSelfIfNewFreeformTaskWhiteList(hideSelfIfNewFreeformTaskWhiteListFromCloud);
//        activityManager.setShowHiddenTaskIfFinishedWhiteList(showHiddenTaskIfFinishedWhiteListFromCloud);
//        activityManager.setFreeformResizeableWhiteList(resizeableWhiteListFromCloud);
//        ActivityManager.setCvwUnsupportedFreeformWhiteList(cvwUnsupportedWhiteListFromCloud);
//        ActivityManager.setAdditionalFreeformAspectRatio1Apps(additionalFreeformAspectRatio1AppsFromCloud);
//        ActivityManager.setAdditionalFreeformAspectRatio2Apps(additionalFreeformAspectRatio2AppsFromCloud);
//        ActivityManager.setDesktopFreeformWhiteList(desktopFreeformWhiteListFromCloud);
//        activityManager.setApplicationLockActivityList(applicationLockBlackListFromCloud);
//        activityManager.setFreeformCaptionInsetsHeightToZeroList(freeformCaptionInsetsHeightToZeroListFromCloud);
//        activityManager.setForceLandscapeApplication(forceLandscapeApplicationFromCloud);
//        activityManager.setTopGameList(topGameListFromCloud);
//        activityManager.setTopVideoList(topVideoListFromCloud);
//        activityManager.setEnableForegroundPin(enableForeground);
//        activityManager.setAudioForegroundPinAppList(audioForegroundPinAppListFromCloud);
//        activityManager.setForegroundPinAppWhiteList(foregroundPinAppWhiteListFromCloud);
//        activityManager.setForegroundPinAppBlackList(foregroundPinAppBlackListFromCloud);
//        activityManager.setFixedRotationAppList(fixedRotationAppListFromCloud);
//        activityManager.setRotationFromDisplayApp(rotationFromDisplayAppFromCloud);
//        activityManager.setUseDefaultCameraPipelineApp(useDefaultCameraPipelineAppFromCloud);
//        activityManager.setSensorDisableWhiteList(sensorDisableWhiteListFromCloud);
//        activityManager.setLaunchInTaskList(launchInTaskListFromCloud);
//        activityManager.setSensorDisableList(sensorDisableListFromCloud);
//        activityManager.setLockModeActivityList(lockModeActivityListFromCloud);
        freeformBlackList.addAll(blackListFromCloud);
        return update_success;
    }

    public static List<String> getFreeformBlackList() {
        ArrayList<String> arrayList;
        if (sEnableBlackListAppFreeFormDebug == null) {
            synchronized (sEmptyList) {
                if (sEnableBlackListAppFreeFormDebug == null) {
                    try {
                        int res = Settings.Secure.getInt(ActivityThread.currentApplication().getContentResolver(), MiuiMultiWindowUtils.DEBUG_MODE_FOR_FREEFORM_BLACKLIST, -1);
                        Log.d(TAG, "getFreeformBlackList: res = " + res);
                        boolean z2 = true;
                        if (res != 1) {
                            z2 = false;
                        }
                        sEnableBlackListAppFreeFormDebug = Boolean.valueOf(z2);
                    } catch (Exception e2) {
                        Log.e(TAG, "getFreeformBlackList: Exception Message: " + e2.getCause());
                    }
                }
            }
        }
        if (sEnableBlackListAppFreeFormDebug != null && sEnableBlackListAppFreeFormDebug.booleanValue()) {
            Log.d(TAG, "getFreeformBlackList: Empty List");
            StringBuilder append = new StringBuilder().append("MiuiMultiWindowAdapter::getFreeformBlackList::sEmptyList = ");
            List<String> list = sEmptyList;
            Log.d(TAG, append.append(list).toString());
            return list;
        }
        synchronized (sSmallWindowBlackList) {
            if (sSmallWindowBlackList.isEmpty()) {
                sSmallWindowBlackList.addAll(FREEFORM_BLACK_LIST);
            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformBlackList::sSmallWindowBlackList = " + sSmallWindowBlackList);
            arrayList = sSmallWindowBlackList;
        }
        return arrayList;
    }

    public static List<String> getFreeformBlackListFromCloud(Context context) {
        List<String> smallWindowBlackList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, miui.os.Build.IS_TABLET ? PAD_SMALL_WINDOW_BLACK_LIST : SMALL_WINDOW_BLACK_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformBlackListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    smallWindowBlackList.add(apps.getString(i2));
//                }
//            } else {
//                smallWindowBlackList.addAll(FREEFORM_BLACK_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformBlackListFromCloud::smallWindowBlackList = " + smallWindowBlackList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformBlackListFromCloud :", e2);
        }
        return smallWindowBlackList;
    }

    public static void setAbnormalFreeformBlackList(List<String> abnormalFreeformBlacklist) {
        if (abnormalFreeformBlacklist != null) {
            synchronized (ABNORMAL_FREEFORM_BLACK_LIST) {
                ABNORMAL_FREEFORM_BLACK_LIST.clear();
                ABNORMAL_FREEFORM_BLACK_LIST.addAll(abnormalFreeformBlacklist);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setAbnormalFreeformBlackList::abnormalFreeformBlacklist = " + abnormalFreeformBlacklist);
    }

    public static List<String> getAbnormalFreeformBlackList(boolean fromSystem) {
        List<String> list;
        if (fromSystem) {
            synchronized (ABNORMAL_FREEFORM_BLACK_LIST) {
                Log.d(TAG, "MiuiMultiWindowAdapter::getAbnormalFreeformBlackList::ABNORMAL_FREEFORM_BLACK_LIST = " + ABNORMAL_FREEFORM_BLACK_LIST);
                list = ABNORMAL_FREEFORM_BLACK_LIST;
            }
            return list;
        }
//        return ActivityManager.getAbnormalFreeformBlackList(true);
        return null;
    }

    public static List<String> getAbnormalFreeformBlackListFromCloud(Context context) {
        List<String> abnormalFreeformBlacklist = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, miui.os.Build.IS_TABLET ? ABNORMAL_PAD_SMALL_WINDOW_BLACKLIST : ABNORMAL_SMALL_WINDOW_BLACKLIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getAbnormalFreeformBlackListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    abnormalFreeformBlacklist.add(apps.getString(i2));
//                }
//            } else {
//                abnormalFreeformBlacklist.addAll(ABNORMAL_FREEFORM_BLACK_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getAbnormalFreeformBlackListFromCloud::abnormalFreeformBlacklist = " + abnormalFreeformBlacklist);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getAbnormalFreeformBlackListFromCloud :", e2);
        }
        return abnormalFreeformBlacklist;
    }

    public static void setAbnormalFreeformWhiteList(List<String> abnormalFreeformWhitelist) {
        if (abnormalFreeformWhitelist != null) {
            synchronized (ABNORMAL_FREEFORM_WHITE_LIST) {
                ABNORMAL_FREEFORM_WHITE_LIST.clear();
                ABNORMAL_FREEFORM_WHITE_LIST.addAll(abnormalFreeformWhitelist);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setAbnormalFreeformWhiteList::abnormalFreeformWhitelist = " + abnormalFreeformWhitelist);
    }

    public static List<String> getAbnormalFreeformWhiteList(boolean fromSystem) {
        List<String> list;
        if (fromSystem) {
            synchronized (ABNORMAL_FREEFORM_WHITE_LIST) {
                Log.d(TAG, "MiuiMultiWindowAdapter::getAbnormalFreeformWhiteList::ABNORMAL_FREEFORM_WHITE_LIST = " + ABNORMAL_FREEFORM_WHITE_LIST);
                list = ABNORMAL_FREEFORM_WHITE_LIST;
            }
            return list;
        }
//        return ActivityManager.getAbnormalFreeformWhiteList(true);
        return null;
    }

    public static List<String> getAbnormalFreeformWhiteListFromCloud(Context context) {
        List<String> abnormalFreeformWhitelist = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, miui.os.Build.IS_TABLET ? ABNORMAL_PAD_SMALL_WINDOW_WHITELIST : ABNORMAL_SMALL_WINDOW_WHITELIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getAbnormalFreeformWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    abnormalFreeformWhitelist.add(apps.getString(i2));
//                }
//            } else {
//                abnormalFreeformWhitelist.addAll(ABNORMAL_FREEFORM_WHITE_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getAbnormalFreeformWhiteListFromCloud::abnormalFreeformWhitelist = " + abnormalFreeformWhitelist);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getAbnormalFreeformWhiteListFromCloud :", e2);
        }
        return abnormalFreeformWhitelist;
    }

    public static boolean updateFreeformListFromCloud(Context context) {
        boolean update_success = false;
        //        try {
//            MiuiSettings.SettingsCloudData.CloudData data = MiuiSettings.SettingsCloudData.getCloudDataSingle(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, null, null, false);
//            update_success = data != null;
//            Log.d(TAG, "MiuiMultiWindowAdapter::updateFreeformListFromCloud::update_success = " + update_success);
//        } catch (Exception e2) {
//            Log.e(TAG, "Exception when get updateFreeformListFromCloud :", e2);
//        }
        return update_success;
    }

    public static void setFreeformVideoWhiteList(List<String> whiteList) {
        if (whiteList != null) {
            synchronized (sSmallWindowWhiteList) {
                sSmallWindowWhiteList.clear();
                sSmallWindowWhiteList.addAll(whiteList);
                LIST_ABOUT_NO_NEED_IN_FREEFORM.clear();
                LIST_ABOUT_NO_NEED_IN_FREEFORM.addAll(whiteList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformVideoWhiteList::whiteList = " + whiteList);
    }

    public static List<String> getFreeformVideoWhiteList() {
        ArrayList<String> arrayList;
        synchronized (sSmallWindowWhiteList) {
            if (sSmallWindowWhiteList.isEmpty()) {
                sSmallWindowWhiteList.addAll(LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO);
            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformVideoWhiteList::sSmallWindowWhiteList = " + sSmallWindowWhiteList);
            arrayList = sSmallWindowWhiteList;
        }
        return arrayList;
    }

    public static List<String> getFreeformVideoWhiteListInSystem() {
//        LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO = ActivityManager.getFreeformVideoWhiteList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformVideoWhiteListInSystem::LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO = " + LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO);
        return LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO;
    }

    public static List<String> getFreeformVideoWhiteListFromCloud(Context context) {
        List<String> smallWindowWhiteList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_VIDEO_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformVideoWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    smallWindowWhiteList.add(apps.getString(i2));
//                }
//            } else {
//                smallWindowWhiteList.addAll(LIST_ABOUT_SUPPORT_LANDSCAPE_VIDEO);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformVideoWhiteListFromCloud::smallWindowWhiteList = " + smallWindowWhiteList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformVideoWhiteListFromCloud :", e2);
        }
        return smallWindowWhiteList;
    }

    public static void setFreeformDisableOverlayList(List<String> disableOverlayList) {
        if (disableOverlayList != null) {
            synchronized (LIST_DISABLE_OVERLAY_WITH_APP_CONTENT) {
                LIST_DISABLE_OVERLAY_WITH_APP_CONTENT.clear();
                LIST_DISABLE_OVERLAY_WITH_APP_CONTENT.addAll(disableOverlayList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformDisableOverlayList::disableOverlayList = " + disableOverlayList);
    }

    public static List<String> getFreeformDisableOverlayList() {
        List<String> list;
        synchronized (LIST_DISABLE_OVERLAY_WITH_APP_CONTENT) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformDisableOverlayList::LIST_DISABLE_OVERLAY_WITH_APP_CONTENT = " + LIST_DISABLE_OVERLAY_WITH_APP_CONTENT);
            list = LIST_DISABLE_OVERLAY_WITH_APP_CONTENT;
        }
        return list;
    }

    public static List<String> getFreeformDisableOverlayListInSystem() {
//        LIST_DISABLE_OVERLAY_WITH_APP_CONTENT = ActivityManager.getFreeformDisableOverlayList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformDisableOverlayListInSystem::LIST_DISABLE_OVERLAY_WITH_APP_CONTENT = " + LIST_DISABLE_OVERLAY_WITH_APP_CONTENT);
        return LIST_DISABLE_OVERLAY_WITH_APP_CONTENT;
    }

    public static List<String> getFreeformDisableOverlayListFromCloud(Context context) {
        List<String> smallWindowDisableOverlayList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_DISABLE_OVERLAY_WITH_APP_CONTENT_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformDisableOverlayListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    smallWindowDisableOverlayList.add(apps.getString(i2));
//                }
//            } else {
//                smallWindowDisableOverlayList.addAll(LIST_DISABLE_OVERLAY_WITH_APP_CONTENT);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformDisableOverlayListFromCloud::smallWindowDisableOverlayList = " + smallWindowDisableOverlayList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformDisableOverlayListFromCloud :", e2);
        }
        return smallWindowDisableOverlayList;
    }

    public static void setFreeformIgnoreRequestOrientationList(List<String> ignoreRequestOrientationList) {
        if (ignoreRequestOrientationList != null) {
            synchronized (LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM) {
                LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM.clear();
                LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM.addAll(ignoreRequestOrientationList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformIgnoreRequestOrientationList::ignoreRequestOrientationList = " + ignoreRequestOrientationList);
    }

    public static List<String> getFreeformIgnoreRequestOrientationList() {
        List<String> list;
        synchronized (LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformIgnoreRequestOrientationList::LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM = " + LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM);
            list = LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM;
        }
        return list;
    }

    public static List<String> getFreeformIgnoreRequestOrientationListFromCloud(Context context) {
        List<String> smallWindowIgnoreRequestOrientationList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_ABOUT_IGNORE_REQUEST_ORIENTATION_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformIgnoreRequestOrientationListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    smallWindowIgnoreRequestOrientationList.add(apps.getString(i2));
//                }
//            } else {
//                smallWindowIgnoreRequestOrientationList.addAll(LIST_ABOUT_IGNORE_REQUEST_ORIENTATION_IN_FREEFORM);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformIgnoreRequestOrientationListFromCloud::smallWindowIgnoreRequestOrientationList = " + smallWindowIgnoreRequestOrientationList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformIgnoreRequestOrientationListFromCloud :", e2);
        }
        return smallWindowIgnoreRequestOrientationList;
    }

    public static void setFreeformNeedRelunchList(List<String> needRelunchList) {
        if (needRelunchList != null) {
            synchronized (LIST_ABOUT_NEED_RELUNCH) {
                LIST_ABOUT_NEED_RELUNCH.clear();
                LIST_ABOUT_NEED_RELUNCH.addAll(needRelunchList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformNeedRelunchList::needRelunchList = " + needRelunchList);
    }

    public static List<String> getFreeformNeedRelunchList() {
        List<String> list;
        synchronized (LIST_ABOUT_NEED_RELUNCH) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformNeedRelunchList::LIST_ABOUT_NEED_RELUNCH = " + LIST_ABOUT_NEED_RELUNCH);
            list = LIST_ABOUT_NEED_RELUNCH;
        }
        return list;
    }

    public static List<String> getFreeformNeedRelunchListFromCloud(Context context) {
        List<String> smallWindowNeedRelunchList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_NEED_RELUNCH_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformNeedRelunchListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    smallWindowNeedRelunchList.add(apps.getString(i2));
//                }
//            } else {
//                smallWindowNeedRelunchList.addAll(LIST_ABOUT_NEED_RELUNCH);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformNeedRelunchListFromCloud::smallWindowNeedRelunchList = " + smallWindowNeedRelunchList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformNeedRelunchListFromCloud :", e2);
        }
        return smallWindowNeedRelunchList;
    }

    public static void setStartFromFreeformBlackList(List<String> startFromFreeformBlackList) {
        if (startFromFreeformBlackList != null) {
            synchronized (START_FROM_FREEFORM_BLACK_LIST_ACTIVITY) {
                START_FROM_FREEFORM_BLACK_LIST_ACTIVITY.clear();
                START_FROM_FREEFORM_BLACK_LIST_ACTIVITY.addAll(startFromFreeformBlackList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setStartFromFreeformBlackList::startFromFreeformBlackList = " + startFromFreeformBlackList);
    }

    public static List<String> getStartFromFreeformBlackList() {
        List<String> list;
        synchronized (START_FROM_FREEFORM_BLACK_LIST_ACTIVITY) {
            Log.d(TAG, "MiuiMultiWindowAdapter::StartFromFreeformBlackList::START_FROM_FREEFORM_BLACK_LIST_ACTIVITY = " + START_FROM_FREEFORM_BLACK_LIST_ACTIVITY);
            list = START_FROM_FREEFORM_BLACK_LIST_ACTIVITY;
        }
        return list;
    }

    public static List<String> getStartFromFreeformBlackListFromCloud(Context context) {
        List<String> startFromFreeformBlackList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_START_FROM_FREEFORM_BLACK_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getStartFromFreeformBlackListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    startFromFreeformBlackList.add(apps.getString(i2));
//                }
//            } else {
//                startFromFreeformBlackList.addAll(START_FROM_FREEFORM_BLACK_LIST_ACTIVITY);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getStartFromFreeformBlackListFromCloud::startFromFreeformBlackList = " + startFromFreeformBlackList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getStartFromFreeformBlackListFromCloud :", e2);
        }
        return startFromFreeformBlackList;
    }

    public static void setHideSelfIfNewFreeformTaskWhiteList(List<String> hideSelfIfNewFreeformTaskWhiteList) {
        if (hideSelfIfNewFreeformTaskWhiteList != null) {
            synchronized (HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY) {
                HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY.clear();
                HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY.addAll(hideSelfIfNewFreeformTaskWhiteList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setHideSelfIfNewFreeformTaskWhiteList::hideSelfIfNewFreeformTaskWhiteList = " + hideSelfIfNewFreeformTaskWhiteList);
    }

    public static List<String> getHideSelfIfNewFreeformTaskWhiteList() {
        List<String> list;
        synchronized (HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getHideSelfIfNewFreeformTaskWhiteList::HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY = " + HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY);
            list = HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY;
        }
        return list;
    }

    public static List<String> getHideSelfIfNewFreeformTaskWhiteListFromCloud(Context context) {
        List<String> hideSelfIfNewFreeformTaskWhiteList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getHideSelfIfNewFreeformTaskWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    hideSelfIfNewFreeformTaskWhiteList.add(apps.getString(i2));
//                }
//            } else {
//                hideSelfIfNewFreeformTaskWhiteList.addAll(HIDE_SELF_IF_NEW_FREEFORM_TASK_WHITE_LIST_ACTIVITY);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getHideSelfIfNewFreeformTaskWhiteListFromCloud::hideSelfIfNewFreeformTaskWhiteList = " + hideSelfIfNewFreeformTaskWhiteList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getHideSelfIfNewFreeformTaskWhiteListFromCloud :", e2);
        }
        return hideSelfIfNewFreeformTaskWhiteList;
    }

    public static void setShowHiddenTaskIfFinishedWhiteList(List<String> showHiddenTaskIfFinishedWhiteList) {
        if (showHiddenTaskIfFinishedWhiteList != null) {
            synchronized (SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY) {
                SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY.clear();
                SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY.addAll(showHiddenTaskIfFinishedWhiteList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setShowHiddenTaskIfFinishedWhiteList::showHiddenTaskIfFinishedWhiteList = " + showHiddenTaskIfFinishedWhiteList);
    }

    public static List<String> getShowHiddenTaskIfFinishedWhiteList() {
        List<String> list;
        synchronized (SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getShowHiddenTaskIfFinishedWhiteList::SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY = " + SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY);
            list = SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY;
        }
        return list;
    }

    public static List<String> getShowHiddenTaskIfFinishedWhiteListFromCloud(Context context) {
        List<String> showHiddenTaskIfFinishedWhiteList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getShowHiddenTaskIfFinishedWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    showHiddenTaskIfFinishedWhiteList.add(apps.getString(i2));
//                }
//            } else {
//                showHiddenTaskIfFinishedWhiteList.addAll(SHOW_HIDDEN_TASK_IF_FINISHED_WHITE_LIST_ACTIVITY);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getShowHiddenTaskIfFinishedWhiteListFromCloud::showHiddenTaskIfFinishedWhiteList = " + showHiddenTaskIfFinishedWhiteList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getShowHiddenTaskIfFinishedWhiteListFromCloud :", e2);
        }
        return showHiddenTaskIfFinishedWhiteList;
    }

    public static void setFreeformResizeableWhiteList(List<String> resizeableWhiteList) {
        if (resizeableWhiteList != null) {
            synchronized (FREEFORM_RESIZEABLE_WHITE_LIST) {
                FREEFORM_RESIZEABLE_WHITE_LIST.clear();
                FREEFORM_RESIZEABLE_WHITE_LIST.addAll(resizeableWhiteList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformResizeableWhiteList::resizeableWhiteList = " + resizeableWhiteList);
    }

    public static List<String> getFreeformResizeableWhiteList() {
        List<String> list;
        synchronized (FREEFORM_RESIZEABLE_WHITE_LIST) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformResizeableWhiteList::FREEFORM_RESIZEABLE_WHITE_LIST = " + FREEFORM_RESIZEABLE_WHITE_LIST);
            list = FREEFORM_RESIZEABLE_WHITE_LIST;
        }
        return list;
    }

    public static List<String> getFreeformResizeableWhiteListInSystem() {
//        FREEFORM_RESIZEABLE_WHITE_LIST = ActivityManager.getFreeformResizeableWhiteList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformResizeableWhiteListInSystem::FREEFORM_RESIZEABLE_WHITE_LIST = " + FREEFORM_RESIZEABLE_WHITE_LIST);
        return FREEFORM_RESIZEABLE_WHITE_LIST;
    }

    public static void setCvwUnsupportedFreeformWhiteList(List<String> list) {
        if (list != null) {
            synchronized (CVW_UNSUPPORTED_FREEFORM_WHITE_LIST) {
                CVW_UNSUPPORTED_FREEFORM_WHITE_LIST.clear();
                CVW_UNSUPPORTED_FREEFORM_WHITE_LIST.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setCvwUnsupportedFreeformWhiteList::list = " + list);
    }

    public static List<String> getCvwUnsupportedFreeformWhiteList() {
        List<String> list;
        synchronized (CVW_UNSUPPORTED_FREEFORM_WHITE_LIST) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getCvwUnsupportedFreeformWhiteList::CVW_UNSUPPORTED_FREEFORM_WHITE_LIST = " + CVW_UNSUPPORTED_FREEFORM_WHITE_LIST);
            list = CVW_UNSUPPORTED_FREEFORM_WHITE_LIST;
        }
        return list;
    }

    public static List<String> getCvwUnsupportedFreeformWhiteListInSystem() {
//        CVW_UNSUPPORTED_FREEFORM_WHITE_LIST = ActivityManager.getCvwUnsupportedFreeformWhiteList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getCvwUnsupportedFreeformWhiteListInSystem::CVW_UNSUPPORTED_FREEFORM_WHITE_LIST = " + CVW_UNSUPPORTED_FREEFORM_WHITE_LIST);
        return CVW_UNSUPPORTED_FREEFORM_WHITE_LIST;
    }

    public static List<String> getCvwUnsupportedFreeformWhiteListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_CVW_UNSUPPORTED_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getCvwUnsupportedFreeformWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(CVW_UNSUPPORTED_FREEFORM_WHITE_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getCvwUnsupportedFreeformWhiteListFromCloud::cvwUnsupportedFreeformWhiteList = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getCvwUnsupportedFreeformWhiteListFromCloud :", e2);
        }
        return list;
    }

    public static void setAdditionalFreeformAspectRatio1Apps(List<String> list) {
        if (!MiuiMultiWindowUtils.isTablet()) {
            return;
        }
        if (list != null) {
            synchronized (ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS) {
                ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS.clear();
                ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setAdditionalFreeformAspectRatio1Apps::list = " + list);
    }

    public static List<String> getAdditionalFreeformAspectRatio1Apps() {
        List<String> list;
        if (!MiuiMultiWindowUtils.isTablet()) {
            return new ArrayList();
        }
        synchronized (ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio1Apps::ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS = " + ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS);
            list = ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS;
        }
        return list;
    }

    public static List<String> getAdditionalFreeformAspectRatio1AppsInSystem() {
//        ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS = ActivityManager.getAdditionalFreeformAspectRatio1Apps();
        Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio1AppsInSystem::ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS = " + ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS);
        return ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS;
    }

    public static List<String> getAdditionalFreeformAspectRatio1AppsFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APP_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio1AppsFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(ADDITIONAL_FREEFORM_ASPECT_RATIO_1_APPS);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio1AppsFromCloud::additionalFreeformAspectRatio1Apps = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getAdditionalFreeformAspectRatio1AppsFromCloud :", e2);
        }
        return list;
    }

    public static void setAdditionalFreeformAspectRatio2Apps(List<String> list) {
        if (!MiuiMultiWindowUtils.isTablet()) {
            return;
        }
        if (list != null) {
            synchronized (ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS) {
                ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS.clear();
                ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS.addAll(list);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setAdditionalFreeformAspectRatio2Apps::list = " + list);
    }

    public static List<String> getAdditionalFreeformAspectRatio2Apps() {
        List<String> list;
        if (!MiuiMultiWindowUtils.isTablet()) {
            return new ArrayList();
        }
        synchronized (ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio2Apps::ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS = " + ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS);
            list = ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS;
        }
        return list;
    }

    public static List<String> getAdditionalFreeformAspectRatio2AppsInSystem() {
//        ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS = ActivityManager.getAdditionalFreeformAspectRatio2Apps();
        Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio2AppsInSystem::ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS = " + ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS);
        return ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS;
    }

    public static List<String> getAdditionalFreeformAspectRatio2AppsFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APP_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio2AppsFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(ADDITIONAL_FREEFORM_ASPECT_RATIO_2_APPS);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getAdditionalFreeformAspectRatio2AppsFromCloud::additionalFreeformAspectRatio2Apps = " + list);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getAdditionalFreeformAspectRatio2AppsFromCloud :", e2);
        }
        return list;
    }

    public static void setDesktopFreeformWhiteList(List<String> list) {
        if (list != null) {
            synchronized (DESKTOP_FREEFORM_WHITE_LIST) {
                DESKTOP_FREEFORM_WHITE_LIST.clear();
                DESKTOP_FREEFORM_WHITE_LIST.addAll(list);
            }
        }
        if (IS_DEBUG_BUILD) {
            Log.d(TAG, "MiuiMultiWindowAdapter::setDesktopFreeformWhiteList::list = " + list);
        }
    }

    public static List<String> getDesktopFreeformWhiteList() {
        List<String> list;
//        if (!ActivityManager.isMiuiDesktopModeActive()) {
//            return new ArrayList();
//        }
        synchronized (DESKTOP_FREEFORM_WHITE_LIST) {
            if (IS_DEBUG_BUILD) {
                Log.e(TAG, "MiuiMultiWindowAdapter::getDesktopFreeformWhiteList::DESKTOP_FREEFORM_WHITE_LIST = " + DESKTOP_FREEFORM_WHITE_LIST);
            }
            list = DESKTOP_FREEFORM_WHITE_LIST;
        }
        return list;
    }

    public static List<String> getDesktopFreeformWhiteListInSystem() {
//        DESKTOP_FREEFORM_WHITE_LIST = ActivityManager.getDesktopFreeformWhiteList();
        if (IS_DEBUG_BUILD) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopFreeformWhiteListInSystem::DESKTOP_FREEFORM_WHITE_LIST = " + DESKTOP_FREEFORM_WHITE_LIST);
        }
        return DESKTOP_FREEFORM_WHITE_LIST;
    }

    public static List<String> getDesktopFreeformWhiteListFromCloud(Context context) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, DKT_FREEFORM_WHITE_LIST, null);
//            if (IS_DEBUG_BUILD) {
//                Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopFreeformWhiteListFromCloud::data = " + data);
//            }
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(DESKTOP_FREEFORM_WHITE_LIST);
//            }
            if (IS_DEBUG_BUILD) {
                Log.d(TAG, "MiuiMultiWindowAdapter::getDesktopFreeformWhiteListFromCloud::" + list);
            }
        } catch (Exception e2) {
            if (IS_DEBUG_BUILD) {
                Log.e(TAG, "Exception when getDesktopFreeformWhiteListFromCloud :", e2);
            }
        }
        return list;
    }

    public static boolean inFreeformWhiteList(String packageName) {
        List<String> whiteList = getFreeformResizeableWhiteList();
        List<String> desktopWhiteList = getDesktopFreeformWhiteList();
        return (whiteList != null && whiteList.contains(packageName)) || (desktopWhiteList != null && desktopWhiteList.contains(packageName));
    }

    public static boolean inFreeformWhiteListInSystem(String packageName) {
        List<String> whiteList = getFreeformResizeableWhiteListInSystem();
        List<String> desktopWhiteList = getDesktopFreeformWhiteListInSystem();
        return (whiteList != null && whiteList.contains(packageName)) || (desktopWhiteList != null && desktopWhiteList.contains(packageName));
    }

    public static List<String> getFreeformResizeableWhiteListFromCloud(Context context) {
        List<String> resizeableWhiteList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_RESIZE_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformResizeableWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    resizeableWhiteList.add(apps.getString(i2));
//                }
//            } else {
//                resizeableWhiteList.addAll(FREEFORM_RESIZEABLE_WHITE_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformResizeableWhiteListFromCloud::resizeableWhiteList = " + resizeableWhiteList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformResizeableWhiteListFromCloud :", e2);
        }
        return resizeableWhiteList;
    }

    public static void setApplicationLockActivityList(List<String> applicationLockActivityList) {
        if (applicationLockActivityList != null) {
            synchronized (APPLICATION_LOCK_ACTIVITY) {
                APPLICATION_LOCK_ACTIVITY.clear();
                APPLICATION_LOCK_ACTIVITY.addAll(applicationLockActivityList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setApplicationLockActivityList::applicationLockActivityList = " + applicationLockActivityList);
    }

    public static List<String> getApplicationLockActivityList() {
        List<String> list;
        synchronized (APPLICATION_LOCK_ACTIVITY) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getApplicationLockActivityList::APPLICATION_LOCK_ACTIVITY = " + APPLICATION_LOCK_ACTIVITY);
            list = APPLICATION_LOCK_ACTIVITY;
        }
        return list;
    }

    public static List<String> getApplicationLockActivityListFromCloud(Context context) {
        List<String> applicationLockActivityList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_APPLICATION_LOCK_BLACK_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getApplicationLockActivityListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    applicationLockActivityList.add(apps.getString(i2));
//                }
//            } else {
//                applicationLockActivityList.addAll(APPLICATION_LOCK_ACTIVITY);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getApplicationLockActivityListFromCloud::applicationLockActivityList = " + applicationLockActivityList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getApplicationLockActivityListFromCloud :", e2);
        }
        return applicationLockActivityList;
    }

    public static void setFreeformCaptionInsetsHeightToZeroList(List<String> freeformCaptionInsetsHeightToZeroList) {
        if (freeformCaptionInsetsHeightToZeroList != null) {
            synchronized (FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST) {
                FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST.clear();
                FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST.addAll(freeformCaptionInsetsHeightToZeroList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFreeformCaptionInsetsHeightToZeroList::freeformCaptionInsetsHeightToZeroList = " + freeformCaptionInsetsHeightToZeroList);
    }

    public static List<String> getFreeformCaptionInsetsHeightToZeroList() {
        List<String> list;
        synchronized (FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformCaptionInsetsHeightToZeroList::FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST = " + FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST);
            list = FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST;
        }
        return list;
    }

    public static List<String> getFreeformCaptionInsetsHeightToZeroListInSystem() {
//        FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST = ActivityManager.getFreeformCaptionInsetsHeightToZeroList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformCaptionInsetsHeightToZeroListInSystem::FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST = " + FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST);
        return FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST;
    }

    public static List<String> getFreeformCaptionInsetsHeightToZeroListFromCloud(Context context) {
        List<String> freeformCaptionInsetsHeightToZeroList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformCaptionInsetsHeightToZeroListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    freeformCaptionInsetsHeightToZeroList.add(apps.getString(i2));
//                }
//            } else {
//                freeformCaptionInsetsHeightToZeroList.addAll(FREEFORM_CAPTION_INSETS_HEIGHT_TO_ZERO_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFreeformCaptionInsetsHeightToZeroListFromCloud::freeformCaptionInsetsHeightToZeroList = " + freeformCaptionInsetsHeightToZeroList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFreeformCaptionInsetsHeightToZeroListFromCloud :", e2);
        }
        return freeformCaptionInsetsHeightToZeroList;
    }

    public static void setForceLandscapeApplication(List<String> forceLandscapeApplication) {
        if (forceLandscapeApplication != null) {
            synchronized (FORCE_LANDSCAPE_APPLICATION) {
                FORCE_LANDSCAPE_APPLICATION.clear();
                FORCE_LANDSCAPE_APPLICATION.addAll(forceLandscapeApplication);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setForceLandscapeApplication::forceLandscapeApplication = " + forceLandscapeApplication);
    }

    public static List<String> getForceLandscapeApplication() {
        List<String> result;
        synchronized (FORCE_LANDSCAPE_APPLICATION) {
            result = new ArrayList<>();
            result.addAll(FORCE_LANDSCAPE_APPLICATION);
            List<String> additionalFreeformAspectRatio1Apps = getAdditionalFreeformAspectRatio1Apps();
            result.addAll(additionalFreeformAspectRatio1Apps);
            if (IS_DEBUG_BUILD) {
                Log.d(TAG, "MiuiMultiWindowAdapter::getForceLandscapeApplication::FORCE_LANDSCAPE_APPLICATION = " + FORCE_LANDSCAPE_APPLICATION + " result=" + result);
            }
        }
        return result;
    }

    public static List<String> getForceLandscapeApplicationInSystem() {
//        Log.d(TAG, "MiuiMultiWindowAdapter::getForceLandscapeApplicationInSystem:: " + ActivityManager.getForceLandscapeApplication());
//        return ActivityManager.getForceLandscapeApplication();
        return null;
    }

    public static List<String> getForceLandscapeApplicationFromCloud(Context context) {
        List<String> forceLandscapeApplication = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FORCE_LANDSCAPE_APPLICATION_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getForceLandscapeApplicationFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    forceLandscapeApplication.add(apps.getString(i2));
//                }
//            } else {
//                forceLandscapeApplication.addAll(FORCE_LANDSCAPE_APPLICATION);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getForceLandscapeApplicationFromCloud::forceLandscapeApplication = " + forceLandscapeApplication);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getForceLandscapeApplicationFromCloud :", e2);
        }
        return forceLandscapeApplication;
    }

    public static void setTopGameList(List<String> topGameList) {
        if (topGameList != null) {
            synchronized (LIST_TOP_GAME) {
                LIST_TOP_GAME.clear();
                LIST_TOP_GAME.addAll(topGameList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setTopGameList::topGameList = " + topGameList);
    }

    public static List<String> getTopGameList() {
        List<String> list;
        synchronized (LIST_TOP_GAME) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getTopGameList::LIST_TOP_GAME = " + LIST_TOP_GAME);
            list = LIST_TOP_GAME;
        }
        return list;
    }

    public static List<String> getTopGameListInSystem() {
//        LIST_TOP_GAME = ActivityManager.getTopGameList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getTopGameListInSystem::LIST_TOP_GAME = " + LIST_TOP_GAME);
        return LIST_TOP_GAME;
    }

    public static List<String> getTopGameListFromCloud(Context context) {
        List<String> listTopGame = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_TOP_GAME_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getTopGameListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    listTopGame.add(apps.getString(i2));
//                }
//            } else {
//                listTopGame.addAll(LIST_TOP_GAME);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getTopGameListFromCloud::listTopGame = " + listTopGame);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getTopGameListFromCloud :", e2);
        }
        return listTopGame;
    }

    public static void setTopVideoList(List<String> topVideoList) {
        if (topVideoList != null) {
            synchronized (LIST_TOP_VIDEO) {
                LIST_TOP_VIDEO.clear();
                LIST_TOP_VIDEO.addAll(topVideoList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setTopVideoList::topVideoList = " + topVideoList);
    }

    public static List<String> getTopVideoList() {
        List<String> list;
        synchronized (LIST_TOP_VIDEO) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getTopVideoList::LIST_TOP_VIDEO = " + LIST_TOP_VIDEO);
            list = LIST_TOP_VIDEO;
        }
        return list;
    }

    public static List<String> getTopVideoListInSystem() {
//        LIST_TOP_VIDEO = ActivityManager.getTopVideoList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getTopVideoListInSystem::LIST_TOP_VIDEO = " + LIST_TOP_VIDEO);
        return LIST_TOP_VIDEO;
    }

    public static List<String> getTopVideoListFromCloud(Context context) {
        List<String> listTopVideo = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_TOP_VIDEO_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getTopVideoListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    listTopVideo.add(apps.getString(i2));
//                }
//            } else {
//                listTopVideo.addAll(LIST_TOP_VIDEO);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getTopVideoListFromCloud::listTopVideo = " + listTopVideo);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getTopVideoListFromCloud :", e2);
        }
        return listTopVideo;
    }

    public static void setEnableForegroundPin(boolean enableForegroundPin) {
        ENABLE_FOREGROUND_PIN = enableForegroundPin;
        Log.d(TAG, "MiuiMultiWindowAdapter::setEnableForeground::enableForegroundPin = " + enableForegroundPin);
    }

    public static boolean getEnableForegroundPin() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getEnableForegroundPin::enableForeground = " + ENABLE_FOREGROUND_PIN);
        return ENABLE_FOREGROUND_PIN;
    }

    public static boolean getEnableForegroundPinFromCloud(Context context) {
        boolean enableForegroundPin = false;
        try {
//            enableForegroundPin = MiuiSettings.SettingsCloudData.getCloudDataBoolean(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_ENABLE_FOREGROUND_PIN, ENABLE_FOREGROUND_PIN);
            Log.d(TAG, "MiuiMultiWindowAdapter::getEnableForegroundPinFromCloud::enableForegroundPin = " + enableForegroundPin);
            return enableForegroundPin;
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getEnableForegroundPinFromCloud :", e2);
            return enableForegroundPin;
        }
    }

    public static void setAudioForegroundPinAppList(List<String> audioForegroundPinAppList) {
        if (audioForegroundPinAppList != null) {
            synchronized (AUDIO_FOREGROUND_PIN_APP_LIST) {
                AUDIO_FOREGROUND_PIN_APP_LIST.clear();
                AUDIO_FOREGROUND_PIN_APP_LIST.addAll(audioForegroundPinAppList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setAudioForegroundPinAppList::audioForegroundPinAppList = " + audioForegroundPinAppList);
    }

    public static List<String> getAudioForegroundPinAppList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getAudioForegroundPinAppList::AUDIO_FOREGROUND_PIN_APP_LIST = " + AUDIO_FOREGROUND_PIN_APP_LIST);
        return AUDIO_FOREGROUND_PIN_APP_LIST;
    }

    public static boolean isInAudioForegroundPinAppList(String packageName) {
        return AUDIO_FOREGROUND_PIN_APP_LIST.contains(packageName);
    }

    public static List<String> getAudioForegroundPinAppListFromCloud(Context context) {
        List<String> audioForegroundPinAppList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_AUDIO_FOREGROUND_PIN_APP_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getAudioForegroundPinAppListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    audioForegroundPinAppList.add(apps.getString(i2));
//                }
//            } else {
//                audioForegroundPinAppList.addAll(AUDIO_FOREGROUND_PIN_APP_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getAudioForegroundPinAppListFromCloud::audioForegroundPinAppList = " + audioForegroundPinAppList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getAudioForegroundPinAppListFromCloud :", e2);
        }
        return audioForegroundPinAppList;
    }

    public static void setForegroundPinAppWhiteList(List<String> foregroundPinAppWhiteList) {
        if (foregroundPinAppWhiteList != null) {
            synchronized (FOREGROUND_PIN_APP_WHITE_LIST) {
                FOREGROUND_PIN_APP_WHITE_LIST.clear();
                FOREGROUND_PIN_APP_WHITE_LIST.addAll(foregroundPinAppWhiteList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setForegroundPinAppWhiteList::foregroundPinAppWhiteList = " + foregroundPinAppWhiteList);
    }

    public static List<String> getForegroundPinAppWhiteList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getForegroundPinAppWhiteList::FOREGROUND_PIN_APP_WHITE_LIST = " + FOREGROUND_PIN_APP_WHITE_LIST);
        return FOREGROUND_PIN_APP_WHITE_LIST;
    }

    public static boolean isInForegroundPinAppWhiteList(String packageName) {
        return FOREGROUND_PIN_APP_WHITE_LIST.contains(packageName);
    }

    public static List<String> getForegroundPinAppWhiteListFromCloud(Context context) {
        List<String> foregroundPinAppWhiteList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FOREGROUND_PIN_APP_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getForegroundPinAppWhiteList::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    foregroundPinAppWhiteList.add(apps.getString(i2));
//                }
//            } else {
//                foregroundPinAppWhiteList.addAll(FOREGROUND_PIN_APP_WHITE_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getForegroundPinAppWhiteListFromCloud::ForegroundPinAppWhiteList = " + foregroundPinAppWhiteList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getForegroundPinAppWhiteListFromCloud :", e2);
        }
        return foregroundPinAppWhiteList;
    }

    public static void setForegroundPinAppBlackList(List<String> foregroundPinAppBlackList) {
        if (foregroundPinAppBlackList != null) {
            synchronized (FOREGROUND_PIN_APP_BLACK_LIST) {
                FOREGROUND_PIN_APP_BLACK_LIST.clear();
                FOREGROUND_PIN_APP_BLACK_LIST.addAll(foregroundPinAppBlackList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setForegroundPinAppBlackList::foregroundPinAppBlackList = " + foregroundPinAppBlackList);
    }

    public static List<String> getForegroundPinAppBlackList() {
        Log.d(TAG, "MiuiMultiWindowAdapter::getForegroundPinAppBlackList::FOREGROUND_PIN_APP_BLACK_LIST = " + FOREGROUND_PIN_APP_BLACK_LIST);
        return FOREGROUND_PIN_APP_BLACK_LIST;
    }

    public static boolean isInForegroundPinAppBlackList(String packageName) {
        return FOREGROUND_PIN_APP_BLACK_LIST.contains(packageName);
    }

    public static List<String> getForegroundPinAppBlackListFromCloud(Context context) {
        List<String> foregroundPinAppBlackList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_FOREGROUND_PIN_APP_BLACK_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getForegroundPinAppBlackList::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    foregroundPinAppBlackList.add(apps.getString(i2));
//                }
//            } else {
//                foregroundPinAppBlackList.addAll(FOREGROUND_PIN_APP_BLACK_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getForegroundPinAppBlackListFromCloud::ForegroundPinAppBlackList = " + foregroundPinAppBlackList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getForegroundPinAppBlackListFromCloud :", e2);
        }
        return foregroundPinAppBlackList;
    }

    public static void setFixedRotationAppList(List<String> fixedRotationAppList) {
        if (fixedRotationAppList != null) {
            synchronized (GET_FIXED_ROTATION_APP_LIST) {
                GET_FIXED_ROTATION_APP_LIST.clear();
                GET_FIXED_ROTATION_APP_LIST.addAll(fixedRotationAppList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setFixedRotationAppList::fixedRotationAppList = " + fixedRotationAppList);
    }

    public static List<String> getFixedRotationAppList() {
        List<String> list;
        synchronized (GET_FIXED_ROTATION_APP_LIST) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getFixedRotationAppList::GET_FIXED_ROTATION_APP_LIST = " + GET_FIXED_ROTATION_APP_LIST);
            list = GET_FIXED_ROTATION_APP_LIST;
        }
        return list;
    }

    public static List<String> getFixedRotationAppListInSystem() {
//        GET_FIXED_ROTATION_APP_LIST = ActivityManager.getFixedRotationAppList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getFixedRotationAppListInSystem::GET_FIXED_ROTATION_APP_LIST = " + GET_FIXED_ROTATION_APP_LIST);
        return GET_FIXED_ROTATION_APP_LIST;
    }

    public static List<String> getFixedRotationAppListFromCloud(Context context) {
        List<String> fixedRotationAppList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_GET_FIXED_ROTATION_APP_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getFixedRotationAppListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    fixedRotationAppList.add(apps.getString(i2));
//                }
//            } else {
//                fixedRotationAppList.addAll(GET_FIXED_ROTATION_APP_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getFixedRotationAppListFromCloud::fixedRotationAppList = " + fixedRotationAppList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getFixedRotationAppListFromCloud :", e2);
        }
        return fixedRotationAppList;
    }

    public static void setRotationFromDisplayApp(List<String> rotationFromDisplayApp) {
        if (rotationFromDisplayApp != null) {
            synchronized (GET_ROTATION_FROM_DISPLAY_APP) {
                GET_ROTATION_FROM_DISPLAY_APP.clear();
                GET_ROTATION_FROM_DISPLAY_APP.addAll(rotationFromDisplayApp);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setRotationFromDisplayApp::rotationFromDisplayApp = " + rotationFromDisplayApp);
    }

    public static List<String> getRotationFromDisplayApp() {
        List<String> list;
        synchronized (GET_ROTATION_FROM_DISPLAY_APP) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getRotationFromDisplayApp::GET_ROTATION_FROM_DISPLAY_APP = " + GET_ROTATION_FROM_DISPLAY_APP);
            list = GET_ROTATION_FROM_DISPLAY_APP;
        }
        return list;
    }

    public static List<String> getRotationFromDisplayAppInSystem() {
//        GET_ROTATION_FROM_DISPLAY_APP = ActivityManager.getRotationFromDisplayApp();
        Log.d(TAG, "MiuiMultiWindowAdapter::getRotationFromDisplayAppInSystem::GET_ROTATION_FROM_DISPLAY_APP = " + GET_ROTATION_FROM_DISPLAY_APP);
        return GET_ROTATION_FROM_DISPLAY_APP;
    }

    public static List<String> getRotationFromDisplayAppFromCloud(Context context) {
        List<String> rotationFromDisplayApp = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_GET_ROTATION_FROM_DISPLAY_APP_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getRotationFromDisplayAppFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    rotationFromDisplayApp.add(apps.getString(i2));
//                }
//            } else {
//                rotationFromDisplayApp.addAll(GET_ROTATION_FROM_DISPLAY_APP);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getRotationFromDisplayAppFromCloud::rotationFromDisplayApp = " + rotationFromDisplayApp);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getRotationFromDisplayAppFromCloud :", e2);
        }
        return rotationFromDisplayApp;
    }

    public static void setUseDefaultCameraPipelineApp(List<String> useDefaultCameraPipelineApp) {
        if (useDefaultCameraPipelineApp != null) {
            synchronized (USE_DEFAULT_CAMERA_PIPELINE_APP) {
                USE_DEFAULT_CAMERA_PIPELINE_APP.clear();
                USE_DEFAULT_CAMERA_PIPELINE_APP.addAll(useDefaultCameraPipelineApp);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setUseDefaultCameraPipelineApp::useDefaultCameraPipelineApp = " + useDefaultCameraPipelineApp);
    }

    public static List<String> getUseDefaultCameraPipelineApp() {
        List<String> list;
        synchronized (USE_DEFAULT_CAMERA_PIPELINE_APP) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getUseDefaultCameraPipelineApp::USE_DEFAULT_CAMERA_PIPELINE_APP = " + USE_DEFAULT_CAMERA_PIPELINE_APP);
            list = USE_DEFAULT_CAMERA_PIPELINE_APP;
        }
        return list;
    }

    public static List<String> getUseDefaultCameraPipelineAppFromCloud(Context context) {
        List<String> useDefaultCameraPipelineApp = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_USE_DEFAULT_CAMERA_PIPELINE_APP_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getUseDefaultCameraPipelineAppFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    useDefaultCameraPipelineApp.add(apps.getString(i2));
//                }
//            } else {
//                useDefaultCameraPipelineApp.addAll(USE_DEFAULT_CAMERA_PIPELINE_APP);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getUseDefaultCameraPipelineAppFromCloud::useDefaultCameraPipelineApp = " + useDefaultCameraPipelineApp);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getUseDefaultCameraPipelineAppFromCloud :", e2);
        }
        return useDefaultCameraPipelineApp;
    }

    public static void setSensorDisableWhiteList(List<String> sensorDisableWhiteList) {
        if (sensorDisableWhiteList != null) {
            synchronized (SENSOR_DISABLE_WHITE_LIST) {
                SENSOR_DISABLE_WHITE_LIST.clear();
                SENSOR_DISABLE_WHITE_LIST.addAll(sensorDisableWhiteList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setSensorDisableWhiteList::sensorDisableWhiteList = " + sensorDisableWhiteList);
    }

    public static List<String> getSensorDisableWhiteList() {
        List<String> list;
        synchronized (SENSOR_DISABLE_WHITE_LIST) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableWhiteList::SENSOR_DISABLE_WHITE_LIST = " + SENSOR_DISABLE_WHITE_LIST);
            list = SENSOR_DISABLE_WHITE_LIST;
        }
        return list;
    }

    public static List<String> getSensorDisableList() {
        List<String> list;
        synchronized (SENSOR_DISABLE_LIST) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableList::SENSOR_DISABLE_LIST = " + SENSOR_DISABLE_LIST);
            list = SENSOR_DISABLE_LIST;
        }
        return list;
    }

    public static List<String> getSensorDisableListInSystem() {
//        SENSOR_DISABLE_LIST = ActivityManager.getSensorDisableList();
        Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableListInSystem::GET_ROTATION_FROM_DISPLAY_APP = " + SENSOR_DISABLE_LIST);
        return SENSOR_DISABLE_LIST;
    }

    public static List<String> getSensorDisableWhiteListFromCloud(Context context) {
        List<String> sensorDisableWhiteList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_SENSOR_DISABLE_WHITE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableWhiteListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    sensorDisableWhiteList.add(apps.getString(i2));
//                }
//            } else {
//                sensorDisableWhiteList.addAll(SENSOR_DISABLE_WHITE_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableWhiteListFromCloud::sensorDisableWhiteList = " + sensorDisableWhiteList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getSensorDisableWhiteListFromCloud :", e2);
        }
        return sensorDisableWhiteList;
    }

    public static void setLaunchInTaskList(List<String> launchInTaskList) {
        if (launchInTaskList != null) {
            synchronized (LIST_LAUNCH_IN_TASK) {
                LIST_LAUNCH_IN_TASK.clear();
                LIST_LAUNCH_IN_TASK.addAll(launchInTaskList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setLaunchInTaskList::launchInTaskList = " + launchInTaskList);
    }

    public static List<String> getLaunchInTaskList() {
        List<String> list;
        synchronized (LIST_LAUNCH_IN_TASK) {
            Log.d(TAG, "MiuiMultiWindowAdapter::getLaunchInTaskList::LIST_LAUNCH_IN_TASK = " + LIST_LAUNCH_IN_TASK);
            list = LIST_LAUNCH_IN_TASK;
        }
        return list;
    }

    public static List<String> getLaunchInTaskListFromCloud(Context context) {
        List<String> launchInTaskList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_LAUNCH_IN_TASK_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getLaunchInTaskListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    launchInTaskList.add(apps.getString(i2));
//                }
//            } else {
//                launchInTaskList.addAll(LIST_LAUNCH_IN_TASK);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getLaunchInTaskListFromCloud::launchInTaskList = " + launchInTaskList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getLaunchInTaskListFromCloud :", e2);
        }
        return launchInTaskList;
    }

    public static void setEnableAbnormalFreeFormDebug(int enableAbnormalFreeFormDebug) {
        synchronized (lockForEnableAbnormalFreeFormDebug) {
            try {
                int res = Settings.Secure.getInt(ActivityThread.currentApplication().getContentResolver(), "enable_debug_mode_for_abnormal_freeform", -1);
                if (res == -1) {
                    sEnableAbnormalFreeFormDebug = Integer.valueOf(enableAbnormalFreeFormDebug);
                    Log.d(TAG, "MiuiMultiWindowAdapter::setEnableAbnormalFreeFormDebug::enableAbnormalFreeFormDebug = " + enableAbnormalFreeFormDebug + " override");
                } else {
                    Log.d(TAG, "MiuiMultiWindowAdapter::setEnableAbnormalFreeFormDebug::enableAbnormalFreeFormDebug = " + enableAbnormalFreeFormDebug + " failed");
                }
            } catch (Exception e2) {
                Log.e(TAG, "getEnableAbnormalFreeFormDebug: Exception Message: " + e2.getCause());
            }
        }
    }

    public static int getEnableAbnormalFreeFormDebug(boolean fromSystem) {
        int intValue;
        if (fromSystem) {
            synchronized (lockForEnableAbnormalFreeFormDebug) {
                if (sEnableAbnormalFreeFormDebug == null) {
                    try {
                        int res = Settings.Secure.getInt(ActivityThread.currentApplication().getContentResolver(), "enable_debug_mode_for_abnormal_freeform", -1);
                        Log.d(TAG, "MiuiMultiWindowAdapter::getEnableAbnormalFreeFormDebug::res = " + res);
                        sEnableAbnormalFreeFormDebug = Integer.valueOf(res);
                    } catch (Exception e2) {
                        Log.e(TAG, "getEnableAbnormalFreeFormDebug: Exception Message: " + e2.getCause());
                    }
                }
                Log.d(TAG, "MiuiMultiWindowAdapter::getEnableAbnormalFreeFormDebug::sEnableAbnormalFreeFormDebug = " + sEnableAbnormalFreeFormDebug);
                intValue = sEnableAbnormalFreeFormDebug.intValue();
            }
            return intValue;
        }
//        return ActivityManager.getEnableAbnormalFreeFormDebug(true);
        return -1;
    }

    public static int getEnableAbnormalFreeFormDebugFromCloud(Context context) {
        int enableAbnormalFreeFormDebug = -1;
        try {
//            int data = MiuiSettings.SettingsCloudData.getCloudDataInt(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, "enable_debug_mode_for_abnormal_freeform", -1);
//            enableAbnormalFreeFormDebug = data;
            Log.d(TAG, "MiuiMultiWindowAdapter::getEnableAbnormalFreeFormDebugFromCloud::enableAbnormalFreeFormDebug = " + enableAbnormalFreeFormDebug);
            return enableAbnormalFreeFormDebug;
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getEnableAbnormalFreeFormDebugFromCloud :", e2);
            return enableAbnormalFreeFormDebug;
        }
    }

    public static void setSensorDisableList(List<String> sensorDisableList) {
        if (sensorDisableList != null) {
            synchronized (SENSOR_DISABLE_LIST) {
                SENSOR_DISABLE_LIST.clear();
                SENSOR_DISABLE_LIST.addAll(sensorDisableList);
            }
        }
        Log.d(TAG, "MiuiMultiWindowAdapter::setSensorDisableList::sensorDisableList = " + sensorDisableList);
    }

    public static List<String> getSensorDisableListFromCloud(Context context) {
        List<String> sensorDisableList = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, SMALL_WINDOW_SENSOR_DISABLE_LIST, null);
//            Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableListFromCloud::data = " + data);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    sensorDisableList.add(apps.getString(i2));
//                }
//            } else {
//                sensorDisableList.addAll(SENSOR_DISABLE_LIST);
//            }
            Log.d(TAG, "MiuiMultiWindowAdapter::getSensorDisableListFromCloud::sensorDisableList = " + sensorDisableList);
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get getSensorDisableListFromCloud :", e2);
        }
        return sensorDisableList;
    }

    public static boolean isOverlayWithDecorCaptionEnabledPolicy(ComponentName compName, boolean overlayWithAppContent) {
        if (compName != null) {
            List<String> freeformDisableOverlayListInSystem = getFreeformDisableOverlayListInSystem();
            LIST_DISABLE_OVERLAY_WITH_APP_CONTENT = freeformDisableOverlayListInSystem;
            if (freeformDisableOverlayListInSystem.contains(compName.getPackageName()) || LIST_DISABLE_OVERLAY_WITH_APP_CONTENT.contains(compName.getClassName())) {
                return false;
            }
            return overlayWithAppContent;
        }
        return overlayWithAppContent;
    }

    public static boolean checkReadyForDispatch(int sensorType, SensorEvent t2) {
        ActivityThread thread = ActivityThread.currentActivityThread();
//        if (thread == null || !thread.inFreeForm() || t2 == null) {
//            return true;
//        }
        switch (sensorType) {
            case 1:
            case 9:
                if (!mSensorDisableListUpdated) {
                    getSensorDisableListInSystem();
                    mSensorDisableListUpdated = true;
                }
                if (!SENSOR_DISABLE_LIST.contains(ActivityThread.currentPackageName())) {
                    return true;
                }
                return false;
            case 8:
                return false;
            default:
                return true;
        }
    }

    public static boolean interceptedForFreeForm(View view) {
        if (DEBUG_DISABLE_REVERT_LOCATION_FOR_FREEFORM || view == null || view.getClass() == null) {
            return false;
        }
        return LIST_REVERT_LOCATION_IN_FREEFORM.contains(view.getClass().toString());
    }

    public static List<String> getListFromCloud(Context context, String key, List<String> cloudList) {
        List<String> list = new ArrayList<>();
        try {
//            String data = MiuiSettings.SettingsCloudData.getCloudDataString(context.getContentResolver(), SMALL_WINDOW_MODULE_NAME, key, null);
//            if (!TextUtils.isEmpty(data)) {
//                JSONArray apps = new JSONArray(data);
//                for (int i2 = 0; i2 < apps.length(); i2++) {
//                    list.add(apps.getString(i2));
//                }
//            } else {
//                list.addAll(cloudList);
//            }
        } catch (Exception e2) {
            Log.e(TAG, "Exception when get CloudData :", e2);
        }
        return list;
    }
}
