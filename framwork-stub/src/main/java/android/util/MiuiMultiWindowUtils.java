package android.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.view.Display;
import android.view.IWindowManager;
import android.view.InsetsState;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.animation.Interpolator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import miui.app.MiuiFreeFormManager;
import miui.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MiuiMultiWindowUtils {
    public static final float ACCESSABLE_MARGIN_DIP;
    public static final float ACCESSABLE_MARGIN_DIP_PAD;
    public static final String ACTIVITY_FOR_DOUBLE_APPLICATION = "com.miui.securitycore/com.miui.xspace.ui.activity.XSpaceResolveActivity";
    public static final int ACTIVITY_ORIENTATION_LANDSCAPE = 1;
    public static final int ACTIVITY_ORIENTATION_PORTRAIT = 0;
    public static final int ACTIVITY_ORIENTATION_UNSET = -1;
    public static final float ADDITIONAL_FREEFORM_ASPECT_RATIO_1 = 1.333333f;
    public static final float ADDITIONAL_FREEFORM_ASPECT_RATIO_2 = 0.75f;
    private static final Map<Float, int[]> ADDITIONAL_FREEFORM_RESOLUTIONS;
    private static final int[] ADDITIONAL_FREEFORM_RESOLUTION_1;
    private static final int[] ADDITIONAL_FREEFORM_RESOLUTION_2;
    public static final int ALREADY_IN_SMALL_FREEFORM = 3;
    public static final String APPLICATION_LOCK_NAME = "com.miui.securitycenter/com.miui.applicationlock.ConfirmAccessControl";
    private static final int ASPECT_RATIO_ARG = 0;
    public static final int AVOIDING_STRATEGY_APPLIED_MAX_FREEFORM_COUNT = 2;
    static final int BACKGROUND_START_SWITCH_DEFAULT_STATE = 0;
    public static final int BOTTOM_BAR_HEIGHT = 54;
    public static final int BOTTOM_BAR_HEIGHT_PAD = 45;
    public static final int BOTTOM_DECOR_CAPTIONVIEW_HEIGHT;
    public static final int BOTTOM_DOT_BOTTOM_MARGIN = 22;
    public static final int BOTTOM_DOT_HORIZONTAL_MARGIN = 28;
    public static final int BOUNDS_LEFT_UNSET = -1;
    public static final int BOUNDS_TOP_UNSET = -1;
    private static final int BUFFE_READER_SIZE = 8192;
    public static final String CAMERA_ONESHORT_NAME = "com.android.camera/.OneShotCamera";
    public static final String DEBUG_MODE_FOR_ABNORMAL_FREEFORM = "enable_debug_mode_for_abnormal_freeform";
    public static final String DEBUG_MODE_FOR_FREEFORM_BLACKLIST = "enable_debug_mode_for_freeform_black_list";
    public static final float DECOR_CAPTIONVIEW_HEIGHT = 36.36f;
    public static List<String> DEVICES_SUPPORT_MULTI_FREEFORM = null;
    public static final int DISABLE_MODE_FOR_ABNORMAL_FREEFORM = 0;
    public static final String DISABLE_REVERT_LOCATION_FOR_FREEFORM = "persist.sys.disable_revert_location";
    public static final float EDGE_FRICTION = 3.0f;
    public static final boolean ENABLE_KEEP_FREEFORM_STATE = false;
    static final boolean ENABLE_WHITELIST = false;
    static final String FLASHBACK_BACKGROUND_START_SWITCH = "FlashBackBackgroundStartSwitch";
    private static final int FLASHBACK_GET_TASK_NUM = 50;
    public static final int FLASHBACK_LAUNCH_FROM_BACKGROUND = 4;
    private static final String FORCE_FSG_NAV_BAR = "force_fsg_nav_bar";
    public static final int FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN;
    public static final int FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_CENTER_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_CENTER_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_RADIUS;
    public static final float FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_RADIUS;
    public static final float FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_RADIUS;
    public static final float FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_RADIUS;
    public static final float FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN;
    public static final int FREEFORM_HOTSPOT_LEFT_BOTTOM = 3;
    public static final int FREEFORM_HOTSPOT_LEFT_TOP = 1;
    public static final int FREEFORM_HOTSPOT_NONE = -1;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_RADIUS;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_RADIUS_FOLD;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_BOTTOM_MARGIN_FOLD;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN_FOLD;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_RADIUS;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_RADIUS_FOLD;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_BOTTOM_MARGIN_FOLD;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN_FLOD;
    public static final int FREEFORM_HOTSPOT_RIGHT_BOTTOM = 4;
    public static final int FREEFORM_HOTSPOT_RIGHT_TOP = 2;
    public static final int FREEFORM_HOTSPOT_TOP_CENTER = 5;
    public static final float FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_RADIUS;
    public static final float FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_RADIUS;
    public static final float FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_BOTTOM_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN;
    public static final float FREEFORM_HOTSPOT_TRIGGER_TOP_MARGIN = 80.0f;
    public static final float FREEFORM_HOT_MARGIN;
    public static final float FREEFORM_LANDCAPE_LANDCAPE_RESERVE_MARGINS;
    public static final float FREEFORM_LANDCAPE_RESERVE_MARGINS;
    public static final String FREEFORM_PACKAGE_NAME = "freeform_package_name";
    public static final int FREEFORM_RECT_OFFSET_X_ZIZHAN;
    public static final int FREEFORM_RECT_OFFSET_Y_ZIZHAN;
    private static final List<Map<Integer, Float>> FREEFORM_RESOLUTION_ARGS;
    private static JSONObject FREEFORM_RESOLUTION_ARGS_ALL = null;
    private static final List<Map<Integer, Float>> FREEFORM_RESOLUTION_ARGS_INNER;
    private static JSONObject FREEFORM_RESOLUTION_ARGS_OF_DEVICE = null;
    private static final List<Map<Integer, Float>> FREEFORM_RESOLUTION_ARGS_OUTER;
    public static final float FREEFORM_ROUND_CORNER_DIP = 25.8f;
    public static final float FREEFORM_ROUND_CORNER_DIP_MIUI15;
    public static final String FREEFORM_WINDOW_CHANGED_SCALE = "freeform_window_changed_scale";
    public static final String FREEFORM_WINDOW_SCALE = "freeform_scale";
    public static final float FRICTION = 1.2f;
    public static final String GLOBAL_LAUNCHER_PKG_NAME = "com.mi.android.globallauncher";
    private static final String HIDE_GESTURE_LINE = "hide_gesture_line";
    public static final int HOT_SPACE_BOTTOM_CAPTION = 1;
    public static final int HOT_SPACE_BOTTOM_CAPTION_DOWNWARDS_OFFSET = 35;
    public static final int HOT_SPACE_BOTTOM_CAPTION_UPWARDS_OFFSET = 10;
    public static final int HOT_SPACE_BOTTOM_OFFSITE_PAD;
    public static final int HOT_SPACE_CVW_LEFT_RESIZE = 4;
    public static final int HOT_SPACE_CVW_RIGHT_RESIZE = 5;
    public static final int HOT_SPACE_LEFT_RESIZE_REGION = 2;
    public static final int HOT_SPACE_OFFSITE;
    public static final int HOT_SPACE_RESIZE_CENTER_HORIZONTAL_MARGIN = 33;
    public static final int HOT_SPACE_RESIZE_CENTER_VERTICAL_MARGIN = 33;
    public static final int HOT_SPACE_RESIZE_CENTRAL_MARGIN_PAD = 24;
    public static final int HOT_SPACE_RESIZE_HEIGHT = 132;
    public static final int HOT_SPACE_RESIZE_OFFSET_PAD = 33;
    public static final int HOT_SPACE_RESIZE_RADIUS = 50;
    public static final int HOT_SPACE_RESIZE_WIDTH = 132;
    public static final int HOT_SPACE_RIGHT_RESIZE_REGION = 3;
    public static final int HOT_SPACE_TOP_CAPTION = 0;
    public static final int HOT_SPACE_TOP_CAPTION_DOWNWARDS_OFFSET = 18;
    public static final int HOT_SPACE_TOP_CAPTION_UPWARDS_OFFSET = 22;
    public static final float HOT_SPATE_TOP_CAPTION_WIDTH_RATIO = 0.745f;
    public static final String INTERNAL_DISPLAY_ACTIVITY_PKG_NAME = "com.xiaomi.misubscreenui";
    public static final int INTERNAL_DISPLAY_ID = 2;
    public static boolean IS_FOLD_SCREEN_DEVICE = false;
    public static final String KEY_APPLICATION_USED_FREEFORM = "application_used_freeform";
    public static final String KEY_FIRST_USE_FREEFORM = "first_use_freeform";
    public static final String KEY_FIRST_USE_TIP_CONFIRM_TIMES = "first_use_tip_confirm_times";
    public static final String KEY_FREEFORM_TIMESTAMPS = "freeform_timestamps";
    public static final String KEY_FREEFORM_WINDOW_STATE = "freeform_window_state";
    public static final String KEY_QUICKREPLY = "quick_reply";
    public static final float LANDCAPE_LANDCAPE_MAX_WIDTH_FOR_ORIGIN_WIDTH = 1.2796443f;
    public static final float LANDCAPE_LANDCAPE_MIN_WIDTH_FOR_ORIGIN_WIDTH = 0.71146244f;
    public static final float LANDCAPE_PORTRAIT_MAX_WIDTH_FOR_ORIGIN_WIDTH = 1.0f;
    public static final float LANDCAPE_PORTRAIT_MIN_WIDTH_FOR_ORIGIN_WIDTH = 0.71146244f;
    public static final int LANDCAPE_SMALL_FREEFORM_LANDCAPE_HORIZONTAL_MARGIN;
    public static final int LANDCAPE_SMALL_FREEFORM_LANDCAPE_VERTICAL_MARGIN;
    public static final int LANDCAPE_SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN;
    public static final int LANDCAPE_SMALL_FREEFORM_PORTRAIT_VERTICAL_MARGIN;
    public static final float LANDSCAPE_CVW_NEW_FREEFORM_HEIGHT_DIP_PAD;
    public static final float LANDSCAPE_CVW_NEW_FREEFORM_WIDTH_DIP_PAD;
    private static final int LEFT_MARGIN_ARG = 4;
    public static final int LEVEL_FULL = 1;
    public static final int LEVEL_HORIZONTAL = 3;
    public static final int LEVEL_NULL = 0;
    public static final int LEVEL_VERTICAL = 2;
    public static int LONG_WAIT = 0;
    public static int LONG_WAIT_TOAST = 0;
    public static final float MAX_LANDCAPE_WIDTH_FOR_ORIGIN_WIDTH = 1.0f;
    public static final float MAX_LANDCAPE_WIDTH_FOR_ORIGIN_WIDTH_FOLD = 1.3007915f;
    public static final float MAX_PORTRAIT_WIDTH_FOR_ORIGIN_WIDTH = 1.3007915f;
    public static final int MAX_WAITING_TIME_ON_CLICK = 1500;
    private static final int MINI_FREEFORM_HEIGHT_RATIO = 0;
    public static final int MINI_FREEFORM_PADDING_STROKE = 20;
    private static final List<Map<Integer, Float>> MINI_FREEFORM_RESOLUTION_ARGS;
    private static final List<Map<Integer, Float>> MINI_FREEFORM_RESOLUTION_ARGS_INNER;
    private static final List<Map<Integer, Float>> MINI_FREEFORM_RESOLUTION_ARGS_OUTER;
    public static final float MINI_FREEFORM_ROUND_CORNER_DIP_MIUI15;
    public static final float MIN_LANDCAPE_WIDTH_FOR_ORIGIN_WIDTH = 0.7259475f;
    public static final float MIN_PORTRAIT_WIDTH_FOR_ORIGIN_WIDTH = 0.6569921f;
    public static final int MIUI_BOUNDARY_SPATIAL = 0;
    public static final float[] MIUI_FREEFORM_AMBIENT_COLOR;
    public static final float[] MIUI_FREEFORM_RESET_COLOR;
    public static final float MIUI_FREEFORM_SHADOW_RADIUS = 400.0f;
    public static final boolean MIUI_FREEFORM_SHADOW_V2_SUPPORTED;
    public static final float MIUI_FRICTION = 0.8f;
    public static final String MONKEY_PACKAGE_NAME = "com.android.commands.monkey";
    public static final String MTBF_PACKAGE_NAME = "com.phonetest.stresstest";
    public static volatile boolean MULTIWIN_SWITCH_ENABLED = false;
    public static boolean MULTI_WINDOW_SWITCH_ENABLED = false;
    public static final int NARROW_SCREEN = 0;
    public static final int NORMAL_MODE_FOR_ABNORMAL_FREEFORM = 1;
    public static final int NOTCH_MARGIN;
    public static final int NO_AVALIABLE_APPLICATION = 2;
    private static final int ORIGINAL_CUT_RATIO_ARG = 1;
    private static final int ORIGINAL_SCALE_ARG = 2;
    public static final int PAD_BOTTOM_BAR_TOUCH_REGION_MARGIN = 126;
    public static final int PAD_FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN;
    public static final int PAD_FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN_DESKTOP;
    public static final int PAD_FREEFORM_ACCESSIBLE_AREA_HORIZONTAL_MARGIN;
    public static final float PAD_FREEFORM_ROUND_CORNER_DIP = 16.9f;
    private static final float PAD_LANDSCAPE_FREEFORM_HEIGHT_RATIO = 0.8f;
    private static final float PAD_LANDSCAPE_MINI_FREEFORM_HEIGHT_RATIO = 0.26f;
    public static final int PAD_MULTI_FREEFORM_DEFAULT_MARGIN;
    private static final float PAD_PORTRAIT_FREEFORM_HEIGHT_RATIO = 0.92f;
    private static final float PAD_PORTRAIT_MINI_FREEFORM_HEIGHT_RATIO = 0.3f;
    private static final float PAD_RIGHT_MARGIN;
    public static final int PAD_SCREEN = 3;
    public static final float PAD_SCREEN_INCH = 10.0f;
    public static final float PAD_SMALL_FREEFORM_ROUND_CORNER_DIP = 7.1f;
    private static final float PAD_TARGET_SIDE_DIFF;
    public static final int PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
    public static final float PORTRAIT_FREEFORM_HEIGHT_DIP_PAD;
    public static final float PORTRAIT_FREEFORM_WIDTH_DIP_PAD;
    public static final Map<Integer, Integer> PREV_HAPTIC_FEEDBACK_ID_TO_NEW_ID;
    public static final String PROPERTY_AUTOUI_ALLOW_SYSTEM_OVERRIDE = "android.window.PROPERTY_AUTOUI_ALLOW_SYSTEM_OVERRIDE";
    public static final int REGULAR_SCREEN = 1;
    public static final int RESIZE_WIDTH;
    private static String RESOLUTION_FILE = null;
    public static final float SCREEN_WIDTH_HEIGHT_RATIO_THRESHOLD_LOW = 0.33333334f;
    public static final String SKIP_TOUCH_CLASSNAME = "org.chromium.chrome.browser.firstrun.FirstRunActivity";
    public static final String SKIP_TOUCH_PACKAGENAME = "com.android.chrome";
    public static final int SLIDE_OUT_FINAL_POSITION_OFFSITE;
    public static final int SLIDE_OUT_POSITION_THRESHOLD;
    public static final int SLIDE_OUT_VELOCITY_THRESHOLD;
    public static int SLOWER_SPEED = 0;
    public static final int SMALL_FREEFORM_DOWNWARD_RIGHT_MARGIN;
    public static final int SMALL_FREEFORM_DOWNWARD_TOP_MARGIN;
    public static final int SMALL_FREEFORM_EXIT_INNER_HOTSPOT = 6;
    public static final float SMALL_FREEFORM_EXIT_INNER_HOTSPOT_HEIGHT = 198.0f;
    public static final float SMALL_FREEFORM_EXIT_INNER_HOTSPOT_RADIUS = 44.0f;
    public static final float SMALL_FREEFORM_EXIT_INNER_HOTSPOT_TOP_MARGIN = 67.0f;
    public static final float SMALL_FREEFORM_EXIT_INNER_HOTSPOT_WIDTH = 352.0f;
    public static final int SMALL_FREEFORM_EXIT_MIDDLE_HOTSPOT = 7;
    public static final float SMALL_FREEFORM_EXIT_MIDDLE_HOTSPOT_HEIGHT = 316.0f;
    public static final float SMALL_FREEFORM_EXIT_MIDDLE_HOTSPOT_RADIUS = 44.0f;
    public static final float SMALL_FREEFORM_EXIT_MIDDLE_HOTSPOT_TOP_MARGIN = 8.0f;
    public static final float SMALL_FREEFORM_EXIT_MIDDLE_HOTSPOT_WIDTH = 483.0f;
    public static final int SMALL_FREEFORM_EXIT_OUTER_HOTSPOT = 8;
    public static final float SMALL_FREEFORM_EXIT_OUTER_HOTSPOT_HEIGHT = 397.0f;
    public static final float SMALL_FREEFORM_EXIT_OUTER_HOTSPOT_RADIUS = 68.0f;
    public static final float SMALL_FREEFORM_EXIT_OUTER_HOTSPOT_TOP_MARGIN = 8.0f;
    public static final float SMALL_FREEFORM_EXIT_OUTER_HOTSPOT_WIDTH = 638.0f;
    public static final int SMALL_FREEFORM_HOTSPOT_NONE = -2;
    public static final int SMALL_FREEFORM_LANDCAPE_HORIZONTAL_MARGIN;
    public static final int SMALL_FREEFORM_LANDCAPE_VERTICAL_MARGIN;
    public static final float SMALL_FREEFORM_MOVE_VELOCITY_X_THRESHOLD = 2000.0f;
    public static final float SMALL_FREEFORM_MOVE_VELOCITY_Y_THRESHOLD = 4000.0f;
    public static final int SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN;
    public static final int SMALL_FREEFORM_PORTRAIT_VERTICAL_MARGIN;
    public static final float SMALL_FREEFORM_RIGHT_MARGIN;
    public static final float SMALL_FREEFORM_TOP_MARGIN;
    public static final float STABLE_DENSITY;
    public static final int START_SAMLL_FREEFORM = 1;
    static final int SWITCH_STATELESS = -1;
    static final String TAG = "MiuiMultiWindowUtils";
    public static final int TEST_MODE_FOR_ABNORMAL_FREEFORM = 2;
    private static final int TIMESTAP_MAX_SIZE = 10;
    public static final int TOP_DECOR_CAPTIONVIEW_HEIGHT;
    private static final int TOP_MARGIN_ARG = 3;
    private static final float UNDEFINED_ARG = -1.0f;
    public static final int UNDEFINED_MODE_FOR_ABNORMAL_FREEFORM = -1;
    public static final int WIDE_SCREEN = 2;
    public static final int WIDE_SCREEN_DP = 600;
    public static final float WIDE_SCREEN_INCH = 8.0f;
    public static int mCurrentSmallWindowCorner;
    private static int mTotalRamStr;
    private static HashMap<String, Integer> sAppList;
    public static volatile Boolean sEnableBlackListAppFreeFormDebug;
    private static Map<String, Integer> sFreeformSuggestionList;
    private static final Object sGetFreeformBlackListLock;
    private static List<String> sNotSupportFreeformDeviceList;
    private static List<String> sSupportFreeformDeviceList;
    private static List<String> sSupportMultiSwitchDeviceList;
    public static final boolean MIUIFREEFORM_ON_SHELL = SystemProperties.getBoolean("persist.wm.debug.miuifreeform_on_shell", true);
    private static long sLastUpdateTime = 0;
    public static final float FREEFORM_ROUND_CORNER = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25.8f, Resources.getSystem().getDisplayMetrics());
    public static final float PAD_FREEFORM_ROUND_CORNER = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.9f, Resources.getSystem().getDisplayMetrics());
    public static final float PAD_SMALL_FREEFORM_ROUND_CORNER = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7.1f, Resources.getSystem().getDisplayMetrics());
    public static volatile float sScale = 1.0f;

    /* loaded from: classes.dex */
    private enum RESOLUTION_ARG_TYPE {
        FREEFORM,
        MINI_FREEFORM,
        FREEFORM_INNER,
        MINI_FREEFORM_INNER,
        FREEFORM_OUTER,
        MINI_FREEFORM_OUTER
    }

    static {
        int dimensionPixelSize;
        IS_FOLD_SCREEN_DEVICE = SystemProperties.getInt("persist.sys.muiltdisplay_type", 0) == 2;
        STABLE_DENSITY = DisplayMetrics.DENSITY_DEVICE_STABLE / 160.0f;
        FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6.0f, Resources.getSystem().getDisplayMetrics());
        PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6.0f, Resources.getSystem().getDisplayMetrics());
        PAD_FREEFORM_ACCESSIBLE_AREA_HORIZONTAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20.0f, Resources.getSystem().getDisplayMetrics());
        PAD_FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        PAD_FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN_DESKTOP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 78.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, Resources.getSystem().getDisplayMetrics());
        PAD_RIGHT_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40.0f, Resources.getSystem().getDisplayMetrics());
        PAD_TARGET_SIDE_DIFF = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 78.0f, Resources.getSystem().getDisplayMetrics());
        RESOLUTION_FILE = "freeform_resolutions.json";
        FREEFORM_RESOLUTION_ARGS = new ArrayList();
        MINI_FREEFORM_RESOLUTION_ARGS = new ArrayList();
        FREEFORM_RESOLUTION_ARGS_INNER = new ArrayList();
        MINI_FREEFORM_RESOLUTION_ARGS_INNER = new ArrayList();
        FREEFORM_RESOLUTION_ARGS_OUTER = new ArrayList();
        MINI_FREEFORM_RESOLUTION_ARGS_OUTER = new ArrayList();
        MULTI_WINDOW_SWITCH_ENABLED = SystemProperties.getBoolean("ro.config.miui_multi_window_switch_enable", false);
        DEVICES_SUPPORT_MULTI_FREEFORM = new ArrayList(Arrays.asList());
        initFreeFormResolutionArgs();
        MIUI_FREEFORM_SHADOW_V2_SUPPORTED = SystemProperties.getBoolean("persist.sys.mi_shadow_supported", true);
        PAD_MULTI_FREEFORM_DEFAULT_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_LANDCAPE_RESERVE_MARGINS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14.54f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_LANDCAPE_LANDCAPE_RESERVE_MARGINS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 47.8f, Resources.getSystem().getDisplayMetrics());
        HOT_SPACE_OFFSITE = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 11.0f, Resources.getSystem().getDisplayMetrics());
        HOT_SPACE_BOTTOM_OFFSITE_PAD = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14.0f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12.36f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_PORTRAIT_VERTICAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 82.55f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_LANDCAPE_HORIZONTAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13.09f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_LANDCAPE_VERTICAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13.09f, Resources.getSystem().getDisplayMetrics());
        LANDCAPE_SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 21.82f, Resources.getSystem().getDisplayMetrics());
        LANDCAPE_SMALL_FREEFORM_PORTRAIT_VERTICAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 82.55f, Resources.getSystem().getDisplayMetrics());
        LANDCAPE_SMALL_FREEFORM_LANDCAPE_HORIZONTAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 21.82f, Resources.getSystem().getDisplayMetrics());
        LANDCAPE_SMALL_FREEFORM_LANDCAPE_VERTICAL_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13.09f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_DOWNWARD_TOP_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 231.27f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_DOWNWARD_RIGHT_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13.09f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75.27f, Resources.getSystem().getDisplayMetrics());
        SMALL_FREEFORM_RIGHT_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_RECT_OFFSET_X_ZIZHAN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 78.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_RECT_OFFSET_Y_ZIZHAN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 44.0f, Resources.getSystem().getDisplayMetrics());
        SLIDE_OUT_VELOCITY_THRESHOLD = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -170.0f, Resources.getSystem().getDisplayMetrics());
        SLIDE_OUT_POSITION_THRESHOLD = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 82.55f, Resources.getSystem().getDisplayMetrics());
        SLIDE_OUT_FINAL_POSITION_OFFSITE = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOT_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_ROUND_CORNER_DIP_MIUI15 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18.0f, Resources.getSystem().getDisplayMetrics());
        MINI_FREEFORM_ROUND_CORNER_DIP_MIUI15 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12.0f, Resources.getSystem().getDisplayMetrics());
        ACCESSABLE_MARGIN_DIP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6.0f, Resources.getSystem().getDisplayMetrics());
        ACCESSABLE_MARGIN_DIP_PAD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20.0f, Resources.getSystem().getDisplayMetrics());
        PORTRAIT_FREEFORM_WIDTH_DIP_PAD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 420.0f, Resources.getSystem().getDisplayMetrics());
        PORTRAIT_FREEFORM_HEIGHT_DIP_PAD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 750.0f, Resources.getSystem().getDisplayMetrics());
        LANDSCAPE_CVW_NEW_FREEFORM_WIDTH_DIP_PAD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 880.0f, Resources.getSystem().getDisplayMetrics());
        LANDSCAPE_CVW_NEW_FREEFORM_HEIGHT_DIP_PAD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 660.0f, Resources.getSystem().getDisplayMetrics());
        float applyDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 102.55f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_RADIUS = applyDimension;
        float applyDimension2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 91.64f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_RADIUS = applyDimension2;
        FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 92.36f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 103.27f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_RADIUS_FOLD = applyDimension;
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 149.09f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_RADIUS_FOLD = applyDimension2;
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 174.18f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN_FLOD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 87.09f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 241.09f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_BOTTOM_MARGIN_FOLD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120.55f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN_FOLD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 37.09f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_BOTTOM_MARGIN_FOLD = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18.55f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45.82f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_BOTTOM_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45.82f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 156.36f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 66.18f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_CENTER_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62.54f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62.54f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 156.36f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 132.36f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 181.82f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_CENTER_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.0f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62.54f, Resources.getSystem().getDisplayMetrics());
        FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_TOP_MARGIN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62.54f, Resources.getSystem().getDisplayMetrics());
        RESIZE_WIDTH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40.0f, Resources.getSystem().getDisplayMetrics());
        NOTCH_MARGIN = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40.73f, Resources.getSystem().getDisplayMetrics());
        mCurrentSmallWindowCorner = -1;
        BOTTOM_DECOR_CAPTIONVIEW_HEIGHT = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 37.82f, Resources.getSystem().getDisplayMetrics());
        sNotSupportFreeformDeviceList = new ArrayList();
        sSupportFreeformDeviceList = new ArrayList();
        sSupportMultiSwitchDeviceList = new ArrayList();
        sFreeformSuggestionList = new HashMap();
        sAppList = new HashMap<>();
        MIUI_FREEFORM_RESET_COLOR = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        MIUI_FREEFORM_AMBIENT_COLOR = new float[]{0.0f, 0.0f, 0.0f, 0.4f};
        LONG_WAIT = 100;
        LONG_WAIT_TOAST = 480;
        SLOWER_SPEED = 500;
        HashMap hashMap = new HashMap();
        ADDITIONAL_FREEFORM_RESOLUTIONS = hashMap;
        int[] iArr = {2133, 1600};
        ADDITIONAL_FREEFORM_RESOLUTION_1 = iArr;
        int[] iArr2 = {1200, 1600};
        ADDITIONAL_FREEFORM_RESOLUTION_2 = iArr2;
        sAppList.put("com.tencent.mm", 1);
        sAppList.put("com.tencent.mobileqq", 2);
        sAppList.put("com.ss.android.lark.kami", 4);
        sAppList.put("com.ss.android.lark", 5);
        sAppList.put("com.alibaba.android.rimet", 7);
        sNotSupportFreeformDeviceList.add("dandelion");
        sNotSupportFreeformDeviceList.add("angelica");
        sNotSupportFreeformDeviceList.add("angelicain");
        sNotSupportFreeformDeviceList.add("cattail");
        sNotSupportFreeformDeviceList.add("angelican");
        sNotSupportFreeformDeviceList.add("frost");
        sSupportFreeformDeviceList.add("fire");
        sSupportFreeformDeviceList.add("heat");
        sSupportFreeformDeviceList.add("earth");
        sSupportFreeformDeviceList.add("xun");
        sSupportMultiSwitchDeviceList.add("cetus");
        MULTIWIN_SWITCH_ENABLED = isSupportMultiSwitchFeature();
        if (isTablet()) {
            hashMap.put(Float.valueOf(1.333333f), iArr);
            hashMap.put(Float.valueOf(0.75f), iArr2);
        }
        if (isWideScreen()) {
            dimensionPixelSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 46.0f, Resources.getSystem().getDisplayMetrics());
        } else {
            dimensionPixelSize = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        }
        TOP_DECOR_CAPTIONVIEW_HEIGHT = dimensionPixelSize;
        mTotalRamStr = 0;
        HashMap hashMap2 = new HashMap();
        PREV_HAPTIC_FEEDBACK_ID_TO_NEW_ID = hashMap2;
        hashMap2.put(268435470, 0);
        hashMap2.put(268435461, 0);
        sEnableBlackListAppFreeFormDebug = null;
        sGetFreeformBlackListLock = new Object();
    }

    public static boolean isTablet() {
        return SystemProperties.get("ro.build.characteristics").contains("tablet");
    }

    public static int getTopCaptionViewHeightForClient(Context context) {
        return getStatusBarHeight(context, 0);
    }

    public static int getTopCaptionViewHeight(Context context) {
        return getInsetValueFromServer(context, WindowInsets.Type.statusBars());
    }

    public static boolean isNightMode(Context ctx) {
        UiModeManager uiManager = (UiModeManager) ctx.getSystemService(Context.UI_MODE_SERVICE);
        return uiManager != null && uiManager.getNightMode() == UiModeManager.MODE_NIGHT_YES;
    }

    public static boolean isVerical(Context context) {
        return (context.getDisplay().getRotation() == Surface.ROTATION_90 || context.getDisplay().getRotation() == Surface.ROTATION_270) ? false : true;
    }

    public static int isEnterHotArea(Context context, float x2, float y2) {
        return isEnterHotArea(context, x2, y2, false);
    }

    public static int isEnterHotArea(Context context, float x2, float y2, boolean isReminder) {
        return isEnterHotArea(context, x2, y2, isReminder, false);
    }

    public static int isEnterHotArea(Context context, float x2, float y2, boolean isReminder, boolean isBottomHotArea) {
        throw new RuntimeException("Stub!");
    }

    public static int isEnterHotArea(boolean inSmallFreeform, Context context, float x2, float y2) {
        if (!inSmallFreeform) {
            return isEnterHotArea(context, x2, y2);
        }
        int displayWidth = getDisplaySize(context).width();
        float left = (displayWidth - 352.0f) / 2.0f;
        RectF hotAreaRect1 = new RectF(left, 67.0f + 44.0f, left + 352.0f, (67.0f + 198.0f) - 44.0f);
        RectF hotAreaRect2 = new RectF(left + 44.0f, 67.0f, (352.0f + left) - 44.0f, 198.0f + 67.0f);
        if (hotAreaRect1.contains(x2, y2) || hotAreaRect2.contains(x2, y2) || getDistance(x2, y2, hotAreaRect2.left, hotAreaRect1.top) <= 44.0d || getDistance(x2, y2, hotAreaRect2.right, hotAreaRect1.top) <= 44.0d || getDistance(x2, y2, hotAreaRect2.left, hotAreaRect1.bottom) <= 44.0d || getDistance(x2, y2, hotAreaRect2.right, hotAreaRect1.bottom) <= 44.0d) {
            return 6;
        }
        float left2 = (displayWidth - 483.0f) / 2.0f;
        float top = 8.0f + 44.0f;
        hotAreaRect1.set(left2, top, left2 + 483.0f, (8.0f + 316.0f) - 44.0f);
        hotAreaRect2.set(left2 + 44.0f, 8.0f, (483.0f + left2) - 44.0f, 316.0f + 8.0f);
        if (hotAreaRect1.contains(x2, y2) || hotAreaRect2.contains(x2, y2) || getDistance(x2, y2, hotAreaRect2.left, hotAreaRect1.top) <= 44.0d || getDistance(x2, y2, hotAreaRect2.right, hotAreaRect1.top) <= 44.0d || getDistance(x2, y2, hotAreaRect2.left, hotAreaRect1.bottom) <= 44.0d || getDistance(x2, y2, hotAreaRect2.right, hotAreaRect1.bottom) <= 44.0d) {
            return 7;
        }
        float left3 = (displayWidth - 638.0f) / 2.0f;
        hotAreaRect1.set(left3, 8.0f + 68.0f, left3 + 638.0f, (8.0f + 397.0f) - 68.0f);
        hotAreaRect2.set(left3 + 68.0f, 8.0f, (638.0f + left3) - 68.0f, 397.0f + 8.0f);
        if (hotAreaRect1.contains(x2, y2) || hotAreaRect2.contains(x2, y2) || getDistance(x2, y2, hotAreaRect2.left, hotAreaRect1.top) <= 68.0d || getDistance(x2, y2, hotAreaRect2.right, hotAreaRect1.top) <= 68.0d || getDistance(x2, y2, hotAreaRect2.left, hotAreaRect1.bottom) <= 68.0d || getDistance(x2, y2, hotAreaRect2.right, hotAreaRect1.bottom) <= 68.0d) {
            return 8;
        }
        return -2;
    }

    private static float[] initParams(boolean isReminder, boolean isBottomHotArea, DisplayMetrics outMetrics, boolean isVertical) {
        float f2;
        float[] params = new float[7];
        if (!isBottomHotArea) {
            if (!isReminder) {
                params[0] = isVertical ? FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_RADIUS : FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_RADIUS;
                params[1] = isVertical ? FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_BOTTOM_MARGIN : FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_BOTTOM_MARGIN;
                params[2] = isVertical ? FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_BOTTOM_MARGIN : FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_BOTTOM_MARGIN;
                params[3] = isVertical ? FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_TOP_MARGIN : FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
                params[4] = isVertical ? FREEFORM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN : FREEFORM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_TOP_MARGIN;
            } else {
                if (IS_FOLD_SCREEN_DEVICE) {
                    params[0] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_RADIUS_FOLD : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_RADIUS_FOLD;
                    params[2] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_BOTTOM_MARGIN_FOLD : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_BOTTOM_MARGIN_FOLD;
                    params[4] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN_FLOD : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN_FOLD;
                } else {
                    params[0] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_RADIUS : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_RADIUS;
                    params[2] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_BOTTOM_MARGIN : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_BOTTOM_MARGIN;
                    params[4] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN;
                }
                params[1] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_BOTTOM_MARGIN : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_BOTTOM_MARGIN;
                params[3] = isVertical ? FREEFORM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_TOP_MARGIN : FREEFORM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
            }
            params[5] = 0.0f;
            params[6] = 0.0f;
        } else {
            if (!isReminder) {
                params[0] = isVertical ? FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_RADIUS : FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_RADIUS;
                params[3] = isVertical ? FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_HORIZONTAL_TOP_MARGIN : FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
                params[4] = isVertical ? FREEFORM_BOTTOM_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN : FREEFORM_BOTTOM_HOTSPOT_TRIGGER_LANDCAPE_VERTICAL_TOP_MARGIN;
                params[5] = isVertical ? 0.0f : ((outMetrics.widthPixels - ((params[3] + params[0]) * 2.0f)) / 2.0f) * 0.45f;
                params[6] = isVertical ? FREEFORM_BOTTOM_CENTER_HOTSPOT_TRIGGER_PORTRAIT_VERTICAL_TOP_MARGIN : (params[5] * (-1.0f)) / 4.0f;
            } else {
                params[0] = isVertical ? FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_RADIUS : FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_RADIUS;
                params[3] = isVertical ? FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_HORIZONTAL_TOP_MARGIN : FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_HORIZONTAL_TOP_MARGIN;
                params[4] = isVertical ? FREEFORM_BOTTOM_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN : FREEFORM_BOTTOM_HOTSPOT_REMINDER_LANDCAPE_VERTICAL_TOP_MARGIN;
                if (isVertical) {
                    f2 = (outMetrics.widthPixels - ((params[3] + params[0]) * 2.0f)) / 2.0f;
                } else {
                    f2 = ((outMetrics.widthPixels - ((params[3] + params[0]) * 2.0f)) / 2.0f) * 0.9f;
                }
                params[5] = f2;
                params[6] = isVertical ? FREEFORM_BOTTOM_CENTER_HOTSPOT_REMINDER_PORTRAIT_VERTICAL_TOP_MARGIN : (params[5] * (-1.0f)) / 4.0f;
            }
            params[1] = 0.0f;
            params[2] = 0.0f;
        }
        return params;
    }

    private static int inFreeformHotSpotNum(float x2, float y2, float[] params, boolean isBottomHotArea, DisplayMetrics outMetrics) {
        if (isBottomHotArea) {
            if (new Rect(0, 0, (int) (params[0] + params[3]), (int) params[4]).contains((int) x2, (int) y2) || new Rect(0, 0, (int) params[3], (int) (params[4] + params[0])).contains((int) x2, (int) y2) || getDistance(x2, y2, params[3], params[4]) <= params[0]) {
                return 1;
            }
            if (new Rect((int) ((outMetrics.widthPixels - params[3]) - params[0]), 0, outMetrics.widthPixels, (int) params[4]).contains((int) x2, (int) y2) || new Rect((int) (outMetrics.widthPixels - params[3]), 0, outMetrics.widthPixels, (int) (params[4] + params[0])).contains((int) x2, (int) y2) || getDistance(x2, y2, outMetrics.widthPixels - params[3], params[4]) <= params[0]) {
                return 2;
            }
            if (y2 >= params[6]) {
                getDistance(x2, y2, outMetrics.widthPixels / 2, params[6]);
                float f2 = params[5];
            }
            return -1;
        }
        if (new Rect(0, 0, (int) (params[0] + params[3]), (int) params[4]).contains((int) x2, (int) y2) || new Rect(0, 0, (int) params[3], (int) (params[4] + params[0])).contains((int) x2, (int) y2) || getDistance(x2, y2, params[3], params[4]) <= params[0]) {
            return 1;
        }
        if (new Rect((int) ((outMetrics.widthPixels - params[3]) - params[0]), 0, outMetrics.widthPixels, (int) params[4]).contains((int) x2, (int) y2) || new Rect((int) (outMetrics.widthPixels - params[3]), 0, outMetrics.widthPixels, (int) (params[4] + params[0])).contains((int) x2, (int) y2) || getDistance(x2, y2, outMetrics.widthPixels - params[3], params[4]) <= params[0]) {
            return 2;
        }
        if (new Rect(0, (int) (outMetrics.heightPixels - params[2]), (int) (params[0] + params[1]), outMetrics.heightPixels).contains((int) x2, (int) y2) || new Rect(0, (int) ((outMetrics.heightPixels - params[2]) - params[0]), (int) params[1], outMetrics.heightPixels).contains((int) x2, (int) y2) || getDistance(x2, y2, params[1], outMetrics.heightPixels - params[2]) <= params[0]) {
            return 3;
        }
        return (new Rect((int) ((((float) outMetrics.widthPixels) - params[0]) - params[1]), (int) (((float) outMetrics.heightPixels) - params[2]), outMetrics.widthPixels, outMetrics.heightPixels).contains((int) x2, (int) y2) || new Rect((int) (((float) outMetrics.widthPixels) - params[1]), (int) ((((float) outMetrics.heightPixels) - params[2]) - params[0]), outMetrics.widthPixels, outMetrics.heightPixels).contains((int) x2, (int) y2) || getDistance(x2, y2, ((float) outMetrics.widthPixels) - params[1], ((float) outMetrics.heightPixels) - params[2]) <= ((double) params[0])) ? 4 : -1;
    }

    public static Rect findNearestCorner(Context context, float x2, float y2, int position, boolean isLandcapeFreeform) {
        return findNearestCorner(context, x2, y2, position, isLandcapeFreeform, true);
    }

    public static Rect findNearestCorner(Context context, float x2, float y2, int position, boolean isLandcapeFreeform, boolean isNormalFreeForm) {
        return findNearestCorner(context, x2, y2, position, 0.0f, 0.0f, isLandcapeFreeform, isNormalFreeForm, null);
    }

    public static Rect findNearestCorner(Context context, float x2, float y2, int position, boolean isLandcapeFreeform, boolean isNormalFreeForm, Rect smallWindowBounds) {
        return findNearestCorner(context, x2, y2, position, 0.0f, 0.0f, isLandcapeFreeform, isNormalFreeForm, smallWindowBounds);
    }

    public static Rect findNearestCorner(Context context, float x2, float y2, int position, float velocityX, float velocityY, boolean isLandcapeFreeform, boolean isNormalFreeForm) {
        return findNearestCorner(context, x2, y2, position, velocityX, velocityY, isLandcapeFreeform, isNormalFreeForm, null);
    }

    public static Rect findNearestCorner(Context context, float x2, float y2, int position, float velocityX, float velocityY, boolean isLandcapeFreeform, boolean isNormalFreeForm, Rect smallWindowBounds) {
        Rect displayRect = getDisplaySize(context);
        int screenWidth = displayRect.width();
        int screenHeight = displayRect.height();
        Rect leftTopWindowBounds = new Rect();
        Rect rightTopWindowBounds = new Rect();
        Rect leftBottomWindowBounds = new Rect();
        Rect rightBottomWindowBounds = new Rect();
        calculateCornerBound(context, leftTopWindowBounds, rightTopWindowBounds, leftBottomWindowBounds, rightBottomWindowBounds, isLandcapeFreeform, isNormalFreeForm, smallWindowBounds);
        Log.d(TAG, "leftTopWindowBounds: " + leftTopWindowBounds + " rightTopWindowBounds:" + rightTopWindowBounds + " leftBottomWindowBounds: " + leftBottomWindowBounds + " rightBottomWindowBounds:" + rightBottomWindowBounds + " position:" + position);
        if (position != -1) {
            if (position == 1) {
                mCurrentSmallWindowCorner = 1;
                return leftTopWindowBounds;
            }
            if (position == 2) {
                mCurrentSmallWindowCorner = 2;
                return rightTopWindowBounds;
            }
            if (position == 4) {
                mCurrentSmallWindowCorner = 4;
                return rightBottomWindowBounds;
            }
            if (position == 3) {
                mCurrentSmallWindowCorner = 3;
                return leftBottomWindowBounds;
            }
        }
        if (Math.abs(velocityX) > 2000.0f || Math.abs(velocityY) > 4000.0f) {
            if (velocityX > 2000.0f && velocityY > 4000.0f) {
                mCurrentSmallWindowCorner = 4;
                return rightBottomWindowBounds;
            }
            if (velocityX < -2000.0f && velocityY < -4000.0f) {
                mCurrentSmallWindowCorner = 1;
                return leftTopWindowBounds;
            }
            if (velocityX > 2000.0f && velocityY < -4000.0f) {
                mCurrentSmallWindowCorner = 2;
                return rightTopWindowBounds;
            }
            if (velocityX < -2000.0f && velocityY > 4000.0f) {
                mCurrentSmallWindowCorner = 3;
                return leftBottomWindowBounds;
            }
            if (velocityX > 2000.0f) {
                int i2 = mCurrentSmallWindowCorner;
                if (i2 == 1 || i2 == 2) {
                    mCurrentSmallWindowCorner = 2;
                    return rightTopWindowBounds;
                }
                if (i2 == 3 || i2 == 4) {
                    mCurrentSmallWindowCorner = 4;
                    return rightBottomWindowBounds;
                }
            } else if (velocityX < -2000.0f) {
                int i3 = mCurrentSmallWindowCorner;
                if (i3 == 1 || i3 == 2) {
                    mCurrentSmallWindowCorner = 1;
                    return leftTopWindowBounds;
                }
                if (i3 == 3 || i3 == 4) {
                    mCurrentSmallWindowCorner = 3;
                    return leftBottomWindowBounds;
                }
            } else if (velocityY > 4000.0f) {
                int i4 = mCurrentSmallWindowCorner;
                if (i4 == 1 || i4 == 3) {
                    mCurrentSmallWindowCorner = 3;
                    return leftBottomWindowBounds;
                }
                if (i4 == 2 || i4 == 4) {
                    mCurrentSmallWindowCorner = 4;
                    return rightBottomWindowBounds;
                }
            } else if (velocityY < -4000.0f) {
                int i5 = mCurrentSmallWindowCorner;
                if (i5 == 1 || i5 == 3) {
                    mCurrentSmallWindowCorner = 1;
                    return leftTopWindowBounds;
                }
                if (i5 == 2 || i5 == 4) {
                    mCurrentSmallWindowCorner = 2;
                    return rightTopWindowBounds;
                }
            }
        }
        if (x2 <= screenWidth / 2) {
            if (y2 <= screenHeight / 2) {
                mCurrentSmallWindowCorner = 1;
                return leftTopWindowBounds;
            }
            mCurrentSmallWindowCorner = 3;
            return leftBottomWindowBounds;
        }
        if (y2 <= screenHeight / 2) {
            mCurrentSmallWindowCorner = 2;
            return rightTopWindowBounds;
        }
        mCurrentSmallWindowCorner = 4;
        return rightBottomWindowBounds;
    }

    public static RectF getSmallFreeformRect(Context context, boolean isLandscapeFreeform) {
        int shortSide = Math.min(getDisplaySize(context).width(), getDisplaySize(context).height());
        return getSmallFreeformRect(context, isLandscapeFreeform, shortSide);
    }

    public static RectF getSmallFreeformRect(Context context, boolean isLandscapeFreeform, int displayShortSide) {
        return getSmallFreeformRect(context, isLandscapeFreeform, displayShortSide, true);
    }

    public static RectF getSmallFreeformRect(Context context, boolean isLandscapeFreeform, boolean isNormalFreeForm) {
        int shortSide = Math.min(getDisplaySize(context).width(), getDisplaySize(context).height());
        return getSmallFreeformRect(context, isLandscapeFreeform, shortSide, isNormalFreeForm);
    }

    public static RectF getSmallFreeformRect(Context context, boolean isLandscapeFreeform, int displayShortSide, boolean isNormalFreeForm) {
        float targetSide;
        float smallFreeformHeight;
        float windowAspectRatio = getSmallAspectRatio(isLandscapeFreeform, context, isNormalFreeForm);
        if (isPadScreen(context)) {
            float targetSide2 = displayShortSide - PAD_TARGET_SIDE_DIFF;
            smallFreeformHeight = isLandscapeFreeform ? PAD_LANDSCAPE_MINI_FREEFORM_HEIGHT_RATIO * targetSide2 : PAD_PORTRAIT_MINI_FREEFORM_HEIGHT_RATIO * targetSide2;
            targetSide = smallFreeformHeight * windowAspectRatio;
        } else {
            float windowScreenRatio = getSmallWindowScreenRatio(isLandscapeFreeform, context);
            if (isLandscapeFreeform) {
                smallFreeformHeight = displayShortSide * windowScreenRatio;
                targetSide = smallFreeformHeight * windowAspectRatio;
            } else {
                float smallFreeformHeight2 = displayShortSide;
                float smallFreeformWidth = smallFreeformHeight2 * windowScreenRatio;
                targetSide = smallFreeformWidth;
                smallFreeformHeight = smallFreeformWidth / windowAspectRatio;
            }
        }
        Slog.d(TAG, "small freeform: {width:" + targetSide + ", height:" + smallFreeformHeight + "}");
        return new RectF(0.0f, 0.0f, targetSide, smallFreeformHeight);
    }

    public static float getMiniFreeformScale(Context context, boolean isLandscapeFreeform, Rect OriginBounds, String packageName) {
        float targetSide;
        float f2;
        int screenWidth = getDisplaySize(context).width();
        int screenHeight = getDisplaySize(context).height();
        int displayShortSide = Math.min(screenHeight, screenWidth);
        int cvwType = getCvwLevelType(packageName, context);
        float smallFreeformWidth = 0.0f;
        float smallFreeformHeight = 0.0f;
        if (isPadScreen(context)) {
            float targetSide2 = displayShortSide - PAD_TARGET_SIDE_DIFF;
            if (cvwType != 0) {
                smallFreeformHeight = cvwType == 2 ? PAD_PORTRAIT_MINI_FREEFORM_HEIGHT_RATIO * targetSide2 : targetSide2 * PAD_LANDSCAPE_MINI_FREEFORM_HEIGHT_RATIO;
            } else {
                if (isLandscapeFreeform) {
                    f2 = PAD_LANDSCAPE_MINI_FREEFORM_HEIGHT_RATIO * targetSide2;
                } else {
                    f2 = targetSide2 * PAD_PORTRAIT_MINI_FREEFORM_HEIGHT_RATIO;
                }
                smallFreeformHeight = f2;
            }
            targetSide = smallFreeformHeight / OriginBounds.height();
        } else {
            float windowScreenRatio = getSmallWindowScreenRatio(isLandscapeFreeform, context);
            if (isLandscapeFreeform) {
                smallFreeformHeight = displayShortSide * windowScreenRatio;
                targetSide = smallFreeformHeight / OriginBounds.height();
            } else {
                float scale = displayShortSide;
                smallFreeformWidth = scale * windowScreenRatio;
                targetSide = smallFreeformWidth / OriginBounds.width();
            }
        }
        Slog.d(TAG, "small freeform: {width:" + smallFreeformWidth + ", height:" + smallFreeformHeight + "scale: " + targetSide + "}");
        return targetSide;
    }

    public static void calculateCornerBound(Context context, Rect outLeftTopWindowBounds, Rect outRightTopWindowBounds, Rect outLeftBottomWindowBounds, Rect outRightBottomWindowBounds, boolean isLandcapeFreeform) {
        calculateCornerBound(context, outLeftTopWindowBounds, outRightTopWindowBounds, outLeftBottomWindowBounds, outRightBottomWindowBounds, isLandcapeFreeform, true, null);
    }

    public static void calculateCornerBound(Context context, Rect outLeftTopWindowBounds, Rect outRightTopWindowBounds, Rect outLeftBottomWindowBounds, Rect outRightBottomWindowBounds, boolean isLandcapeFreeform, boolean isNormalFreeForm, Rect smallWindowBounds) {
        float smallFreeformWidth;
        float smallFreeformWidth2;
        Context context2;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int displayRotation = wm.getDefaultDisplay().getRotation();
        boolean isNotchScreen = isNotchScreen(context);
        int screenWidth = getDisplaySize(context).width();
        int screenHeight = getDisplaySize(context).height();
        int shortSide = Math.min(screenHeight, screenWidth);
        boolean isVertical = screenHeight > screenWidth;
        if (smallWindowBounds == null) {
            RectF smallFreeformRect = getSmallFreeformRect(context, isLandcapeFreeform, shortSide, isNormalFreeForm);
            float smallFreeformWidth3 = smallFreeformRect.width();
            smallFreeformWidth = smallFreeformWidth3;
            smallFreeformWidth2 = smallFreeformRect.height();
        } else {
            float smallFreeformWidth4 = smallWindowBounds.width();
            smallFreeformWidth = smallFreeformWidth4;
            smallFreeformWidth2 = smallWindowBounds.height();
        }
        if (isVertical) {
            if (isLandcapeFreeform) {
                int i2 = LANDCAPE_SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN;
                int shortSide2 = LANDCAPE_SMALL_FREEFORM_PORTRAIT_VERTICAL_MARGIN;
                outLeftTopWindowBounds.set(i2, shortSide2, (int) (i2 + smallFreeformWidth), (int) (shortSide2 + smallFreeformWidth2));
                outLeftBottomWindowBounds.set(i2, (screenHeight - ((int) smallFreeformWidth2)) - shortSide2, (int) (i2 + smallFreeformWidth), screenHeight - shortSide2);
                outRightTopWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i2, shortSide2, screenWidth - i2, (int) (shortSide2 + smallFreeformWidth2));
                outRightBottomWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i2, (screenHeight - ((int) smallFreeformWidth2)) - shortSide2, screenWidth - i2, screenHeight - shortSide2);
                return;
            }
            int i3 = SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN;
            int i4 = SMALL_FREEFORM_PORTRAIT_VERTICAL_MARGIN;
            outLeftTopWindowBounds.set(i3, i4, (int) (i3 + smallFreeformWidth), (int) (i4 + smallFreeformWidth2));
            outLeftBottomWindowBounds.set(i3, (screenHeight - ((int) smallFreeformWidth2)) - i4, (int) (i3 + smallFreeformWidth), screenHeight - i4);
            outRightTopWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i3, i4, screenWidth - i3, (int) (i4 + smallFreeformWidth2));
            outRightBottomWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i3, (screenHeight - ((int) smallFreeformWidth2)) - i4, screenWidth - i3, screenHeight - i4);
            return;
        }
        if (isLandcapeFreeform) {
            int i5 = LANDCAPE_SMALL_FREEFORM_LANDCAPE_HORIZONTAL_MARGIN;
            int i6 = LANDCAPE_SMALL_FREEFORM_LANDCAPE_VERTICAL_MARGIN;
            outLeftTopWindowBounds.set(i5, i6, (int) (i5 + smallFreeformWidth), (int) (i6 + smallFreeformWidth2));
            outLeftBottomWindowBounds.set(i5, (screenHeight - ((int) smallFreeformWidth2)) - i6, (int) (LANDCAPE_SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN + smallFreeformWidth), screenHeight - i6);
            outRightTopWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i5, i6, screenWidth - i5, (int) (i6 + smallFreeformWidth2));
            outRightBottomWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i5, (screenHeight - ((int) smallFreeformWidth2)) - i6, screenWidth - i5, screenHeight - i6);
        } else {
            int i7 = SMALL_FREEFORM_LANDCAPE_HORIZONTAL_MARGIN;
            int i8 = SMALL_FREEFORM_LANDCAPE_VERTICAL_MARGIN;
            outLeftTopWindowBounds.set(i7, i8, (int) (i7 + smallFreeformWidth), (int) (i8 + smallFreeformWidth2));
            outLeftBottomWindowBounds.set(i7, (screenHeight - ((int) smallFreeformWidth2)) - i8, (int) (SMALL_FREEFORM_PORTRAIT_HORIZONTAL_MARGIN + smallFreeformWidth), screenHeight - i8);
            outRightTopWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i7, i8, screenWidth - i7, (int) (i8 + smallFreeformWidth2));
            outRightBottomWindowBounds.set((screenWidth - ((int) smallFreeformWidth)) - i7, (screenHeight - ((int) smallFreeformWidth2)) - i8, screenWidth - i7, screenHeight - i8);
        }
        if (isNotchScreen) {
            if (displayRotation == 1) {
                int i9 = outLeftTopWindowBounds.left;
                int i10 = NOTCH_MARGIN;
                outLeftTopWindowBounds.offsetTo(i9 + i10, outLeftTopWindowBounds.top);
                outLeftBottomWindowBounds.offsetTo(outLeftBottomWindowBounds.left + i10, outLeftBottomWindowBounds.top);
            } else if (displayRotation == 3) {
                int i11 = outRightTopWindowBounds.left;
                int i12 = NOTCH_MARGIN;
                outRightTopWindowBounds.offsetTo(i11 - i12, outRightTopWindowBounds.top);
                outRightBottomWindowBounds.offsetTo(outRightBottomWindowBounds.left - i12, outRightBottomWindowBounds.top);
            }
        }
        if (isFullScreenGestureNav(context)) {
            context2 = context;
        } else {
            context2 = context;
            int navHeight = getInsetValueFromServer(context2, WindowInsets.Type.navigationBars());
            if (displayRotation == 1) {
                outRightTopWindowBounds.offsetTo(outRightTopWindowBounds.left - navHeight, outRightTopWindowBounds.top);
                outRightBottomWindowBounds.offsetTo(outRightBottomWindowBounds.left - navHeight, outRightBottomWindowBounds.top);
            } else if (displayRotation == 3) {
                outLeftTopWindowBounds.offsetTo(outLeftTopWindowBounds.left + navHeight, outLeftTopWindowBounds.top);
                outLeftBottomWindowBounds.offsetTo(outLeftBottomWindowBounds.left + navHeight, outLeftBottomWindowBounds.top);
            }
        }
        int statusBarHeight = Math.max(getInsetValueFromServer(context2, WindowInsets.Type.statusBars()), getInsetValueFromServer(context2, WindowInsets.Type.displayCutout(), false));
        if (outLeftTopWindowBounds.top < statusBarHeight) {
            outLeftTopWindowBounds.offsetTo(outLeftTopWindowBounds.left, statusBarHeight);
        }
        if (outRightTopWindowBounds.top < statusBarHeight) {
            outRightTopWindowBounds.offsetTo(outRightTopWindowBounds.left, statusBarHeight);
        }
    }

    public static boolean isNotchScreen(Context context) {
        try {
            String notchProperty = SystemProperties.get("ro.miui.notch");
            if (notchProperty == null) {
                return false;
            }
            if (notchProperty.equals("1")) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            Slog.e(TAG, "isNotchScreen", e2);
            return false;
        }
    }

    public static int getNotchSize(Context context) {
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context, int defValue) {
        return getStatusBarHeight(context, defValue, true);
    }

    public static int getStatusBarHeight(Context context, int defValue, boolean ignoreVisibility) {
        Insets statusBar;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowMetrics maximumWindowMetrics = wm.getMaximumWindowMetrics();
        if (ignoreVisibility) {
            statusBar = maximumWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());
        } else {
            statusBar = maximumWindowMetrics.getWindowInsets().getInsets(WindowInsets.Type.statusBars());
        }
        if (statusBar.top != 0) {
            return statusBar.top;
        }
        if (statusBar.bottom != 0) {
            return statusBar.bottom;
        }
        if (statusBar.left != 0) {
            return statusBar.left;
        }
        if (statusBar.right != 0) {
            return statusBar.right;
        }
        return defValue;
    }

    public static int getNavigationBarHeight(Context context) {
        return getNavigationBarHeight(context, true);
    }

    public static int getNavigationBarHeight(Context context, boolean ignoreVisibility) {
        Insets navBar;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowMetrics maximumWindowMetrics = wm.getMaximumWindowMetrics();
        if (ignoreVisibility) {
            navBar = maximumWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());
        } else {
            navBar = maximumWindowMetrics.getWindowInsets().getInsets(WindowInsets.Type.navigationBars());
        }
        if (navBar.top != 0) {
            return navBar.top;
        }
        if (navBar.bottom != 0) {
            return navBar.bottom;
        }
        if (navBar.left != 0) {
            return navBar.left;
        }
        if (navBar.right != 0) {
            return navBar.right;
        }
        return 0;
    }

    public static int getDisplayCutoutHeight(Context context, int defValue) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowMetrics maximumWindowMetrics = wm.getMaximumWindowMetrics();
        Insets cutout = maximumWindowMetrics.getWindowInsets().getInsets(WindowInsets.Type.displayCutout());
        if (cutout.top != 0) {
            return cutout.top;
        }
        if (cutout.bottom != 0) {
            return cutout.bottom;
        }
        if (cutout.left != 0) {
            return cutout.left;
        }
        if (cutout.right != 0) {
            return cutout.right;
        }
        return defValue;
    }

    public static int getInsetValueFromServer(Context context, int typeMask) {
        return getInsetValueFromServer(context, typeMask, true);
    }

    public static int getInsetValueFromServer(Context context, int typeMask, boolean ignoreVisibility) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isFullScreenGestureNav(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) != 0;
    }

    private static boolean isHideGestureLine(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "hide_gesture_line", 0) != 0;
    }

    public static int getNavBarResHeight(Context context) {
        int resourceId;
        if (context == null || (resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android")) <= 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public static double getDistance(float x1, float y1, float x2, float y2) {
        double sum = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        return Math.sqrt(sum);
    }

    public static boolean supportFreeFromWindow(Context context, String pkgName) {
        return (context != null && "com.android.systemui".equals(context.getPackageName()) && sAppList.get(pkgName) == null) ? false : true;
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg) {
        return getActivityOptions(context, freeformPkg, false, -1, -1);
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg, int launchBoundsLeft, int launchBoundsTop) {
        return getActivityOptions(context, freeformPkg, false, launchBoundsLeft, launchBoundsTop);
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg, boolean noCheck) {
        return getActivityOptions(context, freeformPkg, noCheck, -1, -1);
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg, boolean noCheck, boolean isMiniFreeformMode) {
        return getActivityOptions(context, freeformPkg, noCheck, -1, -1);
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg, boolean noCheck, boolean isMiniFreeformMode, boolean isDesktopModeActive) {
        return getActivityOptions(context, freeformPkg, noCheck, -1, -1, isDesktopModeActive);
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg, boolean noCheck, int launchBoundsLeft, int launchBoundsTop) {
        throw new RuntimeException("Stub!");
    }

    public static ActivityOptions getActivityOptions(final Context context, final String freeformPkg, boolean noCheck, int launchBoundsLeft, int launchBoundsTop, boolean isDesktopModeActive) {
        int gameKey;
        boolean z2;
        boolean isLaunchMultiWindow;
        boolean isFreeformLandscape;
        if (!isUserAMonkey() && supportFreeform()) {
            context.getResources().getDisplayMetrics();
            try {
                int gameKey2 = Settings.Secure.getIntForUser(context.getContentResolver(), KEY_QUICKREPLY, 0, -2);
                gameKey = gameKey2;
            } catch (Exception e2) {
                e2.printStackTrace();
                gameKey = 0;
            }
            int isScreenProjectionPrivace = Settings.Secure.getInt(context.getContentResolver(), "screen_project_private_on", 0);
            int screenProjectionState = Settings.Secure.getInt(context.getContentResolver(), "screen_project_in_screening", 0);
            if ((((gameKey != 1 && (isScreenProjectionPrivace != 1 || screenProjectionState != 1)) || !supportFreeFromWindow(context, freeformPkg)) && !noCheck) || (!packageSupportFreeform(context, freeformPkg) && !isDesktopModeActive)) {
                z2 = false;
            } else {
                z2 = true;
            }
            boolean isLaunchMultiWindow2 = z2;
            boolean isNormalFreeForm = isNormalFreeForm(context, freeformPkg);
            Log.d(TAG, "isLaunchMultiWindow:" + isLaunchMultiWindow2 + " gameKey:" + gameKey + "isScreenProjectionPrivace:" + isScreenProjectionPrivace + "screenProjectionState:" + screenProjectionState + " noCheck:" + noCheck + " isNormalFreeForm:" + isNormalFreeForm + " isDesktopModeActive:" + isDesktopModeActive);
            if (isLaunchMultiWindow2) {
                Object isInSplitScreenWindowingMode = invoke(ActivityTaskManager.getService(), "isInSplitScreenWindowingMode", new Object[0]);
                if (isInSplitScreenWindowingMode != null) {
                    try {
                        if (((Boolean) isInSplitScreenWindowingMode).booleanValue()) {
                            Log.d(TAG, "current focusStack is DOCKED and will cancel freeform");
                            if (!multiFreeFormSupported(context)) {
                                isLaunchMultiWindow2 = false;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        isLaunchMultiWindow = isLaunchMultiWindow2;
                    }
                }
                isLaunchMultiWindow = isLaunchMultiWindow2;
                if (!isLaunchMultiWindow) {
                    return null;
                }
                ActivityOptions options = ActivityOptions.makeBasic();
                boolean isFreeformLandscape2 = false;
                if (freeformPkg == null) {
                    isFreeformLandscape = false;
                } else {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setPackage(freeformPkg);
                    ResolveInfo rInfo = context.getPackageManager().resolveActivity(intent, 0);
                    if (rInfo != null) {
                        isFreeformLandscape2 = isOrientationLandscape(rInfo.activityInfo.screenOrientation);
                    }
                    isFreeformLandscape = isFreeformLandscape2 | MiuiMultiWindowAdapter.getForceLandscapeApplicationInSystem().contains(freeformPkg);
                }
                options.setLaunchWindowingMode(5);
                Rect launchBounds = getCustomFreeformRect(context, launchBoundsLeft, launchBoundsTop, isFreeformLandscape, freeformPkg, isNormalFreeForm);
                Log.d(TAG, "MiuiMulti::getActivityOptions::launchBounds= " + launchBounds + ",isFreeformLandscape=" + isFreeformLandscape);
                options.setLaunchBounds(launchBounds);
                invoke(options, "setMiuiConfigFlag", 2);
                try {
                    Method method = isMethodExist(options, "getActivityOptionsInjector", (Object) null);
                    if (method != null) {
                        Object invoke = method.invoke(options, new Object[0]);
                        Object[] objArr = new Object[1];
                        try {
                            objArr[0] = Float.valueOf(getOriFreeformScale(context, isFreeformLandscape, isNormalFreeForm, isDesktopModeActive, freeformPkg));
                            invoke(invoke, "setFreeformScale", objArr);
                            invoke(method.invoke(options, new Object[0]), "setNormalFreeForm", Boolean.valueOf(isNormalFreeForm));
                            if (multiFreeFormSupported(context)) {
                                try {
                                    if (getFreeFormAccessibleArea(context, false, isDesktopModeActive).contains(launchBoundsLeft, launchBoundsTop)) {
                                        invoke(method.invoke(options, new Object[0]), "setUseCustomLaunchBounds", true);
                                    }
                                } catch (Exception e4) {
                                }
                            }
                        } catch (Exception e5) {
                        }
                    }
                } catch (Exception e6) {
                }
                Log.d(TAG, "MiuiMulti options: " + options);
                new Thread(new Runnable() { // from class: android.util.MiuiMultiWindowUtils.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MiuiMultiWindowUtils.saveStartFreeform(context, freeformPkg);
                    }
                }).start();
                return options;
            }
            Log.d(TAG, "pkg name: " + freeformPkg + " unsupported freeform mode");
            return null;
        }
        return null;
    }

    public static boolean packageSupportFreeform(Context context, String freeformPkg) {
        List<String> freeformBlackList = getFreeformBlackList(context, null, false);
        return (!freeformBlackList.contains(freeformPkg) && (isForceResizeable() || isPkgActivityResizeable(context, freeformPkg))) || ((isLandscapeGameApp(freeformPkg, context) || isPadScreen(context)));
    }

    public static boolean isNormalFreeForm(Context context, String freeformPkg) {
        List<String> freeformBlackList = getFreeformBlackList(context, null, false);
        return (!freeformBlackList.contains(freeformPkg) && (isForceResizeable() || isPkgActivityResizeable(context, freeformPkg)));
    }

    public static int getCvwLevelType(String packageName, Context context) {
        throw new RuntimeException("Stub!");
    }

    public static Object invoke(Object obj, String methodName, Object... values) {
        try {
            Class clazz = obj.getClass();
            if (values == null) {
                Method method = clazz.getDeclaredMethod(methodName, (Class<?>) null);
                method.setAccessible(true);
                return method.invoke(obj, new Object[0]);
            }
            Class<?>[] argsClass = new Class[values.length];
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2] instanceof Integer) {
                    argsClass[i2] = Integer.TYPE;
                } else if (values[i2] instanceof Boolean) {
                    argsClass[i2] = Boolean.TYPE;
                } else if (values[i2] instanceof Float) {
                    argsClass[i2] = Float.TYPE;
                } else {
                    argsClass[i2] = values[i2].getClass();
                }
            }
            Method method2 = clazz.getDeclaredMethod(methodName, argsClass);
            method2.setAccessible(true);
            return method2.invoke(obj, values);
        } catch (Exception e2) {
            Log.d(TAG, "getDeclaredMethod:" + e2.toString());
            return null;
        }
    }

    public static Method isMethodExist(Object obj, String methodName, Object... values) {
        try {
            Class clazz = obj.getClass();
            if (values == null) {
                Method method = clazz.getDeclaredMethod(methodName, (Class<?>) null);
                method.setAccessible(true);
                return method;
            }
            Class<?>[] argsClass = new Class[values.length];
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2] instanceof Integer) {
                    argsClass[i2] = Integer.TYPE;
                } else if (values[i2] instanceof Boolean) {
                    argsClass[i2] = Boolean.TYPE;
                } else {
                    argsClass[i2] = values[i2].getClass();
                }
            }
            Method method2 = clazz.getDeclaredMethod(methodName, argsClass);
            method2.setAccessible(true);
            return method2;
        } catch (Exception e2) {
            Log.d(TAG, "getDeclaredMethod:" + e2.toString());
            return null;
        }
    }

    public static int moveTaskToStack(int taskId, int stackId, boolean toTop) {
        return -1;
    }

    public static int resizeTask(int taskId, Rect bounds, int resizeMode) {
        return -1;
    }

    public static Rect getFreeformRect(Context context) {
        return getFreeformRect(context, false);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation) {
        return getFreeformRect(context, needDisplayContentRotation, false, false, false, (Rect) null);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, false, false, (Rect) null);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isFreeformLandscape, String packageName) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isFreeformLandscape, packageName, isNormalFreeForm(context, packageName));
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isFreeformLandscape, String packageName, boolean isNormalFreeForm) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, false, isFreeformLandscape, null, packageName, isNormalFreeForm);
    }

    private static JSONObject readFreeFormResolutionArgsFromJson() {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            try {
                try {
                    AssetManager mAssets = Resources.getSystem().getAssets();
                    reader = new BufferedReader(new InputStreamReader(mAssets.open(RESOLUTION_FILE), "UTF-8"));
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        stringBuilder.append(line);
                    }
                    reader.close();
                } catch (Throwable th) {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e2) {
                            Slog.e(TAG, "Failed to close reader", e2);
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                Slog.e(TAG, "Failed to read freeform_resolutions.json", e3);
                if (reader != null) {
                    reader.close();
                }
            }
        } catch (Exception e4) {
            Slog.e(TAG, "Failed to close reader", e4);
        }
        Slog.d(TAG, "freeform resolution args raw data:" + stringBuilder.toString());
        try {
            JSONObject rawData = new JSONObject(stringBuilder.toString());
            return rawData;
        } catch (Exception e5) {
            Slog.e(TAG, "Failed to transfer freeform_resolutions.json", e5);
            return null;
        }
    }

    private static void initFreeFormResolutionArgs() {
        FREEFORM_RESOLUTION_ARGS_ALL = readFreeFormResolutionArgsFromJson();
        initFreeFormResolutionArgsOfDevice(Build.DEVICE);
    }

    private static void initFreeFormResolutionArgsOfDevice(String devicesName) {
        initFreeFormResolutionArgsGeneral(devicesName, false, -1);
    }

    private static void initFreeFormResolutionArgsGeneral(String deviceName, boolean useDefaultArgs, int screenType) {
        JSONObject jSONObject = FREEFORM_RESOLUTION_ARGS_ALL;
        if (jSONObject == null) {
            return;
        }
        try {
            FREEFORM_RESOLUTION_ARGS_OF_DEVICE = jSONObject.getJSONObject(deviceName);
            parseFreeFormResolution(RESOLUTION_ARG_TYPE.FREEFORM);
            parseFreeFormResolution(RESOLUTION_ARG_TYPE.MINI_FREEFORM);
            if (IS_FOLD_SCREEN_DEVICE) {
                if (useDefaultArgs) {
                    if (screenType >= 2) {
                        parseFreeFormResolution(RESOLUTION_ARG_TYPE.FREEFORM_INNER, true);
                        parseFreeFormResolution(RESOLUTION_ARG_TYPE.MINI_FREEFORM_INNER, true);
                    } else {
                        parseFreeFormResolution(RESOLUTION_ARG_TYPE.FREEFORM_OUTER, true);
                        parseFreeFormResolution(RESOLUTION_ARG_TYPE.MINI_FREEFORM_OUTER, true);
                    }
                } else {
                    parseFreeFormResolution(RESOLUTION_ARG_TYPE.FREEFORM_INNER);
                    parseFreeFormResolution(RESOLUTION_ARG_TYPE.FREEFORM_OUTER);
                    parseFreeFormResolution(RESOLUTION_ARG_TYPE.MINI_FREEFORM_INNER);
                    parseFreeFormResolution(RESOLUTION_ARG_TYPE.MINI_FREEFORM_OUTER);
                }
            }
        } catch (JSONException e2) {
            Slog.d(TAG, "initFreeFormResolutionArgs failed, device is " + deviceName);
        }
    }

    private static void initFreeFormResolutionArgsOfDefault(int screenType) {
        if (screenType >= 0 && screenType <= 3) {
            String[] devices = {"narrow_default", "regular_default", "wide_default", "pad_default"};
            initFreeFormResolutionArgsGeneral(devices[screenType], true, screenType);
        }
    }

    private static void parseFreeFormResolution(RESOLUTION_ARG_TYPE type) {
        parseFreeFormResolution(type, false);
    }

    private static void parseFreeFormResolution(RESOLUTION_ARG_TYPE type, boolean useDefaultFoldArgs) {
        JSONObject jSONObject = FREEFORM_RESOLUTION_ARGS_OF_DEVICE;
        if (jSONObject == null) {
            return;
        }
        JSONObject jsonData = null;
        List<Map<Integer, Float>> targetList = null;
        try {
            JSONObject freeformArgs = jSONObject.getJSONObject("freeform_args");
            JSONObject miniFreeformArgs = FREEFORM_RESOLUTION_ARGS_OF_DEVICE.getJSONObject("mini_freeform_args");
            switch (AnonymousClass3.$SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE[type.ordinal()]) {
                case 1:
                    jsonData = freeformArgs;
                    targetList = FREEFORM_RESOLUTION_ARGS;
                    break;
                case 2:
                    jsonData = useDefaultFoldArgs ? freeformArgs : freeformArgs.getJSONObject("inner");
                    targetList = FREEFORM_RESOLUTION_ARGS_INNER;
                    break;
                case 3:
                    jsonData = useDefaultFoldArgs ? freeformArgs : freeformArgs.getJSONObject("outer");
                    targetList = FREEFORM_RESOLUTION_ARGS_OUTER;
                    break;
                case 4:
                    jsonData = miniFreeformArgs;
                    targetList = MINI_FREEFORM_RESOLUTION_ARGS;
                    break;
                case 5:
                    jsonData = useDefaultFoldArgs ? miniFreeformArgs : miniFreeformArgs.getJSONObject("inner");
                    targetList = MINI_FREEFORM_RESOLUTION_ARGS_INNER;
                    break;
                case 6:
                    jsonData = useDefaultFoldArgs ? miniFreeformArgs : miniFreeformArgs.getJSONObject("outer");
                    targetList = MINI_FREEFORM_RESOLUTION_ARGS_OUTER;
                    break;
            }
        } catch (JSONException e2) {
            Log.e(TAG, "Failed to parse freeform resolution");
        }
        parseResolutionJsonData(jsonData, targetList);
    }

    /* renamed from: android.util.MiuiMultiWindowUtils$3, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE;

        static {
            int[] iArr = new int[RESOLUTION_ARG_TYPE.values().length];
            $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE = iArr;
            try {
                iArr[RESOLUTION_ARG_TYPE.FREEFORM.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE[RESOLUTION_ARG_TYPE.FREEFORM_INNER.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE[RESOLUTION_ARG_TYPE.FREEFORM_OUTER.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE[RESOLUTION_ARG_TYPE.MINI_FREEFORM.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE[RESOLUTION_ARG_TYPE.MINI_FREEFORM_INNER.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$util$MiuiMultiWindowUtils$RESOLUTION_ARG_TYPE[RESOLUTION_ARG_TYPE.MINI_FREEFORM_OUTER.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    private static void parseResolutionJsonData(JSONObject jsonData, List<Map<Integer, Float>> target) {
        throw new RuntimeException("Stub!");
    }

    public static float getFreeFormArg(Context context, boolean vertical, boolean landscape, int argType, int screenType, boolean isNormalFreeForm) {
        float freeFormArg = getFreeFormArg(vertical, landscape, argType, screenType);
        if (argType == 2 && !isNormalFreeForm) {
            return calculateScale(context, vertical, landscape, null, freeFormArg);
        }
        return freeFormArg;
    }

    public static float getFreeFormArg(boolean vertical, boolean landscape, int argType, int screenType) {
        List<Map<Integer, Float>> argsList;
        if (IS_FOLD_SCREEN_DEVICE) {
            if ((screenType >= 2 && FREEFORM_RESOLUTION_ARGS_INNER.isEmpty()) || (screenType < 2 && FREEFORM_RESOLUTION_ARGS_OUTER.isEmpty())) {
                initFreeFormResolutionArgsOfDefault(screenType);
            }
        } else if (FREEFORM_RESOLUTION_ARGS.isEmpty()) {
            initFreeFormResolutionArgsOfDefault(screenType);
        }
        if (IS_FOLD_SCREEN_DEVICE) {
            argsList = screenType >= 2 ? FREEFORM_RESOLUTION_ARGS_INNER : FREEFORM_RESOLUTION_ARGS_OUTER;
        } else {
            argsList = FREEFORM_RESOLUTION_ARGS;
        }
        if (argsList.size() < 4) {
            return -1.0f;
        }
        for (Map<Integer, Float> arg : argsList) {
            if (arg.size() < 5) {
                return -1.0f;
            }
        }
        if (vertical && !landscape) {
            return argsList.get(0).get(Integer.valueOf(argType)).floatValue();
        }
        if (!vertical && !landscape) {
            return argsList.get(1).get(Integer.valueOf(argType)).floatValue();
        }
        if (vertical) {
            return argsList.get(2).get(Integer.valueOf(argType)).floatValue();
        }
        return argsList.get(3).get(Integer.valueOf(argType)).floatValue();
    }

    public static float getMiniFreeFormArg(boolean vertical, boolean landscape, int argType, int screenType) {
        List<Map<Integer, Float>> argsList;
        if (IS_FOLD_SCREEN_DEVICE) {
            if ((screenType >= 2 && MINI_FREEFORM_RESOLUTION_ARGS_INNER.isEmpty()) || (screenType < 2 && MINI_FREEFORM_RESOLUTION_ARGS_OUTER.isEmpty())) {
                initFreeFormResolutionArgsOfDefault(screenType);
            }
        } else if (MINI_FREEFORM_RESOLUTION_ARGS.isEmpty()) {
            initFreeFormResolutionArgsOfDefault(screenType);
        }
        if (IS_FOLD_SCREEN_DEVICE) {
            argsList = screenType >= 2 ? MINI_FREEFORM_RESOLUTION_ARGS_INNER : MINI_FREEFORM_RESOLUTION_ARGS_OUTER;
        } else {
            argsList = MINI_FREEFORM_RESOLUTION_ARGS;
        }
        if (argsList.size() < 4) {
            return -1.0f;
        }
        for (Map<Integer, Float> arg : argsList) {
            if (arg.size() < 1) {
                return -1.0f;
            }
        }
        if (vertical && !landscape) {
            return argsList.get(0).get(Integer.valueOf(argType)).floatValue();
        }
        if (!vertical && !landscape) {
            return argsList.get(1).get(Integer.valueOf(argType)).floatValue();
        }
        if (vertical) {
            return argsList.get(2).get(Integer.valueOf(argType)).floatValue();
        }
        return argsList.get(3).get(Integer.valueOf(argType)).floatValue();
    }

    public static float getSmallAspectRatio(boolean landscape, Context context, boolean isNormalFreeForm) {
        float aspectRatio;
        int screenType = getScreenType(context);
        float aspectRatio2 = getMiniFreeFormArg(true, landscape, 0, screenType);
        if (!isNormalFreeForm || aspectRatio2 == -1.0f) {
            int displayWidth = getDisplaySize(context).width();
            int displayHeight = getDisplaySize(context).height();
            if (landscape) {
                aspectRatio = Math.max(displayHeight, displayWidth) / Math.min(displayHeight, displayWidth);
            } else {
                aspectRatio = Math.min(displayHeight, displayWidth) / Math.max(displayHeight, displayWidth);
            }
            return aspectRatio;
        }
        return aspectRatio2;
    }

    public static float getSmallWindowScreenRatio(boolean landscape, Context context) {
        float windowScreenRatio = getMiniFreeFormArg(true, landscape, 0, getScreenType(context));
        if (windowScreenRatio == -1.0f) {
            return 1.0f;
        }
        return windowScreenRatio;
    }

    public static float getRequestedAspectRatio(String packageName) {
        return 0.0f;
    }

    public static float getAspectRatio(boolean vertical, boolean landscape, Context context, String packageName) {
        float aspectRatio = getRequestedAspectRatio(packageName);
        if (aspectRatio != 0.0f) {
            return aspectRatio;
        }
        int screenType = getScreenType(context);
        float aspectRatio2 = getFreeFormArg(vertical, landscape, 0, screenType);
        if (aspectRatio2 == -1.0f) {
            int displayWidth = getDisplaySize(context).width();
            int displayHeight = getDisplaySize(context).height();
            if (landscape) {
                return Math.max(displayHeight, displayWidth) / Math.min(displayHeight, displayWidth);
            }
            if (isGameApp(packageName, context)) {
                return Math.min(displayHeight, displayWidth) / Math.max(displayHeight, displayWidth);
            }
            return aspectRatio2;
        }
        return aspectRatio2;
    }

    private static float getOriginalRatioBeforeScaling(boolean vertical, boolean landscape, int screenType) {
        return getFreeFormArg(vertical, landscape, 1, screenType);
    }

    public static float getFreeFormScale(Context context, boolean vertical, boolean landscape, int screenType) {
        int miui_desktopmode = 0;
        try {
        } catch (Exception e2) {
            Slog.d(TAG, "Failed to read MIUI_DESKTOP_MODE settings " + e2);
        }
        boolean z2 = false;
        if (SystemProperties.getBoolean("ro.config.miui_desktop_mode_enabled", false) && miui_desktopmode != 0) {
            z2 = true;
        }
        boolean isDesktopModeActive = z2;
        return getFreeFormScale(context, vertical, landscape, screenType, true, isDesktopModeActive, null);
    }

    public static float getFreeFormScale(Context context, boolean vertical, boolean landscape, int screenType, boolean isNormalFreeForm) {
        int miui_desktopmode = 0;
        try {
        } catch (Exception e2) {
            Slog.d(TAG, "Failed to read MIUI_DESKTOP_MODE settings " + e2);
        }
        boolean z2 = false;
        if (SystemProperties.getBoolean("ro.config.miui_desktop_mode_enabled", false) && miui_desktopmode != 0) {
            z2 = true;
        }
        boolean isDesktopModeActive = z2;
        return getFreeFormScale(context, vertical, landscape, screenType, isNormalFreeForm, isDesktopModeActive, null);
    }

    private static float getFreeFormScale(Context context, boolean vertical, boolean landscape, int screenType, boolean isNormalFreeForm, boolean isDesktopModeActive, String packageName) {
        float preferredHeight;
        RectF possibleBounds = getPossibleBounds(context, vertical, landscape, packageName, isNormalFreeForm);
        Rect bounds = new Rect();
        possibleBounds.round(bounds);
        if (!isPadScreen(context)) {
            float preferredScale = getFreeFormArg(vertical, landscape, 2, screenType);
            float currentScale = isNormalFreeForm ? preferredScale : calculateScale(context, vertical, landscape, null, preferredScale);
            return reviewFreeFormBounds(bounds, new Rect(), currentScale, getFreeFormAccessibleArea(context, true, isDesktopModeActive));
        }
        Rect displayRect = getDisplaySize(context);
        float target = Math.min(displayRect.width(), displayRect.height()) - PAD_TARGET_SIDE_DIFF;
        int cvwType = getCvwLevelType(packageName, context);
        if (cvwType != 0) {
            preferredHeight = cvwType == 3 ? 0.8f * target : target * PAD_PORTRAIT_FREEFORM_HEIGHT_RATIO;
        } else if (landscape) {
            preferredHeight = 0.8f * target;
        } else {
            preferredHeight = target * PAD_PORTRAIT_FREEFORM_HEIGHT_RATIO;
        }
        float preferredScale2 = preferredHeight / possibleBounds.height();
        Rect accessibleArea = getFreeFormAccessibleArea(context, true, isDesktopModeActive);
        if (isDesktopModeActive) {
            preferredScale2 = adjustFreeFormScaleForAutoLayout(context, bounds, preferredScale2, accessibleArea, packageName);
        }
        return reviewFreeFormBounds(bounds, new Rect(), preferredScale2, accessibleArea);
    }

    public static float calculateScale(Context context, boolean vertical, boolean isFreeformLandscape, String packageName, float normalScale) {
        int targetSide;
        float windowAspectRatio = getAspectRatio(vertical, isFreeformLandscape, context, packageName);
        int screenType = getScreenType(context);
        float originalRatio = getOriginalRatioBeforeScaling(vertical, isFreeformLandscape, screenType);
        int displayWidth = getDisplaySize(context).width();
        int displayHeight = getDisplaySize(context).height();
        if (isFreeformLandscape || screenType == 3) {
            targetSide = Math.max(displayHeight, displayWidth);
        } else {
            targetSide = Math.min(displayHeight, displayWidth);
        }
        RectF normalBounds = new RectF();
        normalBounds.left = 0.0f;
        normalBounds.top = 0.0f;
        normalBounds.right = targetSide * originalRatio;
        normalBounds.bottom = normalBounds.top + (normalBounds.width() / windowAspectRatio);
        RectF scaledBounds = new RectF();
        scaledBounds.left = 0.0f;
        scaledBounds.top = 0.0f;
        scaledBounds.right = isFreeformLandscape ? Math.max(displayHeight, displayWidth) : Math.min(displayHeight, displayWidth);
        scaledBounds.bottom = isFreeformLandscape ? Math.min(displayHeight, displayWidth) : Math.max(displayHeight, displayWidth);
        float possibleScale = normalScale;
        if (screenType == 3) {
            if (!isFreeformLandscape) {
                possibleScale = (normalBounds.height() * normalScale) / scaledBounds.height();
            }
        } else if (isFreeformLandscape) {
            possibleScale = (normalBounds.width() * normalScale) / scaledBounds.width();
        }
        Log.d(TAG, "Calculated Scale = " + possibleScale);
        return possibleScale;
    }

    private static int getTopMargin(Context context, boolean vertical, boolean freeformLandscape, int screenType, int displayShortSide, int displayLongSide, float freeFormHeight, Rect accessibleArea) {
        if (isPadScreen(context)) {
            int top = (int) (((accessibleArea.height() - freeFormHeight) / 2.0f) + accessibleArea.top + 0.5f);
            return top;
        }
        if (!vertical && getScreenType(context) <= 1) {
            int top2 = (int) ((displayShortSide - freeFormHeight) / 2.0f);
            return top2;
        }
        float topMarginRatio = getFreeFormArg(vertical, freeformLandscape, 3, screenType);
        if (topMarginRatio == -1.0f) {
            if (vertical) {
                return 0;
            }
            return (int) ((displayShortSide - freeFormHeight) / 2.0f);
        }
        return (int) (displayLongSide * topMarginRatio);
    }

    private static int getLeftMargin(Context context, boolean vertical, boolean freeformLandscape, int screenType, int displayShortSide, int displayLongSide, float freeFormWidth) {
        if (isPadScreen(context)) {
            return vertical ? (int) (((displayShortSide - freeFormWidth) - PAD_RIGHT_MARGIN) + 0.5f) : (int) (((displayLongSide - freeFormWidth) - PAD_RIGHT_MARGIN) + 0.5f);
        }
        float leftMarginRatio = getFreeFormArg(vertical, freeformLandscape, 4, screenType);
        if (leftMarginRatio == -1.0f) {
            if (vertical) {
                return (int) ((displayShortSide - freeFormWidth) / 2.0f);
            }
            return 0;
        }
        return (int) (displayLongSide * leftMarginRatio);
    }

    public static float getScalingMaxValue(Context context, boolean vertical, boolean landscape) {
        if (vertical) {
            return landscape ? 1.0f : 1.3007915f;
        }
        if (landscape) {
            return 1.2796443f;
        }
        return isFoldInnerScreen(context) ? 1.3007915f : 1.0f;
    }

    public static float getScalingMinValue(boolean vertical, boolean landscape) {
        if (vertical) {
            if (landscape) {
                return 0.71146244f;
            }
            return 0.6569921f;
        }
        if (landscape) {
            return 0.71146244f;
        }
        return 0.7259475f;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isGameApp(String r5, Context r6) {
        /*
            boolean r0 = android.util.MiuiMultiWindowAdapter.isInTopGameListInSystem(r5)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = 0
            android.content.pm.PackageManager r2 = r6.getPackageManager()     // Catch: java.lang.Exception -> L26
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r5, r0)     // Catch: java.lang.Exception -> L26
            if (r2 == 0) goto L25
            int r3 = r2.category     // Catch: java.lang.Exception -> L26
            if (r3 == 0) goto L21
            int r3 = r2.flags     // Catch: java.lang.Exception -> L26
            r4 = 33554432(0x2000000, float:9.403955E-38)
            r3 = r3 & r4
            if (r3 != r4) goto L1f
            goto L21
        L1f:
            r3 = r0
            goto L22
        L21:
            r3 = r1
        L22:
            if (r3 == 0) goto L25
            return r1
        L25:
            goto L27
        L26:
            r1 = move-exception
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.MiuiMultiWindowUtils.isGameApp(java.lang.String, android.content.Context):boolean");
    }

    public static Rect getDisplaySize(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        return new Rect(0, 0, outMetrics.widthPixels, outMetrics.heightPixels);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0030, code lost:

        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static Rect getDisplaySizeForRotation(Context r5, int r6) {
        /*
            android.util.DisplayMetrics r0 = new android.util.DisplayMetrics
            r0.<init>()
            android.view.Display r1 = r5.getDisplayNoVerify()
            android.view.DisplayStub r1 = r1.mStub
            r1.getDisplayRealMetricsSize(r0)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            int r2 = r0.widthPixels
            int r3 = r0.heightPixels
            int r2 = java.lang.Math.min(r2, r3)
            int r3 = r0.widthPixels
            int r4 = r0.heightPixels
            int r3 = java.lang.Math.max(r3, r4)
            r4 = 0
            switch(r6) {
                case 0: goto L2c;
                case 1: goto L28;
                case 2: goto L2c;
                case 3: goto L28;
                default: goto L27;
            }
        L27:
            goto L30
        L28:
            r1.set(r4, r4, r3, r2)
            goto L30
        L2c:
            r1.set(r4, r4, r2, r3)
        L30:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.MiuiMultiWindowUtils.getDisplaySizeForRotation(android.content.Context, int):android.graphics.Rect");
    }

    public static Rect getFreeFormAccessibleAreaOnPad(Context context, boolean isLandscape, int displayRotation, int statusBarHeight, int navBarHeight, boolean inDesktopMode) {
        boolean displayIsLandscape = true;
        if (displayRotation != 1 && displayRotation != 3) {
            displayIsLandscape = false;
        }
        Rect displaySize = getDisplaySize(context);
        Rect fullScreen = new Rect(displaySize);
        if (isLandscape != displayIsLandscape) {
            fullScreen.set(0, 0, displaySize.height(), displaySize.width());
        }
        Rect accessibleArea = new Rect();
        int notchBarHeight = isNotchScreen(context) ? getInsetValueFromServer(context, WindowInsets.Type.displayCutout(), false) : 0;
        int bottomMargin = Math.max(inDesktopMode ? PAD_FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN_DESKTOP : PAD_FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN, navBarHeight);
        switch (displayRotation) {
            case 0:
                int i2 = PAD_FREEFORM_ACCESSIBLE_AREA_HORIZONTAL_MARGIN;
                accessibleArea.set(i2, Math.max(statusBarHeight, notchBarHeight), fullScreen.right - i2, fullScreen.bottom - bottomMargin);
                break;
            case 1:
                int i3 = PAD_FREEFORM_ACCESSIBLE_AREA_HORIZONTAL_MARGIN;
                accessibleArea.set(Math.max(notchBarHeight, i3), Math.max(statusBarHeight, FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN), fullScreen.right - i3, fullScreen.bottom - bottomMargin);
                break;
            case 2:
                int i4 = PAD_FREEFORM_ACCESSIBLE_AREA_HORIZONTAL_MARGIN;
                accessibleArea.set(i4, statusBarHeight, fullScreen.right - i4, fullScreen.bottom - Math.max(bottomMargin, notchBarHeight));
                break;
            case 3:
                int i5 = PAD_FREEFORM_ACCESSIBLE_AREA_HORIZONTAL_MARGIN;
                accessibleArea.set(i5, Math.max(statusBarHeight, FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN), fullScreen.right - Math.max(notchBarHeight, i5), fullScreen.bottom - bottomMargin);
                break;
        }
        Slog.d(TAG, "getFreeFormAccessibleAreaOnPad::freeform accessibleArea on PAD:" + accessibleArea);
        return accessibleArea;
    }

    public static Rect getFreeFormAccessibleArea(Context context, boolean isDesktopModeActive) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int displayRotation = wm.getDefaultDisplay().getRotation();
        return getFreeFormAccessibleArea(context, displayRotation, isDesktopModeActive);
    }

    public static Rect getFreeFormAccessibleArea(Context context, boolean ignoreVisibility, boolean isDesktopModeActive) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int displayRotation = wm.getDefaultDisplay().getRotation();
        return getFreeFormAccessibleArea(context, displayRotation, ignoreVisibility, isDesktopModeActive);
    }

    public static Rect getFreeFormAccessibleArea(Context context, boolean ignoreVisibility, int statusBarHeight, int navBarHeight, boolean isDesktopModeActive) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int displayRotation = wm.getDefaultDisplay().getRotation();
        return getFreeFormAccessibleArea(context, displayRotation, Math.max(statusBarHeight, getInsetValueFromServer(context, WindowInsets.Type.statusBars(), ignoreVisibility)), Math.max(navBarHeight, getInsetValueFromServer(context, WindowInsets.Type.navigationBars(), ignoreVisibility)), isDesktopModeActive);
    }

    public static Rect getFreeFormAccessibleArea(Context context, int displayRotation, boolean isDesktopModeActive) {
        return getFreeFormAccessibleArea(context, displayRotation, true, isDesktopModeActive);
    }

    public static Rect getFreeFormAccessibleArea(Context context, int displayRotation, boolean ignoreVisibility, boolean isDesktopModeActive) {
        return getFreeFormAccessibleArea(context, displayRotation, getInsetValueFromServer(context, WindowInsets.Type.statusBars(), ignoreVisibility), getInsetValueFromServer(context, WindowInsets.Type.navigationBars(), ignoreVisibility), isDesktopModeActive);
    }

    public static Rect getFreeFormAccessibleArea(Context context, int displayRotation, int statusBarHeight, int navBarHeight, boolean isDesktopModeActive) {
        return getFreeFormAccessibleArea(context, displayRotation, statusBarHeight, navBarHeight, getInsetValueFromServer(context, WindowInsets.Type.displayCutout(), false), isDesktopModeActive);
    }

    public static Rect getFreeFormAccessibleArea(Context context, int displayRotation, int statusBarHeight, int navBarHeight, int notchBar, boolean isDesktopModeActive) {
        boolean z2 = true;
        if (isPadScreen(context)) {
            if (displayRotation != 1 && displayRotation != 3) {
                z2 = false;
            }
            return getFreeFormAccessibleAreaOnPad(context, z2, displayRotation, statusBarHeight, navBarHeight, isDesktopModeActive);
        }
        Rect fullScreen = getDisplaySizeForRotation(context, displayRotation);
        Rect accessibleArea = new Rect();
        boolean isFullScreenGestureNav = isFullScreenGestureNav(context);
        int screenType = getScreenType(context);
        switch (displayRotation) {
            case 0:
                int i2 = PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
                accessibleArea.set(i2, Math.max(notchBar, statusBarHeight), fullScreen.right - i2, fullScreen.bottom - navBarHeight);
                break;
            case 1:
                if (!isFullScreenGestureNav && screenType <= 1) {
                    int i3 = PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
                    accessibleArea.set(Math.max(notchBar, i3), Math.max(statusBarHeight, FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN), fullScreen.right - Math.max(navBarHeight, i3), fullScreen.bottom);
                    break;
                } else {
                    int i4 = PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
                    accessibleArea.set(Math.max(notchBar, i4), Math.max(statusBarHeight, FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN), fullScreen.right - i4, fullScreen.bottom - Math.max(navBarHeight, FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN));
                    break;
                }
            case 2:
                int i5 = PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
                accessibleArea.set(i5, statusBarHeight, fullScreen.right - i5, fullScreen.bottom - Math.max(navBarHeight, notchBar));
                break;
            case 3:
                if (!isFullScreenGestureNav && screenType <= 1) {
                    int i6 = PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
                    accessibleArea.set(Math.max(navBarHeight, i6), Math.max(statusBarHeight, FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN), fullScreen.right - Math.max(notchBar, i6), fullScreen.bottom);
                    break;
                } else {
                    int i7 = PHONE_FREEFORM_ACCESSIBLE_AREA_SIDE_MARGIN;
                    accessibleArea.set(i7, Math.max(statusBarHeight, FREEFORM_ACCESSIBLE_AREA_TOP_MARGIN), fullScreen.right - Math.max(notchBar, i7), fullScreen.bottom - Math.max(navBarHeight, FREEFORM_ACCESSIBLE_AREA_BOTTOM_MARGIN));
                    break;
                }
        }
        return accessibleArea;
    }

    public static int getScreenType(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        int displayWidth = outMetrics.widthPixels;
        int displayHeight = outMetrics.heightPixels;
        int shortSide = Math.min(displayHeight, displayWidth);
        int longSide = Math.max(displayHeight, displayWidth);
        double screenInch = Math.sqrt(Math.pow(shortSide / outMetrics.xdpi, 2.0d) + Math.pow(longSide / outMetrics.ydpi, 2.0d));
        if (shortSide / longSide <= 0.33333334f) {
            return 0;
        }
        if (screenInch < 10.0d && screenInch >= 8.0d) {
            return 2;
        }
        if (screenInch >= 10.0d) {
            return 3;
        }
        return 1;
    }

    public static Boolean isNarrowScreen(Context context) {
        int displayWidth = getDisplaySize(context).width();
        int displayHeight = getDisplaySize(context).height();
        return Boolean.valueOf(((float) Math.min(displayHeight, displayWidth)) / ((float) Math.max(displayHeight, displayWidth)) <= 0.33333334f);
    }

    public static RectF getPossibleBounds(Context context, boolean vertical, boolean isFreeformLandscape, String packageName, boolean isNormalFreeForm) {
        int targetSide;
        float windowAspectRatio = getAspectRatio(vertical, isFreeformLandscape, context, packageName);
        int[] widthAndHeight = ADDITIONAL_FREEFORM_RESOLUTIONS.get(windowAspectRatio);
        if (widthAndHeight != null) {
            return new RectF(0.0f, 0.0f, widthAndHeight[0], widthAndHeight[1]);
        }
        int screenType = getScreenType(context);
        float originalRatio = getOriginalRatioBeforeScaling(vertical, isFreeformLandscape, screenType);
        Log.d(TAG, "originalRatio = " + originalRatio + ", isNormalFreeForm=" + isNormalFreeForm);
        int displayWidth = getDisplaySize(context).width();
        int displayHeight = getDisplaySize(context).height();
        if (isFreeformLandscape || screenType == 3) {
            targetSide = Math.max(displayHeight, displayWidth);
        } else {
            targetSide = Math.min(displayHeight, displayWidth);
        }
        RectF possibleBounds = new RectF();
        possibleBounds.left = 0.0f;
        possibleBounds.top = 0.0f;
        if (!isNormalFreeForm) {
            possibleBounds.right = isFreeformLandscape ? Math.max(displayHeight, displayWidth) : Math.min(displayHeight, displayWidth);
            possibleBounds.bottom = isFreeformLandscape ? Math.min(displayHeight, displayWidth) : Math.max(displayHeight, displayWidth);
        } else if (screenType == 3) {
            if (isFreeformLandscape) {
                if (getCvwLevelType(packageName, context) == 3) {
                    possibleBounds.right = LANDSCAPE_CVW_NEW_FREEFORM_WIDTH_DIP_PAD;
                    possibleBounds.bottom = LANDSCAPE_CVW_NEW_FREEFORM_HEIGHT_DIP_PAD;
                } else {
                    possibleBounds.right = Math.max(displayWidth, displayHeight);
                    possibleBounds.bottom = Math.min(displayWidth, displayHeight);
                }
            } else {
                possibleBounds.right = PORTRAIT_FREEFORM_WIDTH_DIP_PAD;
                possibleBounds.bottom = PORTRAIT_FREEFORM_HEIGHT_DIP_PAD;
            }
        } else {
            possibleBounds.right = targetSide * originalRatio;
            possibleBounds.bottom = possibleBounds.top + (possibleBounds.width() / windowAspectRatio);
        }
        return possibleBounds;
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, null);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, packageName, false, 0);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName, boolean isNormalFreeForm) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, packageName, false, 0, isNormalFreeForm);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName, boolean isSystemServerCalling, boolean avoidNeeded, int statusBarHeight, int navBarHeight) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, packageName, isSystemServerCalling, avoidNeeded, statusBarHeight, navBarHeight, isNormalFreeForm(context, packageName));
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName, boolean isSystemServerCalling, boolean avoidNeeded, int statusBarHeight, int navBarHeight, boolean isNormalFreeForm) {
        int miui_desktopmode = 0;
        try {
        } catch (Exception e2) {
            Slog.d(TAG, "Failed to read MIUI_DESKTOP_MODE settings " + e2);
        }
        boolean z2 = false;
        if (SystemProperties.getBoolean("ro.config.miui_desktop_mode_enabled", false) && miui_desktopmode != 0) {
            z2 = true;
        }
        boolean isDesktopModeActive = z2;
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, packageName, isSystemServerCalling, avoidNeeded, statusBarHeight, navBarHeight, isNormalFreeForm, isDesktopModeActive);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName, boolean isSystemServerCalling, boolean avoidNeeded, int statusBarHeight, int navBarHeight, boolean isNormalFreeForm, boolean isDesktopModeActive) {
        Rect accessibleArea;
        if (context == null) {
            return null;
        }
        int displayWidth = getDisplaySize(context).width();
        int displayHeight = getDisplaySize(context).height();
        int shortSide = Math.min(displayHeight, displayWidth);
        int longSide = Math.max(displayHeight, displayWidth);
        boolean z2 = true;
        boolean verticalScreen = displayWidth <= displayHeight;
        if ((needDisplayContentRotation && !isVertical) || (!needDisplayContentRotation && !verticalScreen)) {
            z2 = false;
        }
        boolean vertical = z2;
        int screenType = getScreenType(context);
        RectF possibleBounds = getPossibleBounds(context, vertical, isFreeformLandscape, packageName, isNormalFreeForm);
        sScale = getFreeFormScale(context, vertical, isFreeformLandscape, screenType, isNormalFreeForm, isDesktopModeActive, packageName);
        if (isSystemServerCalling) {
            accessibleArea = getFreeFormAccessibleArea(context, false, statusBarHeight, navBarHeight, isDesktopModeActive);
        } else {
            accessibleArea = getFreeFormAccessibleArea(context, false, isDesktopModeActive);
        }
        Rect defaultLaunchBounds = new Rect();
        Rect accessibleArea2 = accessibleArea;
        defaultLaunchBounds.left = getLeftMargin(context, vertical, isFreeformLandscape, screenType, shortSide, longSide, possibleBounds.width() * sScale);
        defaultLaunchBounds.top = getTopMargin(context, vertical, isFreeformLandscape, screenType, shortSide, longSide, possibleBounds.height() * sScale, accessibleArea2);
        defaultLaunchBounds.right = (int) (defaultLaunchBounds.left + possibleBounds.width());
        defaultLaunchBounds.bottom = (int) (defaultLaunchBounds.top + possibleBounds.height());
        if (defaultLaunchBounds.left + (possibleBounds.width() * sScale) > accessibleArea2.right) {
            defaultLaunchBounds.offsetTo((int) (accessibleArea2.right - (possibleBounds.width() * sScale)), defaultLaunchBounds.top);
        }
        if (defaultLaunchBounds.top + (possibleBounds.height() * sScale) > accessibleArea2.bottom) {
            defaultLaunchBounds.offsetTo(defaultLaunchBounds.left, (int) (accessibleArea2.bottom - (possibleBounds.height() * sScale)));
        }
        Rect checkedBounds = new Rect();
        sScale = reviewFreeFormBounds(defaultLaunchBounds, checkedBounds, sScale, accessibleArea2);
        defaultLaunchBounds.offsetTo(checkedBounds.left, checkedBounds.top);
        if (multiFreeFormSupported(context) && packageName != null && avoidNeeded) {
            float scale = getOriFreeformScale(context, isFreeformLandscape, isNormalFreeForm, isDesktopModeActive, packageName);
            getAvoidFreeformBounds(context, defaultLaunchBounds, packageName, scale, accessibleArea2);
        }
        Log.d(TAG, "screenType:" + screenType + " launchBounds:" + defaultLaunchBounds + " boundWidth:" + defaultLaunchBounds.width() + " boundHeight:" + defaultLaunchBounds.height() + " sScale:" + sScale + " freeform size:" + (defaultLaunchBounds.width() * sScale) + "," + (defaultLaunchBounds.height() * sScale));
        if (outBounds != null) {
            outBounds.set(defaultLaunchBounds);
        }
        return defaultLaunchBounds;
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName, boolean isSystemServerCalling, int statusBarHeight) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, packageName, isSystemServerCalling, true, statusBarHeight, 0);
    }

    public static Rect getFreeformRect(Context context, boolean needDisplayContentRotation, boolean isVertical, boolean isMiniFreeformMode, boolean isFreeformLandscape, Rect outBounds, String packageName, boolean isSystemServerCalling, int statusBarHeight, boolean isNormalFreeForm) {
        return getFreeformRect(context, needDisplayContentRotation, isVertical, isMiniFreeformMode, isFreeformLandscape, outBounds, packageName, isSystemServerCalling, true, statusBarHeight, 0, isNormalFreeForm);
    }

    public static Rect getCustomFreeformRect(Context context, int left, int top, boolean isFreeformLandscape, String packageName) {
        return getCustomFreeformRect(context, left, top, isFreeformLandscape, packageName, true);
    }

    public static Rect getCustomFreeformRect(Context context, int left, int top, boolean isFreeformLandscape, String packageName, boolean isNormalFreeForm) {
        int miui_desktopmode = 0;
        try {
        } catch (Exception e2) {
            Slog.d(TAG, "Failed to read MIUI_DESKTOP_MODE settings " + e2);
        }
        boolean z2 = false;
        if (SystemProperties.getBoolean("ro.config.miui_desktop_mode_enabled", false) && miui_desktopmode != 0) {
            z2 = true;
        }
        boolean isDesktopModeActive = z2;
        return getCustomFreeformRect(context, left, top, isFreeformLandscape, packageName, isNormalFreeForm, isDesktopModeActive);
    }

    public static Rect getCustomFreeformRect(Context context, int left, int top, boolean isFreeformLandscape, String packageName, boolean isNormalFreeForm, boolean desktopActive) {
        Rect customLaunchBounds;
        float scale;
        int leftMargin;
        int topMargin;
        if (context == null) {
            return null;
        }
        Rect displayRect = getDisplaySize(context);
        int displayWidth = displayRect.width();
        int displayHeight = displayRect.height();
        boolean vertical = displayWidth <= displayHeight;
        Rect accessibleArea = getFreeFormAccessibleArea(context, false, desktopActive);
        RectF possibleBounds = getPossibleBounds(context, vertical, isFreeformLandscape, packageName, isNormalFreeForm);
        int screenType = getScreenType(context);
        int shortSide = Math.min(displayHeight, displayWidth);
        int longSide = Math.max(displayHeight, displayWidth);
        float scale2 = getFreeFormScale(context, vertical, isFreeformLandscape, screenType, isNormalFreeForm, desktopActive, packageName);
        Rect customLaunchBounds2 = new Rect();
        boolean hasCustomLeft = left != -1;
        boolean hasCustomTop = top != -1;
        if (hasCustomLeft) {
            leftMargin = (int) Math.min(Math.max(left, possibleBounds.left), accessibleArea.right);
            customLaunchBounds = customLaunchBounds2;
            scale = scale2;
        } else {
            customLaunchBounds = customLaunchBounds2;
            scale = scale2;
            leftMargin = getLeftMargin(context, vertical, isFreeformLandscape, screenType, shortSide, longSide, possibleBounds.width() * scale2);
        }
        customLaunchBounds.left = leftMargin;
        if (hasCustomTop) {
            topMargin = (int) Math.min(Math.max(top, possibleBounds.top), accessibleArea.bottom);
        } else {
            topMargin = getTopMargin(context, vertical, isFreeformLandscape, screenType, shortSide, longSide, possibleBounds.height() * scale, accessibleArea);
        }
        customLaunchBounds.top = topMargin;
        customLaunchBounds.right = (int) (customLaunchBounds.left + possibleBounds.width());
        customLaunchBounds.bottom = (int) (customLaunchBounds.top + possibleBounds.height());
        Rect checkedBounds = new Rect();
        float scale3 = reviewFreeFormBounds(customLaunchBounds, checkedBounds, scale, accessibleArea);
        customLaunchBounds.offsetTo(checkedBounds.left, checkedBounds.top);
        if (!displayRect.contains(left, top) && multiFreeFormSupported(context)) {
            getAvoidFreeformBounds(context, customLaunchBounds, packageName, scale3, accessibleArea);
        }
        Rect customLaunchBounds3 = customLaunchBounds;
        Log.d(TAG, " packageName:" + packageName + " isNormalFreeForm:" + isNormalFreeForm + " desktopActive:" + desktopActive + " custom launchBounds:" + customLaunchBounds3 + " boundWidth:" + customLaunchBounds3.width() + " boundHeight:" + customLaunchBounds3.height() + " scale:" + scale3 + " freeform size:" + (customLaunchBounds3.width() * scale3) + "," + (customLaunchBounds3.height() * scale3));
        return customLaunchBounds3;
    }

    public static void getAvoidFreeformBounds(Context context, Rect outBounds, String packageName, float scale, Rect accessibleArea) {
        MiuiFreeFormManager.MiuiFreeFormStackInfo existingStackInfo = MiuiFreeFormManager.getFreeFormStackToAvoid(context.getDisplay().getDisplayId(), packageName);
        if (existingStackInfo != null) {
            Rect existingVisualBounds = getVisualBounds(existingStackInfo.bounds, existingStackInfo.freeFormScale);
            Rect defaultVisualBounds = getVisualBounds(outBounds, scale);
            if (accessibleArea != null) {
                avoidAsPossible(defaultVisualBounds, existingVisualBounds, accessibleArea);
            }
            outBounds.offsetTo(defaultVisualBounds.left, defaultVisualBounds.top);
        }
    }

    public static void translateToBlankSpace(Rect mobile, Rect fixed, Rect restriction) {
        Rect transactRegion = new Rect();
        if (!transactRegion.setIntersect(mobile, fixed)) {
            return;
        }
        int translateLength = Math.max(restriction.width(), restriction.height());
        int translateDirection = -1;
        if (fixed.left - restriction.left >= mobile.width() && translateLength > mobile.right - fixed.left) {
            translateLength = mobile.right - fixed.left;
            translateDirection = 0;
        }
        if (restriction.right - fixed.right >= mobile.width() && translateLength > fixed.right - mobile.left) {
            translateLength = fixed.right - mobile.left;
            translateDirection = 1;
        }
        if (fixed.top - restriction.top >= mobile.height() && translateLength > mobile.bottom - fixed.top) {
            translateLength = mobile.bottom - fixed.top;
            translateDirection = 2;
        }
        if (restriction.bottom - fixed.bottom >= mobile.height() && translateLength > fixed.bottom - mobile.top) {
            translateLength = fixed.bottom - mobile.top;
            translateDirection = 3;
        }
        switch (translateDirection) {
            case -1:
                return;
            case 0:
                mobile.offset((-translateLength) - PAD_MULTI_FREEFORM_DEFAULT_MARGIN, 0);
                break;
            case 1:
                mobile.offset(PAD_MULTI_FREEFORM_DEFAULT_MARGIN + translateLength, 0);
                break;
            case 2:
                mobile.offset(0, (-translateLength) - PAD_MULTI_FREEFORM_DEFAULT_MARGIN);
                break;
            case 3:
                mobile.offset(0, PAD_MULTI_FREEFORM_DEFAULT_MARGIN + translateLength);
                break;
        }
        if (mobile.left < restriction.left) {
            mobile.offsetTo(restriction.left, mobile.top);
        }
        if (mobile.right > restriction.right) {
            mobile.offsetTo(restriction.right - mobile.width(), mobile.top);
        }
        if (mobile.top < restriction.top) {
            mobile.offsetTo(mobile.left, restriction.top);
        }
        if (mobile.bottom > restriction.bottom) {
            mobile.offsetTo(mobile.left, restriction.bottom - mobile.height());
        }
    }

    public static Rect getVisualBounds(Rect bounds, float scale) {
        return new Rect(bounds.left, bounds.top, (int) (bounds.left + (bounds.width() * scale) + 0.5f), (int) (bounds.top + (bounds.height() * scale) + 0.5f));
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [android.util.MiuiMultiWindowUtils$1PositionItem] */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.util.MiuiMultiWindowUtils$1PositionItem] */
    /* JADX WARN: Type inference failed for: r3v5, types: [android.util.MiuiMultiWindowUtils$1PositionItem] */
    /* JADX WARN: Type inference failed for: r3v7, types: [android.util.MiuiMultiWindowUtils$1PositionItem] */
    /* JADX WARN: Type inference failed for: r3v9, types: [android.util.MiuiMultiWindowUtils$1PositionItem] */
    public static void avoidAsPossibleOnPad(Rect mobile, Rect fixed, Rect restriction) {
        throw new RuntimeException("Stub!");
    }

    public static void avoidIfNeeded(Rect mobile, Rect fixed, Rect restriction) {
        int targetTop;
        int targetLeft;
        Rect transactRegion = new Rect();
        if (!transactRegion.setIntersect(mobile, fixed)) {
            return;
        }
        int i2 = mobile.left - fixed.left;
        int i3 = FREEFORM_RECT_OFFSET_X_ZIZHAN;
        if (i2 < i3 && fixed.right - mobile.right < i3) {
            if ((fixed.right - i3) - mobile.width() < restriction.left && fixed.left + i3 + mobile.width() > restriction.right) {
                targetLeft = (mobile.left >= fixed.left || mobile.right > fixed.right) ? restriction.right - mobile.width() : restriction.left;
            } else {
                int targetLeft2 = fixed.right;
                if ((targetLeft2 - i3) - mobile.width() >= restriction.left && fixed.left + i3 + mobile.width() <= restriction.right) {
                    if (mobile.left < fixed.left && mobile.right <= fixed.right) {
                        targetLeft = (fixed.right - i3) - mobile.width();
                    } else {
                        targetLeft = fixed.left + i3;
                    }
                } else {
                    int targetLeft3 = fixed.right;
                    if ((targetLeft3 - i3) - mobile.width() >= restriction.left) {
                        targetLeft = (fixed.right - i3) - mobile.width();
                    } else {
                        int targetLeft4 = fixed.left;
                        targetLeft = targetLeft4 + i3;
                    }
                }
            }
            mobile.offsetTo(targetLeft, mobile.top);
        }
        int targetLeft5 = mobile.top;
        int i4 = targetLeft5 - fixed.top;
        int i5 = FREEFORM_RECT_OFFSET_Y_ZIZHAN;
        if (i4 < i5 && fixed.bottom - mobile.bottom < i5) {
            if ((fixed.bottom - i5) - mobile.height() < restriction.top && fixed.top + i5 + mobile.height() > restriction.bottom) {
                targetTop = ((mobile.bottom <= fixed.bottom || mobile.top < fixed.top) && !mobile.contains(fixed)) ? restriction.top : restriction.bottom - mobile.height();
            } else {
                int targetTop2 = fixed.bottom;
                if ((targetTop2 - i5) - mobile.height() >= restriction.top && fixed.top + i5 + mobile.height() <= restriction.bottom) {
                    if ((mobile.bottom > fixed.bottom && mobile.top >= fixed.top) || mobile.contains(fixed)) {
                        targetTop = fixed.top + i5;
                    } else {
                        targetTop = (fixed.bottom - i5) - mobile.height();
                    }
                } else {
                    int targetTop3 = fixed.bottom;
                    if ((targetTop3 - i5) - mobile.height() >= restriction.top) {
                        targetTop = (fixed.bottom - i5) - mobile.height();
                    } else {
                        int targetTop4 = fixed.top;
                        targetTop = targetTop4 + i5;
                    }
                }
            }
            mobile.offsetTo(mobile.left, targetTop);
        }
    }

    public static void avoidAsPossible(Rect mobile, Rect fixed, Rect restriction) {
        if (isTablet()) {
            avoidAsPossibleOnPad(mobile, fixed, restriction);
        } else {
            avoidIfNeeded(mobile, fixed, restriction);
        }
    }

    public static void exitFreeFormWindowIfNeeded() {
        try {
            if (ActivityTaskManager.getService().getRootTaskInfo(5, 0) == null) {
                return;
            }
            List<ActivityTaskManager.RootTaskInfo> taskInfos = ActivityTaskManager.getService().getAllRootTaskInfos();
            for (ActivityTaskManager.RootTaskInfo taskInfo : taskInfos) {
                taskInfo.configuration.windowConfiguration.getWindowingMode();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    private static String getActivityName(Context context, ActivityInfo activityInfo) {
        PackageManager pm = context.getPackageManager();
        if (pm != null && activityInfo != null) {
            return activityInfo.applicationInfo.loadLabel(pm).toString();
        }
        return "";
    }

    public static String startSmallFreeformForControlCenter(Context context) {
        return startSmallFreeformVersion2(context, 2, "from_control_center");
    }

    public static void startSmallFreeformForRecent(int taskId, int cornerPosition, Rect fromRect) {
        startMiniFreeform(taskId, cornerPosition, "from_recent", fromRect);
    }

    public static String startSmallFreeformVersion2(Context context, int cornerPosition, String reason) {
        throw new RuntimeException("Stub!");
    }

    private static void startMiniFreeform(int taskId, int cornerPosition, String reason, Rect fromRect) {
        invoke(ActivityTaskManager.getService(), "launchMiniFreeFormWindowVersion2", taskId, cornerPosition, reason, fromRect);
    }

    public static int startSmallFreeform(Context context) {
        if (isUserAMonkey() || isKeyguardLocked(context)) {
            return 2;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Rect rect = new Rect();
        wm.getDefaultDisplay().getRectSize(rect);
        return startSmallFreeform(context, rect, 2, false);
    }

    public static int startSmallFreeform(Context context, Rect rect) {
        return startSmallFreeform(context, rect, 2, true);
    }

    public static int startSmallFreeform(Context context, Rect rect, int cornerPosition) {
        return startSmallFreeform(context, rect, cornerPosition, true);
    }

    public static void startSmallFreeformForRecentMiui14(int taskId, int cornerPosition, Rect fromRect) {
        invoke(ActivityTaskManager.getService(), "launchSmallFreeFormWindowForRecentMiui14", Integer.valueOf(taskId), fromRect, Integer.valueOf(cornerPosition));
    }

    public static int startSmallFreeform(Context context, Rect rect, int cornerPosition, boolean isLaunchFromRecent) {
        Object isPairRootTask;
        try {
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (keyguardManager == null || !keyguardManager.isKeyguardLocked()) {
                ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                IActivityTaskManager activityTaskManager = ActivityTaskManager.getService();
                List<ActivityTaskManager.RootTaskInfo> rootTaskInfos = activityTaskManager.getAllRootTaskInfosOnDisplay(context.getDisplay() != null ? context.getDisplay().getDisplayId() : 0);
                List<MiuiFreeFormManager.MiuiFreeFormStackInfo> freeFormStackInfoList = MiuiFreeFormManager.getAllFreeFormStackInfosOnDisplay(context.getDisplay() != null ? context.getDisplay().getDisplayId() : 0);
                int unReplaceFreeformCount = 0;
                if (freeFormStackInfoList != null) {
                    for (MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo : freeFormStackInfoList) {
                        if (miuiFreeFormStackInfo.isInMiniFreeFormMode() || miuiFreeFormStackInfo.inPinMode) {
                            unReplaceFreeformCount++;
                        }
                    }
                }
                int maxFreeformCount = multiFreeFormSupported(context) ? 2 : 1;
                for (ActivityTaskManager.RootTaskInfo rootTaskInfo : rootTaskInfos) {
                    if (unReplaceFreeformCount < maxFreeformCount) {
                        if (rootTaskInfo.configuration.windowConfiguration.getWindowingMode() == 5) {
                            MiuiFreeFormManager.MiuiFreeFormStackInfo mffs = MiuiFreeFormManager.getFreeFormStackInfoByStackId(rootTaskInfo.taskId);
                            if (!mffs.inPinMode) {
                                if (mffs.isInFreeFormMode()) {
                                    if (hasSmallFreeform()) {
                                        if (isPadScreen(context)) {
                                            Intent intent = new Intent();
//                                            intent.setAction(CameraBlackCoveredManager.ACTION_FULLSCREEN_STATE_CHANGE);
                                            intent.putExtra("state", "toSmallFreeform");
                                            intent.putExtra("rootStackID", rootTaskInfo.taskId);
                                            context.sendBroadcast(intent);
                                            return 1;
                                        }
                                        return 3;
                                    }
                                    if (!isLaunchFromRecent) {
                                        Intent intent2 = new Intent();
//                                        intent2.setAction(CameraBlackCoveredManager.ACTION_FULLSCREEN_STATE_CHANGE);
                                        intent2.putExtra("state", "toSmallFreeform");
                                        intent2.putExtra("rootStackID", rootTaskInfo.taskId);
                                        context.sendBroadcast(intent2);
                                        return 1;
                                    }
                                    if (isLaunchFromRecent) {
                                    }
                                } else if (mffs.isInMiniFreeFormMode()) {
                                    if (!isPadScreen(context)) {
                                        return 3;
                                    }
                                }
                            }
                        }
                        if (rootTaskInfo.configuration.windowConfiguration.getActivityType() == 2 || rootTaskInfo.configuration.windowConfiguration.getActivityType() == 3) {
                            return 2;
                        }
                        if (rootTaskInfo.configuration.windowConfiguration.getWindowingMode() == 1 && rootTaskInfo.childTaskIds != null && rootTaskInfo.childTaskIds.length == 2 && (isPairRootTask = invoke(activityTaskManager, "isPairRootTask", Integer.valueOf(rootTaskInfo.taskId))) != null && ((Boolean) isPairRootTask).booleanValue()) {
                            return 2;
                        }
                        if (rootTaskInfo.configuration.windowConfiguration.getWindowingMode() == 1) {
                            String packageName = ComponentName.unflattenFromString(rootTaskInfo.childTaskNames[0]).getPackageName();
                            if (packageName != null) {
                                saveStartFreeform(context, packageName);
                                ComponentName realActivity = rootTaskInfo.realActivity;
//                                if (realActivity != null && MiuiMultiWindowAdapter.getFreeformResizeableWhiteListInSystem().contains(realActivity.flattenToString())) {
//                                    if ("com.miui.securitycenter/com.miui.applicationlock.ConfirmAccessControl".contains(realActivity.flattenToString())) {
//                                        if (ActivityInfo.isResizeableMode(rootTaskInfo.resizeMode)) {
//                                            invoke(ActivityTaskManager.getService(), "launchSmallFreeFormWindow", rootTaskInfo, rect, Integer.valueOf(cornerPosition));
//                                            return 1;
//                                        }
//                                        return 2;
//                                    }
//                                    invoke(ActivityTaskManager.getService(), "launchSmallFreeFormWindow", rootTaskInfo, rect, Integer.valueOf(cornerPosition));
//                                    return 1;
//                                }
                                if (!getTaskResizeable(activityManager, rootTaskInfo)) {
                                    if (rootTaskInfo.displayId != 2 || !packageName.equals(INTERNAL_DISPLAY_ACTIVITY_PKG_NAME)) {
                                        Slog.d(TAG, "app in black list. packageName: " + packageName);
                                        return 2;
                                    }
                                }
                            }
                            invoke(ActivityTaskManager.getService(), "launchSmallFreeFormWindow", rootTaskInfo, rect, Integer.valueOf(cornerPosition));
                            return 1;
                        }
                    } else {
                        return 2;
                    }
                }
                return 2;
            }
            return 2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 2;
        }
    }

    public static boolean hasSmallFreeform() {
        List<MiuiFreeFormManager.MiuiFreeFormStackInfo> miuiFreeFormStackInfoList = MiuiFreeFormManager.getAllFreeFormStackInfosOnDisplay(-1);
        for (MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo : miuiFreeFormStackInfoList) {
            if (miuiFreeFormStackInfo.windowState == 1 && !miuiFreeFormStackInfo.inPinMode) {
                return true;
            }
        }
        return false;
    }

    public static int startSmallFreeform(Context context, String packageName) {
        if (isUserAMonkey() || isKeyguardLocked(context)) {
            return 2;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Rect rect = new Rect();
        wm.getDefaultDisplay().getRectSize(rect);
        return startSmallFreeform(context, rect, packageName);
    }

    public static int startSmallFreeform(Context context, Rect rect, String packageName) {
        try {
            List<ActivityTaskManager.RootTaskInfo> rootTaskInfos = ActivityTaskManager.getService().getAllRootTaskInfos();
            int flashBackBackgroundStartSwitchState = getFlashBackBackgroundStartSwitchState(context);
            return onStartSmallFreeform(context, rect, packageName, rootTaskInfos, flashBackBackgroundStartSwitchState);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 2;
        }
    }

    private static int getFlashBackBackgroundStartSwitchState(Context context) {
        int flashBackBackgroundStartSwitchState = Settings.System.getInt(context.getContentResolver(), FLASHBACK_BACKGROUND_START_SWITCH, -1);
        if (flashBackBackgroundStartSwitchState == -1) {
            return 0;
        }
        return flashBackBackgroundStartSwitchState;
    }

    private static int onStartSmallFreeform(Context context, Rect rect, String packageName, List<ActivityTaskManager.RootTaskInfo> rootTaskInfos, int flashBackBackgroundStartSwitchState) {
        try {
            List<ActivityManager.RunningTaskInfo> taskInfos = ActivityManager.getService().getTasks(50);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfos) {
                if (taskInfo != null && taskInfo.baseActivity != null) {
                    String taskPackageName = taskInfo.baseActivity.getPackageName();
                    if (!packageName.equals(taskPackageName)) {
                        continue;
                    } else {
                        if (taskInfo.getWindowingMode() == 5) {
                            return 3;
                        }
                        if (onStartSmallFreeformFromBackGroundStatus(taskInfo, flashBackBackgroundStartSwitchState)) {
                            return onStartSmallFreeformFromBackGround(context, rect, packageName, taskInfo);
                        }
                    }
                }
            }
            return 2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 2;
        }
    }

    private static boolean onStartSmallFreeformFromBackGroundStatus(ActivityManager.RunningTaskInfo taskInfo, int flashBackBackgroundStartSwitchState) {
        return (taskInfo.getWindowingMode() != 1 || taskInfo.isVisible || flashBackBackgroundStartSwitchState == 0) ? false : true;
    }

    private static int onStartSmallFreeformFromBackGround(Context context, Rect rect, String packageName, ActivityManager.RunningTaskInfo taskInfo) {
        saveStartFreeform(context, packageName);
        List<String> blackList = getFreeformBlackList(context, null, false);
        if ((blackList != null && blackList.contains(packageName)) || !pkgHasIcon(context, packageName)) {
            Slog.d(TAG, "app in black list. packageName: " + packageName);
            return 2;
        }
        invoke(ActivityTaskManager.getService(), "launchFlashBackFromBackGround", taskInfo, rect, 2);
        return 4;
    }

    public static boolean isTopFocusedStack(int stackId) {
        int topFocusedStackId = -1;
        try {
            topFocusedStackId = ActivityTaskManager.getService().getFocusedRootTaskInfo().taskId;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Slog.d(TAG, "topFocusedStackId: " + topFocusedStackId + " requestStackId " + stackId);
        return topFocusedStackId == stackId;
    }

    private static boolean getTaskResizeable(ActivityManager activityManager, ActivityTaskManager.RootTaskInfo rootTaskInfo) {
        try {
            Method getTaskResizeable = activityManager.getClass().getMethod("getTaskResizeableForFreeform", Integer.TYPE);
            return ((Boolean) getTaskResizeable.invoke(activityManager, Integer.valueOf(rootTaskInfo.childTaskIds[0]))).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static float adjustFreeFormBounds(float[] visualSizeBefore, Rect currentBounds, float currentScale, Rect accessibleAreaBefore, Rect accessibleAreaAfter, int maxWidthLimit) {
        Rect newBounds = new Rect(currentBounds);
        int availableWidthBefore = accessibleAreaBefore.width();
        int availableHeightBefore = accessibleAreaBefore.height();
        int availableWidthAfter = accessibleAreaAfter.width();
        int availableHeightAfter = accessibleAreaAfter.height();
        int boundsWidth = currentBounds.width();
        int boundsHeight = currentBounds.height();
        float currentVisualWidth = boundsWidth * currentScale;
        float currentVisualHeight = boundsHeight * currentScale;
        float scaleDown = (currentVisualWidth > ((float) Math.min(maxWidthLimit, availableWidthAfter)) || currentVisualHeight > ((float) availableHeightAfter)) ? Math.min(Math.min(maxWidthLimit, availableWidthAfter) / currentVisualWidth, availableHeightAfter / currentVisualHeight) : 1.0f;
        int newVisualWidth = (int) (currentVisualWidth * scaleDown);
        int newVisualHeight = (int) (currentVisualHeight * scaleDown);
        if (currentVisualHeight < availableHeightBefore) {
            newBounds.top = (int) ((((availableHeightAfter - newVisualHeight) * (currentBounds.top - accessibleAreaBefore.top)) / (availableHeightBefore - visualSizeBefore[1])) + accessibleAreaAfter.top);
        } else {
            newBounds.top = ((availableHeightAfter - newVisualHeight) / 2) + accessibleAreaAfter.top;
        }
        if (currentVisualWidth < availableWidthBefore) {
            newBounds.left = (int) ((((availableWidthAfter - newVisualWidth) * (currentBounds.left - accessibleAreaBefore.left)) / (availableWidthBefore - visualSizeBefore[0])) + accessibleAreaAfter.left);
        } else {
            newBounds.left = ((availableWidthAfter - newVisualWidth) / 2) + accessibleAreaAfter.left;
        }
        newBounds.right = newBounds.left + boundsWidth;
        newBounds.bottom = newBounds.top + boundsHeight;
        if (newBounds.top + newVisualHeight > accessibleAreaAfter.bottom) {
            newBounds.offsetTo(newBounds.left, accessibleAreaAfter.bottom - newVisualHeight);
        }
        if (newBounds.left + newVisualWidth > accessibleAreaAfter.right) {
            newBounds.offsetTo(accessibleAreaAfter.right - newVisualWidth, newBounds.top);
        }
        if (newBounds.left < accessibleAreaAfter.left) {
            newBounds.offsetTo(accessibleAreaAfter.left, newBounds.top);
        }
        if (newBounds.top < accessibleAreaAfter.top) {
            newBounds.offsetTo(newBounds.left, accessibleAreaAfter.top);
        }
        Log.d(TAG, "adjustFreeFormBounds:: bounds:" + newBounds + " scaleDown:" + scaleDown);
        if (newBounds.left != currentBounds.left || newBounds.top != currentBounds.top) {
            currentBounds.offsetTo(newBounds.left, newBounds.top);
        }
        return scaleDown;
    }

    public static float adjustFreeFormBounds(Rect currentBounds, float currentScale, Rect accessibleAreaBefore, Rect accessibleAreaAfter, int maxWidthLimit) {
        return adjustFreeFormBounds(new float[]{currentBounds.width() * currentScale, currentBounds.height() * currentScale}, currentBounds, currentScale, accessibleAreaBefore, accessibleAreaAfter, maxWidthLimit);
    }

    public static void adjustMiniFreeFormPosition(Rect currentRect, Rect accessibleAreaBefore, Rect accessibleAreaAfter) {
        adjustFreeFormBounds(currentRect, 1.0f, accessibleAreaBefore, accessibleAreaAfter, currentRect.width());
    }

    public static float reviewFreeFormBounds(Rect currentBounds, Rect newBounds, float currentScale, Rect accessibleArea) {
        newBounds.set(currentBounds);
        int boundsWidth = currentBounds.width();
        int boundsHeight = currentBounds.height();
        float currentVisualWidth = boundsWidth * currentScale;
        float currentVisualHeight = boundsHeight * currentScale;
        Rect visualRect = new Rect(currentBounds.left, currentBounds.top, (int) (currentBounds.left + currentVisualWidth), (int) (currentBounds.top + currentVisualHeight));
        if (accessibleArea.contains(visualRect)) {
            return currentScale;
        }
        float scale = 1.0f;
        int availableWidth = accessibleArea.width();
        int availableHeight = accessibleArea.height();
        if (currentVisualWidth > availableWidth) {
            scale = availableWidth / currentVisualWidth;
        }
        if (currentVisualHeight > availableHeight) {
            scale = Math.min(scale, availableHeight / currentVisualHeight);
        }
        float newScale = currentScale * scale;
        int newVisualWidth = (int) (boundsWidth * newScale);
        int newVisualHeight = (int) (boundsHeight * newScale);
        if (newBounds.top + newVisualHeight > accessibleArea.bottom) {
            newBounds.offsetTo(newBounds.left, accessibleArea.bottom - newVisualHeight);
        }
        if (newBounds.left + newVisualWidth > accessibleArea.right) {
            newBounds.offsetTo(accessibleArea.right - newVisualWidth, newBounds.top);
        }
        if (newBounds.left < accessibleArea.left) {
            newBounds.offsetTo(accessibleArea.left, newBounds.top);
        }
        if (newBounds.top < accessibleArea.top) {
            newBounds.offsetTo(newBounds.left, accessibleArea.top);
        }
        return newScale;
    }

    public static float adjustFreeFormScaleForAutoLayout(Context context, Rect currentBounds, float currentScale, Rect accessibleArea, String packageName) {
        getCvwLevelType(packageName, context);
        float interval = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20.0f, Resources.getSystem().getDisplayMetrics());
        int boundsWidth = currentBounds.width();
        int boundsHeight = currentBounds.height();
        float currentVisualWidth = boundsWidth * currentScale;
        float f2 = boundsHeight * currentScale;
        int num = (int) (currentVisualWidth / interval);
        float scale = (num * interval) / boundsWidth;
        Slog.d(TAG, "adjustFreeFormScaleForAutoLayout: old scale= " + currentScale + " new scale = " + scale + " packageName= " + packageName);
        return scale;
    }

    public static float getOriFreeformScale(Context context, boolean isFreeformLandscape, boolean isNormalFreeForm) {
        return getOriFreeformScale(context, isFreeformLandscape, isNormalFreeForm, null);
    }

    public static float getOriFreeformScale(Context context, boolean isFreeformLandscape, boolean isNormalFreeForm, String packageName) {
        int miui_desktopmode = 0;
        try {
//            miui_desktopmode = Settings.System.getIntForUser(context.getContentResolver(), MiuiSettings.System.MIUI_DESKTOP_MODE, -2);
        } catch (Exception e2) {
            Slog.d(TAG, "Failed to read MIUI_DESKTOP_MODE settings " + e2);
        }
        boolean z2 = false;
        if (SystemProperties.getBoolean("ro.config.miui_desktop_mode_enabled", false) && miui_desktopmode != 0) {
            z2 = true;
        }
        boolean isDesktopModeActive = z2;
        return getOriFreeformScale(context, isFreeformLandscape, isNormalFreeForm, isDesktopModeActive, packageName);
    }

    public static float getOriFreeformScale(Context context, boolean isFreeformLandscape, boolean isNormalFreeForm, boolean isDesktopModeActive, String packageName) {
        if (context == null) {
            return 1.0f;
        }
        Rect outRect = getDisplaySize(context);
        int displayWidth = outRect.width();
        int displayHeight = outRect.height();
        boolean vertical = displayWidth < displayHeight;
        float scale = getFreeFormScale(context, vertical, isFreeformLandscape, getScreenType(context), isNormalFreeForm, isDesktopModeActive, packageName);
        Log.d(TAG, "getOriFreeformScale:: scale=" + scale + " isDesktopModeActive:" + isDesktopModeActive + " packageName:" + packageName);
        return scale;
    }

    public static boolean supportPinFreeFormApp() {
        return false;
    }

    public static boolean supportQuickReply() {
        return supportFreeform();
    }

    public static boolean supportForeGroundPin() {
        if (getTotalRam() <= 6) {
            Log.d(TAG, "not supportForeGroundPin for TOTAL_RAM <= 6");
            return false;
        }
//        return MiuiMultiWindowAdapter.getEnableForegroundPin();
        throw new RuntimeException("Stub!");
    }

    public static boolean supportFreeform() {
        return supportMultiWindow();
    }

    private static boolean supportMultiWindow() {
//        if (DeviceLevel.IS_MIUI_LITE_VERSION) {
//            if (DeviceLevel.getMiuiLiteVersion() == 1) {
//                if (isNotSupportFreeformForCheckMemory()) {
//                    return false;
//                }
//                List<String> list = sNotSupportFreeformDeviceList;
//                if (list != null && list.contains(android.os.Build.DEVICE)) {
//                    Log.d(TAG, "not supportFreeform for device: " + android.os.Build.DEVICE);
//                    return false;
//                }
//            } else if (DeviceLevel.getMiuiLiteVersion() == 2) {
//                List<String> list2 = sSupportFreeformDeviceList;
//                if (list2 != null && list2.contains(android.os.Build.DEVICE) && !isNotSupportFreeformForCheckMemory()) {
//                    Slog.d(TAG, "miuiLite version_2 support freeform for satisfy memory");
//                    return true;
//                }
//                Slog.d(TAG, "DeviceLevel.IS_MIUI_LITE_VERSION 2.0 not support miui multiWindow" + android.os.Build.DEVICE);
//                return false;
//            }
//        }
        return true;
    }

    private static boolean isNotSupportFreeformForCheckMemory() {
        if (getTotalRam() <= 3) {
            Log.d(TAG, "not supportFreeform for TOTAL_RAM <= 3");
            return true;
        }
        return false;
    }

    public static int getMaxNumFreeformDesktopMode() {
        if (getTotalRam() < 8) {
            Log.d(TAG, "not support DesktopMode for TOTAL_RAM < 8");
            return 0;
        }
        List<String> list = sNotSupportFreeformDeviceList;
        if (list != null && list.contains(android.os.Build.DEVICE)) {
            Log.d(TAG, "not supportFreeform for device: " + android.os.Build.DEVICE);
            return 0;
        }
        return 4;
    }

    private static int getTotalRam() {
        int i2 = mTotalRamStr;
        if (i2 != 0) {
            return i2;
        }
        if (i2 == 0) {
//            mTotalRamStr = formatSizeWith1024(HardwareInfo.getTotalPhysicalMemory());
            Slog.d(TAG, "getTotalRam formatSizeWith1024: " + mTotalRamStr);
        }
        return mTotalRamStr;
    }

    private static int formatSizeWith1024(long number) {
        float result = (float) number;
        if (result <= 921.6d) {
            return 0;
        }
        float result2 = result / 1024.0f;
        if (result2 <= 921.6d) {
            return 0;
        }
        if (result2 / 1024.0f > 921.6d) {
//            return (int) Math.ceil(r1 / 1024.0f);
        }
        return 0;
    }

    public static boolean isLandscapeGameApp(String pkg, Context context) {
        boolean isLandscape = false;
        if (pkg != null) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setPackage(pkg);
            ResolveInfo rInfo = context.getPackageManager().resolveActivity(intent, 0);
            if (rInfo != null) {
                isLandscape = isOrientationLandscape(rInfo.activityInfo.screenOrientation) || MiuiMultiWindowAdapter.getForceLandscapeApplicationInSystem().contains(pkg);
            }
        }
        return isLandscape && isGameApp(pkg, context);
    }

    public static boolean isOrientationLandscape(int orientation) {
        return orientation == 0 || orientation == 6 || orientation == 8 || orientation == 11;
    }

    public static boolean isOrientationPortrait(int orientation) {
        return orientation == 1 || orientation == 7 || orientation == 9 || orientation == 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void saveStartFreeform(Context context, String packageName) {
        HashMap<String, LinkedList<Long>> allTimestamps = readFreeformTimestamps(context);
        LinkedList<Long> freeformTimestamps = allTimestamps.get(packageName);
        if (freeformTimestamps == null) {
            freeformTimestamps = new LinkedList<>();
        }
        if (freeformTimestamps.size() == 10) {
            freeformTimestamps.removeFirst();
        }
        freeformTimestamps.addLast(Long.valueOf(System.currentTimeMillis()));
        allTimestamps.put(packageName, freeformTimestamps);
        saveFreeformTimestamps(context, allTimestamps);
    }

    public static ArrayList<String> getFreeformSuggestionList(Context context) {
        HashMap<String, LinkedList<Long>> allTimestamps = readFreeformTimestamps(context);
        List<String> freeformBlackList = getFreeformBlackList(context, allTimestamps, true);
//        Map<String, Integer> freeformSuggestionList = MiuiMultiWindowAdapter.calFreeformSuggestionList(context, freeformBlackList, allTimestamps);
        ArrayList<String> result = new ArrayList<>();
//        List<Map.Entry<String, Integer>> list = new ArrayList<>(freeformSuggestionList.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { // from class: android.util.MiuiMultiWindowUtils.2
//            /* JADX DEBUG: Method merged with bridge method: compare(Ljava/lang/Object;Ljava/lang/Object;)I */
//            @Override // java.util.Comparator
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o2.getValue().intValue() - o1.getValue().intValue();
//            }
//        });
//        for (int i2 = 0; i2 < list.size(); i2++) {
//            String packageName = list.get(i2).getKey();
//            result.add(packageName);
//        }
        return result;
    }

    public static List<String> getFreeformBlackList(Context context, HashMap<String, LinkedList<Long>> allTimestamps, boolean isNeedUpdateList) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<String> freeformBlackList = new ArrayList<>();
        if (isNeedUpdateList && !DateUtils.isToday(sLastUpdateTime)) {
            updateFreeformTimestamps(context, allTimestamps);
            updateListFromCloud(context, activityManager, freeformBlackList);
        }
        if (freeformBlackList.isEmpty()) {
//            freeformBlackList.addAll(activityManager.getFreeformBlackList());
        }
        return freeformBlackList;
    }

    public static void updateListFromCloud(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<String> freeformBlackList = new ArrayList<>();
        updateListFromCloud(context, activityManager, freeformBlackList);
    }

    private static void updateListFromCloud(Context context, ActivityManager activityManager, List<String> freeformBlackList) {
//        boolean updatelist_success = MiuiMultiWindowAdapter.updateList(activityManager, context, freeformBlackList);
//        if (updatelist_success) {
//            Log.d(TAG, "update freeform list success");
//            sLastUpdateTime = System.currentTimeMillis();
//        } else {
//            Log.d(TAG, "update freeform list fail");
//        }
    }

    private static boolean isThirdPartApp(ApplicationInfo info) {
        if (UserHandle.getAppId(info.uid) >= 10000 && (info.flags & 1) == 0) {
            Log.d(TAG, "packageName: " + info.packageName + " isThirdPartApp: true");
            return true;
        }
        Log.d(TAG, "packageName: " + info.packageName + " isThirdPartApp: false");
        return false;
    }

    public static boolean isEnableAbnormalFreeform(String currentPackage, Context context, List<String> blackList, List<String> whiteList, int enableAbnormalFreeform) {
        if (enableAbnormalFreeform == -1 || enableAbnormalFreeform == 0) {
            return false;
        }
        if (enableAbnormalFreeform == 1) {
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(currentPackage, 0);
                if (isThirdPartApp(ai)) {
                    if (!blackList.contains(currentPackage)) {
                        return true;
                    }
                } else if (whiteList.contains(currentPackage)) {
                    return true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (enableAbnormalFreeform == 2) {
            return true;
        }
        return false;
    }

    private static void updateFreeformTimestamps(Context context, HashMap<String, LinkedList<Long>> allTimestamps) {
        Log.d(TAG, "before updateFreeformTimestamps::allTimestamps = " + allTimestamps);
        Iterator mapIterator = allTimestamps.entrySet().iterator();
        while (mapIterator.hasNext()) {
//            Map.Entry<String, LinkedList<Long>> entry = mapIterator.next();
//            LinkedList<Long> timestamps = entry.getValue();
//            Iterator<Long> listIter = timestamps.iterator();
//            while (listIter.hasNext()) {
//                long timestamp = listIter.next().longValue();
//                if (System.currentTimeMillis() - timestamp >= 604800000) {
//                    listIter.remove();
//                    if (timestamps.isEmpty()) {
//                        mapIterator.remove();
//                    }
//                }
//            }
        }
        saveFreeformTimestamps(context, allTimestamps);
        Log.d(TAG, "after updateFreeformTimestamps::allTimestamps = " + allTimestamps);
    }

    private static void saveFreeformTimestamps(Context context, HashMap<String, LinkedList<Long>> timestamps) {
        if (timestamps.isEmpty()) {
            return;
        }
        JSONArray jArray = new JSONArray();
        jArray.put(timestamps);
        try {
            Settings.Secure.putString(context.getContentResolver(), KEY_FREEFORM_TIMESTAMPS, jArray.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static HashMap<String, LinkedList<Long>> readFreeformTimestamps(Context context) {
        HashMap<String, LinkedList<Long>> result = new HashMap<>();
        String str = Settings.Secure.getString(context.getContentResolver(), KEY_FREEFORM_TIMESTAMPS);
        Log.d(TAG, "readFreeformTimestamps::str = " + str);
        if (str != null) {
            try {
                JSONArray jArray = new JSONArray(str);
                for (int i2 = 0; i2 < jArray.length(); i2++) {
                    JSONObject job = new JSONObject((String) jArray.get(i2));
                    Iterator it = job.keys();
                    while (it.hasNext()) {
                        LinkedList<Long> timestamps = new LinkedList<>();
                        String key = it.next().toString();
                        JSONArray array = (JSONArray) job.get(key);
                        for (int j2 = 0; j2 < array.length(); j2++) {
                            timestamps.add((Long) array.get(j2));
                        }
                        result.put(key, timestamps);
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        Log.d(TAG, "readFreeformTimestamps::result = " + result);
        return result;
    }

    public static List<UsageStats> queryUsageStats(Context context) {
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(4, -1);
        long beginTime = calendar.getTimeInMillis();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        return usageStatsManager.queryUsageStats(1, beginTime, endTime);
    }

    public static boolean pkgHasIcon(Context context, String pkgName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkgName);
//        List<ResolveInfo> infos = context.getPackageManager().queryIntentActivitiesAsUser(intent, 786496, CrossUserUtils.getCurrentUserId());
//        return !infos.isEmpty();
        throw new RuntimeException("Stub!");
    }

    public static boolean isPkgMainActivityResizeable(Context context, String pkgName) {
        return ActivityInfo.isResizeableMode(getActivityInfoResizeMode(Intent.CATEGORY_LAUNCHER, context, pkgName));
    }

    public static boolean isPkgActivityResizeable(Context context, String pkgName) {
        return isPkgMainActivityResizeable(context, pkgName) || ActivityInfo.isResizeableMode(getActivityInfoResizeMode(Intent.CATEGORY_DEFAULT, context, pkgName));
    }

    private static int getActivityInfoResizeMode(String intentCategory, Context context, String pkgName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(intentCategory);
        intent.setPackage(pkgName);
//        List<ResolveInfo> infos = context.getPackageManager().queryIntentActivitiesAsUser(intent, 786496, CrossUserUtils.getCurrentUserId());
//        if (infos == null || infos.size() <= 0 || infos.get(0).activityInfo == null) {
//            return 0;
//        }
//        return infos.get(0).activityInfo.resizeMode;
        throw new RuntimeException("Stub!");
    }

    /* loaded from: classes.dex */
    public static class QuadraticEaseOutInterpolator implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float t2) {
            return (-t2) * (t2 - 2.0f);
        }
    }

    public static boolean checkAppInstalled(Context context, String pkgName) {
        PackageInfo packageInfo;
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        }
        Log.d(TAG, " checkAppInstalled " + pkgName + "is installed");
        return true;
    }

    public static boolean isGlobalLauncher() {
        return GLOBAL_LAUNCHER_PKG_NAME.equals(getLauncherPackageName());
    }

    public static String getLauncherPackageName() {
        return SystemProperties.get("ro.miui.product.home", "com.miui.home");
    }

    public static boolean isUserAMonkey() {
        return false;
    }

    private static boolean isKeyguardLocked(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(KeyguardManager.class);
        return keyguardManager != null && keyguardManager.isKeyguardLocked();
    }

    public static boolean isForceResizeable() {
        return false;
    }

    public static boolean isWideScreen(Configuration configuration) {
        return configuration != null && configuration.smallestScreenWidthDp >= 600;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawableToBitmap(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int width, int height) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.UNKNOWN ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap bitmapZoomByScale(Bitmap srcBitmap, float scaleWidth, float scaleHeight) {
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
        if (bitmap != null) {
            return bitmap;
        }
        return srcBitmap;
    }

    public static void getCvwResizeRegions(RoundedRectF leftRegion, RoundedRectF rightRegion, Rect freeFormVisualRect) {
        leftRegion.set(getCvwResizeRegion(4, freeFormVisualRect));
        rightRegion.set(getCvwResizeRegion(5, freeFormVisualRect));
    }

    public static RoundedRectF getCvwResizeRegion(int resizeRegionType, Rect freeFormVisualRect) {
        RoundedRectF resizeRegion = new RoundedRectF();
        if (resizeRegionType == 4) {
            resizeRegion.set(freeFormVisualRect.left + 33, freeFormVisualRect.bottom - 33, 132.0f, 132.0f, 50.0f);
        } else if (resizeRegionType == 5) {
            resizeRegion.set(freeFormVisualRect.right - 33, freeFormVisualRect.bottom - 33, 132.0f, 132.0f, 50.0f);
        }
        return resizeRegion;
    }

    public static boolean inCvwResizeRegion(Rect freeFormVisualRect, float x2, float y2) {
        RoundedRectF leftResizeRegion = getCvwResizeRegion(4, freeFormVisualRect);
        RoundedRectF rightResizeRegion = getCvwResizeRegion(5, freeFormVisualRect);
        return leftResizeRegion.contains(x2, y2) || rightResizeRegion.contains(x2, y2);
    }

    public static Rect getFreeFormHotSpots(int screenType, int hotSpotType, Rect freeFormVisualRect, Context context) {
        Rect hotSpotRec = new Rect();
        if (screenType == 3 && (hotSpotType == 2 || hotSpotType == 3)) {
            return hotSpotRec;
        }
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        if (screenType == 3) {
            switch (hotSpotType) {
                case 0:
                    left = freeFormVisualRect.left + ((int) ((freeFormVisualRect.width() * 0.255f) / 2.0f));
                    top = freeFormVisualRect.top - 22;
                    right = (int) (freeFormVisualRect.right - ((freeFormVisualRect.width() * 0.255f) / 2.0f));
                    bottom = freeFormVisualRect.top + getTopCaptionViewHeight(context) + 18;
                    break;
                case 1:
                    left = freeFormVisualRect.left + 126;
                    top = (freeFormVisualRect.bottom - 45) - 10;
                    right = freeFormVisualRect.right - 126;
                    bottom = freeFormVisualRect.bottom + 35;
                    break;
                default:
                    return hotSpotRec;
            }
        } else {
            switch (hotSpotType) {
                case 0:
                    int i2 = freeFormVisualRect.left;
                    int i3 = RESIZE_WIDTH;
                    left = i2 + i3;
                    top = freeFormVisualRect.top - HOT_SPACE_OFFSITE;
                    right = freeFormVisualRect.right - i3;
                    bottom = freeFormVisualRect.top + getTopCaptionViewHeight(context);
                    break;
                case 1:
                    int i4 = freeFormVisualRect.left;
                    int i5 = RESIZE_WIDTH;
                    left = i4 + i5 + 1;
                    top = freeFormVisualRect.bottom - 54;
                    right = (freeFormVisualRect.right - i5) - 1;
                    bottom = freeFormVisualRect.bottom + HOT_SPACE_OFFSITE;
                    break;
                case 2:
                    left = freeFormVisualRect.left;
                    top = freeFormVisualRect.bottom - 54;
                    right = left + RESIZE_WIDTH;
                    bottom = freeFormVisualRect.bottom + HOT_SPACE_OFFSITE;
                    break;
                case 3:
                    left = freeFormVisualRect.right - RESIZE_WIDTH;
                    right = freeFormVisualRect.right;
                    top = freeFormVisualRect.bottom - 54;
                    bottom = freeFormVisualRect.bottom + HOT_SPACE_OFFSITE;
                    break;
            }
        }
        hotSpotRec.set(left, top, right, bottom);
        return hotSpotRec;
    }

    /* loaded from: classes.dex */
    public static class RoundedRectF {
        float mCenterX;
        float mCenterY;
        float mHeight;
        float mRadius;
        float mWidth;

        public RoundedRectF() {
        }

        public RoundedRectF(float centerX, float centerY, float width, float height, float radius) {
            this.mCenterX = centerX;
            this.mCenterY = centerY;
            this.mWidth = width;
            this.mHeight = height;
            this.mRadius = radius;
        }

        public void set(RoundedRectF other) {
            set(other.mCenterX, other.mCenterY, other.mWidth, other.mHeight, other.mRadius);
        }

        public void set(float centerX, float centerY, float width, float height, float radius) {
            this.mCenterX = centerX;
            this.mCenterY = centerY;
            this.mWidth = width;
            this.mHeight = height;
            this.mRadius = radius;
        }

        public boolean contains(float x2, float y2) {
            return true;
        }

        public void union(Rect rect) {
            Rect outRect = new Rect(rect);
            if (rect.left < rect.right && rect.top < rect.bottom) {
                float f2 = this.mCenterX;
                float f3 = this.mWidth;
                if (f2 - (f3 / 2.0f) < (f3 / 2.0f) + f2) {
                    float f4 = this.mCenterY;
                    float f5 = this.mHeight;
                    if (f4 - (f5 / 2.0f) < f4 + (f5 / 2.0f)) {
                        if (f2 - (f3 / 2.0f) > rect.left) {
                            outRect.left = rect.left;
                        }
                        if (this.mCenterY - (this.mHeight / 2.0f) > rect.top) {
                            outRect.top = rect.top;
                        }
                        if (this.mCenterX + (this.mWidth / 2.0f) < rect.right) {
                            outRect.right = rect.right;
                        }
                        if (this.mCenterY + (this.mHeight / 2.0f) < rect.bottom) {
                            outRect.bottom = rect.bottom;
                            return;
                        }
                        return;
                    }
                }
                outRect.left = rect.left;
                outRect.top = rect.top;
                outRect.right = rect.right;
                outRect.bottom = rect.bottom;
            }
        }

        public boolean isEmpty() {
            return this.mWidth == 0.0f || this.mHeight == 0.0f;
        }

        public String toString() {
            return "CenterX:" + this.mCenterX + " CenterY:" + this.mCenterY + " Width:" + this.mWidth + " Height:" + this.mHeight + " Radius:" + this.mRadius;
        }
    }

    public static Rect findCVWNearestCorner(Context context, float x2, float y2, boolean isLandcapeFreeform, Rect cvwMiniFreeformBounds, boolean isNormalFreeForm) {
        Rect cornerPoint = findNearestCorner(context, x2, y2, -1, isLandcapeFreeform, isNormalFreeForm);
        Rect displayRect = getDisplaySize(context);
        int screenWidth = displayRect.width();
        int screenHeight = displayRect.height();
        if (x2 > screenWidth / 2) {
            int rightMargin = screenWidth - cornerPoint.right;
            if (y2 <= screenHeight / 2) {
                cornerPoint.set((screenWidth - cvwMiniFreeformBounds.width()) - rightMargin, cornerPoint.top, cornerPoint.right, cornerPoint.top + cvwMiniFreeformBounds.height());
            } else {
                cornerPoint.set((screenWidth - cvwMiniFreeformBounds.width()) - rightMargin, cornerPoint.bottom - cvwMiniFreeformBounds.height(), cornerPoint.right, cornerPoint.bottom);
            }
        } else if (y2 <= screenHeight / 2) {
            cornerPoint.set(cornerPoint.left, cornerPoint.top, cornerPoint.left + cvwMiniFreeformBounds.width(), cornerPoint.top + cvwMiniFreeformBounds.height());
        } else {
            cornerPoint.set(cornerPoint.left, cornerPoint.bottom - cvwMiniFreeformBounds.height(), cornerPoint.left + cvwMiniFreeformBounds.width(), cornerPoint.bottom);
        }
        return cornerPoint;
    }

    public static float getFreeformRoundCorner(Context context) {
        int deviceType = getScreenType(context);
        if (deviceType == 3) {
            return PAD_FREEFORM_ROUND_CORNER / 1.414f;
        }
        return FREEFORM_ROUND_CORNER / 1.414f;
    }

    public static float getSmallFreeformRoundCorner(Context context) {
        return PAD_SMALL_FREEFORM_ROUND_CORNER / 1.414f;
    }

    public static boolean isPadScreen(Context context) {
        return getScreenType(context) == 3;
    }

    public static boolean isFoldInnerScreen(Context context) {
        return IS_FOLD_SCREEN_DEVICE && getScreenType(context) >= 2;
    }

    public static boolean isFoldOuterScreen(Context context) {
        return IS_FOLD_SCREEN_DEVICE && getScreenType(context) < 2;
    }

    public static boolean multiFreeFormSupported(Context context) {
        if (isPadScreen(context) || DEVICES_SUPPORT_MULTI_FREEFORM.contains(Build.DEVICE) || Build.TOTAL_RAM > 4) {
            return true;
        }
        Log.d(TAG, "not multiFreeFormSupported for TOTAL_RAM: " + Build.TOTAL_RAM);
        return false;
    }

    public static boolean isInFullScreenNavHotArea(Context context, float x2, float y2, boolean isInHome) {
        float navWidthOffsetPercent;
        float height;
        if (!isFullScreenGestureNav(context)) {
            return false;
        }
        Rect outRect = getDisplaySize(context);
        int displayWidth = outRect.width();
        int displayHeight = outRect.height();
        boolean vertical = displayWidth < displayHeight;
        RectF homeHandleHotArea = new RectF();
        RectF leftDockHotArea = new RectF();
        RectF rightDockHotArea = new RectF();
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        if (isPadScreen(context) && !isInHome) {
            float homeHandleWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, vertical ? 334.22f : 417.78f, displayMetrics);
            float dockHotAreaWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, vertical ? 135.11f : 191.11f, displayMetrics);
            float height2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 44.0f, displayMetrics);
            homeHandleHotArea.set((displayWidth - homeHandleWidth) / 2.0f, displayHeight - height2, (displayWidth + homeHandleWidth) / 2.0f, displayHeight);
            leftDockHotArea.set(((displayWidth - homeHandleWidth) - (dockHotAreaWidth * 2.0f)) / 2.0f, displayHeight - height2, homeHandleHotArea.left, displayHeight);
            rightDockHotArea.set(homeHandleHotArea.right, displayHeight - height2, homeHandleHotArea.right + dockHotAreaWidth, displayHeight);
        } else {
            boolean hideGestureLine = isHideGestureLine(context);
            if (isPadScreen(context)) {
                navWidthOffsetPercent = vertical ? 0.6f : 0.5f;
                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, hideGestureLine ? 20.0f : 35.0f, displayMetrics);
            } else {
                navWidthOffsetPercent = (vertical || hideGestureLine) ? 1.0f : 0.5f;
                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, hideGestureLine ? 13.0f : 25.0f, displayMetrics);
            }
            float left = (displayWidth - (displayWidth * navWidthOffsetPercent)) / 2.0f;
            float right = (displayWidth / 2) + ((displayWidth * navWidthOffsetPercent) / 2.0f);
            homeHandleHotArea.set(left, displayHeight - height, right, displayHeight);
        }
        if (homeHandleHotArea.contains(x2, y2) || leftDockHotArea.contains(x2, y2) || rightDockHotArea.contains(x2, y2)) {
            Log.d(TAG, "is inFullScreenNavHotArea displayWidth:" + displayWidth + " displayHeight:" + displayHeight + " x:" + x2 + " y:" + y2 + " isInHome:" + isInHome + " homeHandleHotArea:" + homeHandleHotArea + " leftDockHotArea:" + leftDockHotArea + " rightDockHotArea:" + rightDockHotArea);
            return true;
        }
        return false;
    }

    public static boolean isInFullScreenNavHotArea(Context context, float x2, float y2) {
        return isInFullScreenNavHotArea(context, x2, y2, false);
    }

    public static boolean isWideScreen() {
        return false;
    }

    public static boolean isSupportMultiSwitchFeature() {
//        boolean IS_CTS_MODE = !SystemProperties.getBoolean(DisplayModeDirectorImpl.MIUI_OPTIMIZATION_PROP, !"1".equals(SystemProperties.get("ro.miui.cts")));
//        Log.d("IS_CTS_MODE", IS_CTS_MODE + "");
//        Log.d("MULTI_WINDOW_SWITCH_ENABLED", MULTI_WINDOW_SWITCH_ENABLED + "");
//        return MULTI_WINDOW_SWITCH_ENABLED && !IS_CTS_MODE;
        throw new RuntimeException("Stub!");
    }

    public static void refreshMultiWindowSwitchStatus() {
        MULTIWIN_SWITCH_ENABLED = isSupportMultiSwitchFeature();
    }

    public static boolean isSupportMiuiShadow() {
        Class<?>[] parameterTypes = {SurfaceControl.class, Integer.TYPE, Float.TYPE, float[].class, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE};
        try {
            Method setShadowSettingsMethod = getDeclaredMethod(SurfaceControl.Transaction.class, "setShadowSettings", parameterTypes);
            if (setShadowSettingsMethod != null) {
                return true;
            }
            return false;
        } catch (NoSuchMethodException e2) {
            return false;
        }
    }

    public static boolean isSupportMiuiShadowV1() {
        Class<?>[] parameterTypes = {SurfaceControl.class, Float.TYPE, float[].class, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE};
        try {
            Method setShadowSettingsMethod = getDeclaredMethod(SurfaceControl.Transaction.class, "setShadowSettings", parameterTypes);
            if (setShadowSettingsMethod != null) {
                return true;
            }
            return false;
        } catch (NoSuchMethodException e2) {
            return false;
        }
    }

    public static boolean isSupportMiuiShadowV2() {
        Method setShadowSettingsMethod = null;
        if (!MIUI_FREEFORM_SHADOW_V2_SUPPORTED) {
            return false;
        }
        Class<?>[] parameterTypes = {SurfaceControl.class, Integer.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE};
        try {
            setShadowSettingsMethod = getDeclaredMethod(SurfaceControl.Transaction.class, "setMiShadow", parameterTypes);
        } catch (NoSuchMethodException e2) {
        }
        return setShadowSettingsMethod != null;
    }

    public static <T> T callObjectMethod(Object obj, Class<T> cls, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            Method declaredMethod = getDeclaredMethod(obj.getClass(), str, clsArr);
            if (declaredMethod == null) {
                return null;
            }
            declaredMethod.setAccessible(true);
            return (T) declaredMethod.invoke(obj, objArr);
        } catch (NoSuchMethodException e2) {
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Method getDeclaredMethod(Class<?> clazz, String method, Class<?>[] parameterTypes) throws NoSuchMethodException {
        try {
            Method result = clazz.getDeclaredMethod(method, parameterTypes);
            return result;
        } catch (NoSuchMethodException e2) {
            throw e2;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static double mergeXY(float x2, float y2) {
        return Math.sqrt((x2 * x2) + (y2 * y2));
    }

    public static float[] getPredictXY(float x2, float y2, float speedX, float speedY, float friction) {
        float[] predictXY = {calPredict(x2, speedX, friction), calPredict(y2, speedY, friction)};
        return predictXY;
    }

    private static float calPredict(float val, float speed, float friction) {
        return ((-speed) / ((-4.2f) * friction)) + val;
    }

    public static float getPredictY(float x2, float y2, float predictX, float predictY, int screenWidth) {
        float disX = predictX - x2;
        float disY = predictY - y2;
        if (disX > 0.0f) {
            float dis = screenWidth - x2;
            float k2 = disY / disX;
            float targetY = y2 + (dis * k2);
            return targetY;
        }
        if (disX >= 0.0f) {
            return predictY;
        }
        float dis2 = 0.0f - x2;
        float k3 = disY / disX;
        float targetY2 = y2 + (dis2 * k3);
        return targetY2;
    }

    public static boolean outerScreen(float x2, float dis, int screenWidth) {
        return x2 < dis || x2 > ((float) screenWidth) - dis;
    }

    public static <T> T callStaticObjectMethod(Class<?> cls, Class<T> cls2, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return (T) declaredMethod.invoke(null, objArr);
        } catch (Exception e2) {
            Log.d(TAG, "callStaticObjectMethod:" + e2.toString());
            return null;
        }
    }

    public static boolean isSupportLatestVibrate() {
//        return SystemProperties.get("sys.haptic.version", "1.0").equals(HapticCompat.HapticVersion.HAPTIC_VERSION_2);
        throw new RuntimeException("Stub!");
    }

    public static boolean isSupportSplitScreenFeature() {
        return supportMultiWindow();
    }

    public static int getNavBarPosition() {
        IActivityTaskManager activityTaskManager = ActivityTaskManager.getService();
        Object navBarPosition = invoke(activityTaskManager, "getNavBarPosition", (Object) null);
        if (navBarPosition != null) {
            int nav = ((Integer) navBarPosition).intValue();
            return nav;
        }
        return 4;
    }

    public static List<String> getTopSplitPackageNames(Context context) {
        @SuppressLint("WrongConstant") ActivityTaskManager activityTaskManager = (ActivityTaskManager) context.getSystemService(Context.ACTIVITY_TASK_SERVICE);
        Object splitPackageNames = invoke(activityTaskManager, "getTopSplitPackageNames", new Object[0]);
        if (splitPackageNames != null && (splitPackageNames instanceof List)) {
            return (List) splitPackageNames;
        }
        return null;
    }

    public static float applyDip2Px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().getDisplayMetrics());
    }

    public static boolean isMultiWindowSwitchEnabled() {
        return true;
    }

    public static List<String> getDesktopModeLaunchFullscreenAppList() {
//        return MiuiMultiWindowAdapter.getDesktopModeLaunchFullscreenAppList();
        throw new RuntimeException("Stub!");
    }

    public static List<String> getDesktopModeLaunchFullscreenAppListInSystem() {
//        return MiuiMultiWindowAdapter.getDesktopModeLaunchFullscreenAppListInSystem();
        throw new RuntimeException("Stub!");
    }

    public static List<String> getAbnormalFreeformBlackList(boolean fromSystem) {
//        return MiuiMultiWindowAdapter.getAbnormalFreeformBlackList(fromSystem);
        throw new RuntimeException("Stub!");
    }

    public static List<String> getAbnormalFreeformWhiteList(boolean fromSystem) {
//        return MiuiMultiWindowAdapter.getAbnormalFreeformWhiteList(fromSystem);
        throw new RuntimeException("Stub!");
    }

    public static int getEnableAbnormalFreeFormDebug(boolean fromSystem) {
//        return MiuiMultiWindowAdapter.getEnableAbnormalFreeFormDebug(fromSystem);
        throw new RuntimeException("Stub!");
    }

    public static List<String> getDesktopModeLaunchFullscreenNotHideOtherAppList() {
//        return MiuiMultiWindowAdapter.getDesktopModeLaunchFullscreenNotHideOtherAppList();
        throw new RuntimeException("Stub!");
    }

    public static int getTotalMem() {
        return Build.TOTAL_RAM;
    }

    public static int getHapticNormal() {
        return 268435461;
    }

    public static Uri getCloudDataNotifyUri() {
        throw new RuntimeException("Stub!");
    }

    public static String getCloudDataString(Context context, String module, String key, String defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public static ArrayList<String> getListAboutSupportCvwLevelHorizontal() {
        return new ArrayList<>(Arrays.asList());
    }

    public static ArrayList<String> getListAboutSupportCvwLevelVertical() {
        return new ArrayList<>(Arrays.asList());
    }

    public static ArrayList<String> getListAboutSupportCvwLevelFull() {
        return new ArrayList<>(Arrays.asList());
    }

    public static ArrayList<String> getListAboutUnsupportCvwLevelFull() {
        return new ArrayList<>(Arrays.asList());
    }

    public static String getMultiwindowNotSupportCvwString(Context context) {
        throw new RuntimeException("Stub!");
    }
}
