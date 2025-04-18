package miui.os;

import android.content.Context;
import android.os.IPowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class Build extends android.os.Build {
    public static final int DEVICE_HIGH_END = 3;
    public static final int DEVICE_LEVEL_FOR_CPU = 2;
    public static final int DEVICE_LEVEL_FOR_GPU = 3;
    public static final int DEVICE_LEVEL_FOR_RAM = 1;
    public static final int DEVICE_LEVEL_UNKNOWN = -1;
    public static final int DEVICE_LOW_END = 1;
    public static final int DEVICE_MIDDLE_END = 2;
    public static final int DEV_STANDARD_VER_1 = 1;
    public static final boolean HAS_CUST_PARTITION;
    public static final boolean IS_ALPHA_BUILD;
    public static final boolean IS_CDMA;
    public static final boolean IS_CM_COOPERATION;
    public static final boolean IS_CM_CUSTOMIZATION;
    public static final boolean IS_CM_CUSTOMIZATION_TEST;
    public static final boolean IS_CTA_BUILD = false;
    public static final boolean IS_CTS_BUILD;
    public static final boolean IS_CT_CUSTOMIZATION;
    public static final boolean IS_CT_CUSTOMIZATION_TEST;
    public static final boolean IS_CU_CUSTOMIZATION;
    public static final boolean IS_CU_CUSTOMIZATION_TEST;
    public static final boolean IS_DEBUGGABLE;
    public static final boolean IS_DEMO_BUILD;
    public static final boolean IS_DEVELOPMENT_VERSION;
    public static final boolean IS_DEV_VERSION;
    public static final boolean IS_FUNCTION_LIMITED;
    public static final boolean IS_GLOBAL_BUILD;
    public static final boolean IS_HONGMI;
    public static final boolean IS_HONGMI2_TDSCDMA;
    public static final boolean IS_HONGMI_THREE;
    public static final boolean IS_HONGMI_THREEX;
    public static final boolean IS_HONGMI_THREEX_CM;
    public static final boolean IS_HONGMI_THREEX_CT;
    public static final boolean IS_HONGMI_THREEX_CU;
    public static final boolean IS_HONGMI_THREE_LTE;
    public static final boolean IS_HONGMI_THREE_LTE_CM;
    public static final boolean IS_HONGMI_THREE_LTE_CU;
    public static final boolean IS_HONGMI_TWO;
    public static final boolean IS_HONGMI_TWOS_LTE_MTK;
    public static final boolean IS_HONGMI_TWOX;
    public static final boolean IS_HONGMI_TWOX_BR;
    public static final boolean IS_HONGMI_TWOX_CM;
    public static final boolean IS_HONGMI_TWOX_CT;
    public static final boolean IS_HONGMI_TWOX_CU;
    public static final boolean IS_HONGMI_TWOX_IN;
    public static final boolean IS_HONGMI_TWOX_LC;
    public static final boolean IS_HONGMI_TWOX_SA;
    public static final boolean IS_HONGMI_TWO_A;
    public static final boolean IS_HONGMI_TWO_S;
    public static final boolean IS_INTERNATIONAL_BUILD;
    public static final boolean IS_MI1S;
    public static final boolean IS_MI2A;
    public static final boolean IS_MIFIVE;
    public static final boolean IS_MIFOUR;
    public static final boolean IS_MIFOUR_CDMA;
    public static final boolean IS_MIFOUR_LTE_CM;
    public static final boolean IS_MIFOUR_LTE_CT;
    public static final boolean IS_MIFOUR_LTE_CU;
    public static final boolean IS_MIFOUR_LTE_INDIA;
    public static final boolean IS_MIFOUR_LTE_SEASA;
    public static final boolean IS_MIONE;
    public static final boolean IS_MIONE_CDMA;
    public static final boolean IS_MIPAD;
    public static final boolean IS_MITHREE;
    public static final boolean IS_MITHREE_CDMA;
    public static final boolean IS_MITHREE_TDSCDMA;
    public static final boolean IS_MITWO;
    public static final boolean IS_MITWO_CDMA;
    public static final boolean IS_MITWO_TDSCDMA;
    public static final boolean IS_MIUI;
    public static final boolean IS_MIUI_LITE_VERSION;
    public static final boolean IS_N7;
    public static final boolean IS_OFFICIAL_VERSION;
    public static final boolean IS_PRE_VERSION;
    public static final boolean IS_PRIVATE_BUILD;
    public static final boolean IS_PRIVATE_WATER_MARKER;
    public static final boolean IS_PRO_DEVICE;
    public static final boolean IS_STABLE_VERSION;
    public static final boolean IS_TABLET;
    public static final boolean IS_TDS_CDMA;
    public static final boolean IS_XIAOMI;
    private static final String PROP_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String REGULAR_EXPRESSION_FOR_DEV = "^(V\\d{1,})(\\.\\d{1,})*(\\.DEV)$";
    private static final String REGULAR_EXPRESSION_FOR_PRE = "\\d+(.\\d+){2,}(-internal)?";
    private static final String REGULAR_EXPRESSION_FOR_STABLE = "^(V\\d{1,})(\\.\\d{1,})*(\\.([A-Z]{4,}))$";
    private static final String TAG = "lowmemvalue";
    public static final int TOTAL_RAM;
    public static final String USERDATA_IMAGE_VERSION_CODE;
    public static final String USER_MODE = "persist.sys.user_mode";
    public static final int USER_MODE_ELDER = 1;
    public static final int USER_MODE_NORMAL = 0;

    protected Build() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    static {
        boolean z2 = "mione".equals(DEVICE) || "mione_plus".equals(DEVICE);
        IS_MIONE = z2;
        IS_MI1S = "MI 1S".equals(MODEL) || "MI 1SC".equals(MODEL);
        boolean z3 = "aries".equals(DEVICE) || "taurus".equals(DEVICE) || "taurus_td".equals(DEVICE);
        IS_MITWO = z3;
        IS_MI2A = "MI 2A".equals(MODEL) || "MI 2A TD".equals(MODEL);
        boolean z4 = "pisces".equals(DEVICE) || ("cancro".equals(DEVICE) && MODEL.startsWith("MI 3"));
        IS_MITHREE = z4;
        boolean z5 = "cancro".equals(DEVICE) && MODEL.startsWith("MI 4");
        IS_MIFOUR = z5;
        boolean equals = "virgo".equals(DEVICE);
        IS_MIFIVE = equals;
        IS_XIAOMI = z2 || z3 || z4 || z5 || equals;
        IS_MIPAD = "mocha".equals(DEVICE);
        IS_N7 = "flo".equals(DEVICE);
        boolean equals2 = "armani".equals(DEVICE);
        IS_HONGMI_TWO_A = equals2;
        boolean z6 = "HM2014011".equals(DEVICE) || "HM2014012".equals(DEVICE);
        IS_HONGMI_TWO_S = z6;
        boolean equals3 = "HM2014501".equals(DEVICE);
        IS_HONGMI_TWOS_LTE_MTK = equals3;
        boolean z7 = "HM2013022".equals(DEVICE) || "HM2013023".equals(DEVICE) || equals2 || z6;
        IS_HONGMI_TWO = z7;
        boolean z8 = "lcsh92_wet_jb9".equals(DEVICE) || "lcsh92_wet_tdd".equals(DEVICE);
        IS_HONGMI_THREE = z8;
        boolean equals4 = "dior".equals(DEVICE);
        IS_HONGMI_THREE_LTE = equals4;
        IS_HONGMI_THREE_LTE_CM = equals4 && "LTETD".equals(SystemProperties.get("ro.boot.modem"));
        IS_HONGMI_THREE_LTE_CU = equals4 && "LTEW".equals(SystemProperties.get("ro.boot.modem"));
        boolean equals5 = "HM2014811".equals(DEVICE);
        IS_HONGMI_TWOX_CU = equals5;
        boolean z9 = "HM2014812".equals(DEVICE) || "HM2014821".equals(DEVICE);
        IS_HONGMI_TWOX_CT = z9;
        boolean z10 = "HM2014813".equals(DEVICE) || "HM2014112".equals(DEVICE);
        IS_HONGMI_TWOX_CM = z10;
        boolean equals6 = "HM2014818".equals(DEVICE);
        IS_HONGMI_TWOX_IN = equals6;
        boolean equals7 = "HM2014817".equals(DEVICE);
        IS_HONGMI_TWOX_SA = equals7;
        boolean equals8 = "HM2014819".equals(DEVICE);
        IS_HONGMI_TWOX_BR = equals8;
        boolean z11 = equals5 || z9 || z10 || equals6 || equals7 || equals8;
        IS_HONGMI_TWOX = z11;
        boolean equals9 = "lte26007".equals(DEVICE);
        IS_HONGMI_TWOX_LC = equals9;
        boolean equals10 = "gucci".equals(DEVICE);
        IS_HONGMI_THREEX = equals10;
        IS_HONGMI_THREEX_CM = equals10 && "cm".equals(SystemProperties.get("persist.sys.modem"));
        IS_HONGMI_THREEX_CU = equals10 && "cu".equals(SystemProperties.get("persist.sys.modem"));
        IS_HONGMI_THREEX_CT = equals10 && "ct".equals(SystemProperties.get("persist.sys.modem"));
        IS_HONGMI = z7 || z8 || z11 || equals4 || equals9 || equals3 || equals10;
        boolean z12 = z2 && hasMsm8660Property();
        IS_MIONE_CDMA = z12;
        boolean z13 = z3 && "CDMA".equals(SystemProperties.get("persist.radio.modem"));
        IS_MITWO_CDMA = z13;
        boolean z14 = z4 && "MI 3C".equals(MODEL);
        IS_MITHREE_CDMA = z14;
        boolean z15 = z5 && "CDMA".equals(SystemProperties.get("persist.radio.modem"));
        IS_MIFOUR_CDMA = z15;
        boolean z16 = z3 && "TD".equals(SystemProperties.get("persist.radio.modem"));
        IS_MITWO_TDSCDMA = z16;
        boolean z17 = z4 && "TD".equals(SystemProperties.get("persist.radio.modem"));
        IS_MITHREE_TDSCDMA = z17;
        IS_MIFOUR_LTE_CM = z5 && "LTE-CMCC".equals(SystemProperties.get("persist.radio.modem"));
        IS_MIFOUR_LTE_CU = z5 && "LTE-CU".equals(SystemProperties.get("persist.radio.modem"));
        boolean z18 = z5 && "LTE-CT".equals(SystemProperties.get("persist.radio.modem"));
        IS_MIFOUR_LTE_CT = z18;
        IS_MIFOUR_LTE_INDIA = z5 && "LTE-India".equals(SystemProperties.get("persist.radio.modem"));
        IS_MIFOUR_LTE_SEASA = z5 && "LTE-SEAsa".equals(SystemProperties.get("persist.radio.modem"));
        boolean equals11 = "HM2013022".equals(DEVICE);
        IS_HONGMI2_TDSCDMA = equals11;
        IS_CDMA = z12 || z13 || z14 || z15 || z18;
        IS_TDS_CDMA = z17 || equals11 || z16;
        IS_CU_CUSTOMIZATION = "cu".equals(SystemProperties.get("ro.carrier.name"));
        IS_CM_CUSTOMIZATION = "cm".equals(SystemProperties.get("ro.carrier.name")) && ("cn_chinamobile".equals(SystemProperties.get("ro.miui.cust_variant")) || "cn_cta".equals(SystemProperties.get("ro.miui.cust_variant")));
        IS_CM_COOPERATION = "cm".equals(SystemProperties.get("ro.carrier.name")) && "cn_cmcooperation".equals(SystemProperties.get("ro.miui.cust_variant"));
        IS_CT_CUSTOMIZATION = "ct".equals(SystemProperties.get("ro.carrier.name"));
        boolean z19 = !TextUtils.isEmpty(Build.VERSION.INCREMENTAL) && Build.VERSION.INCREMENTAL.matches(REGULAR_EXPRESSION_FOR_PRE);
        IS_PRE_VERSION = z19;
        boolean z20 = !TextUtils.isEmpty(Build.VERSION.INCREMENTAL) && Build.VERSION.INCREMENTAL.matches(REGULAR_EXPRESSION_FOR_DEV);
        IS_DEV_VERSION = z20;
        IS_DEVELOPMENT_VERSION = z19 || z20;
        boolean z21 = "user".equals(TYPE) && !TextUtils.isEmpty(Build.VERSION.INCREMENTAL) && Build.VERSION.INCREMENTAL.matches(REGULAR_EXPRESSION_FOR_STABLE);
        IS_STABLE_VERSION = z21;
        IS_OFFICIAL_VERSION = z19 || z20 || z21;
        IS_ALPHA_BUILD = SystemProperties.get("ro.product.mod_device", "").endsWith("_alpha");
        IS_DEMO_BUILD = SystemProperties.get("ro.product.mod_device", "").contains("_demo");
        IS_CTS_BUILD = false;
        IS_PRIVATE_BUILD = false;
        IS_PRIVATE_WATER_MARKER = false;
        IS_CM_CUSTOMIZATION_TEST = "cm".equals(SystemProperties.get("ro.cust.test"));
        IS_CU_CUSTOMIZATION_TEST = "cu".equals(SystemProperties.get("ro.cust.test"));
        IS_CT_CUSTOMIZATION_TEST = "ct".equals(SystemProperties.get("ro.cust.test"));
        IS_FUNCTION_LIMITED = false;
        TOTAL_RAM = getTotalPhysicalRam();
        IS_MIUI_LITE_VERSION = isMiuiLiteVersion();
        IS_INTERNATIONAL_BUILD = SystemProperties.get("ro.product.mod_device", "").contains("_global");
        IS_GLOBAL_BUILD = SystemProperties.get("ro.product.mod_device", "").endsWith("_global");
        IS_TABLET = isTablet();
        USERDATA_IMAGE_VERSION_CODE = getUserdataImageVersionCode();
        IS_DEBUGGABLE = SystemProperties.getInt("ro.debuggable", 0) == 1;
        HAS_CUST_PARTITION = SystemProperties.getBoolean("ro.miui.has_cust_partition", false);
        IS_PRO_DEVICE = SystemProperties.get("ro.miui.cust_device", "").endsWith("_pro");
        IS_MIUI = !SystemProperties.get(PROP_MIUI_VERSION_CODE, "").isEmpty();
    }

    public static String getRegion() {
        return SystemProperties.get("ro.miui.region", "CN");
    }

    public static boolean checkRegion(String region) {
        return getRegion().equalsIgnoreCase(region);
    }

    private static boolean hasMsm8660Property() {
        String soc = SystemProperties.get("ro.soc.name");
        return "msm8660".equals(soc) || "unkown".equals(soc);
    }

    private static boolean isTablet() {
        return SystemProperties.get("ro.build.characteristics").contains("tablet");
    }

    public static boolean hasCameraFlash(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static int getUserMode() {
        return SystemProperties.getInt(USER_MODE, 0);
    }

    public static void setUserMode(Context context, int mode) {
        SystemProperties.set(USER_MODE, Integer.toString(mode));
        reboot(false, null, false);
    }

    private static void reboot(boolean confim, String reason, boolean wait) {
        try {
            IPowerManager powermanager = IPowerManager.Stub.asInterface(ServiceManager.getService(Context.POWER_SERVICE));
            if (powermanager != null) {
                powermanager.reboot(confim, reason, wait);
            }
        } catch (RemoteException e2) {
        }
    }

    public static String getCustVariant() {
        if (!IS_INTERNATIONAL_BUILD) {
            return SystemProperties.get("ro.miui.cust_variant", "cn");
        }
        return SystemProperties.get("ro.miui.cust_variant", "hk");
    }

    private static String getUserdataImageVersionCode() {
        String region;
        String versionCodeProperty = SystemProperties.get("ro.miui.userdata_version", "");
        if ("".equals(versionCodeProperty)) {
            return "Unavailable";
        }
        if (IS_INTERNATIONAL_BUILD) {
            region = "global";
        } else {
            region = "cn";
        }
        String carrier = SystemProperties.get("ro.carrier.name", "");
        if (!"".equals(carrier)) {
            carrier = "_" + carrier;
        }
        return String.format("%s(%s%s)", versionCodeProperty, region, carrier);
    }

    public static final String getMiUiVersionCode() {
        return SystemProperties.get(PROP_MIUI_VERSION_CODE, "");
    }

    private static final boolean isMiuiLiteVersion() {
        int lowmem_value = SystemProperties.getInt("ro.config.low_ram.threshold_gb", 0);
        int i2 = TOTAL_RAM;
        return i2 > 0 && i2 <= lowmem_value;
    }

    private static final int getTotalPhysicalRam() {
        try {
            Class clazz = Class.forName("miui.util.HardwareInfo");
            Method method = clazz.getMethod("getTotalPhysicalMemory", (Class<?>) null);
            long totalPhysicalMemory = ((Long) method.invoke(null, (Object) null)).longValue();
            int memory_G = (int) (((totalPhysicalMemory / 1024) / 1024) / 1024);
            return memory_G;
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage());
            return -1;
        }
    }

    public static final int getDeviceLevelForAnimation() {
        int i2 = TOTAL_RAM;
        if (i2 <= 4) {
            return 1;
        }
        if (i2 <= 8) {
            return 2;
        }
        return 3;
    }

    private static final int getDeviceLevelForRAM(int version) {
        if (version == 1) {
            int i2 = TOTAL_RAM;
            if (i2 <= 4) {
                return 1;
            }
            if (i2 <= 6) {
                return 2;
            }
            return 3;
        }
        return -1;
    }

    private static final int getDeviceLevelForCPU(int version) {
        if (version == 1) {
            return 3;
        }
        return -1;
    }

    private static final int getDeviceLevelForGPU(int version) {
        if (version == 1) {
            return 3;
        }
        return -1;
    }

    public static final int getDeviceLevel(int version, int type) {
        if (type == 1) {
            return getDeviceLevelForRAM(version);
        }
        if (type == 2) {
            return getDeviceLevelForCPU(version);
        }
        if (type == 3) {
            return getDeviceLevelForGPU(version);
        }
        return -1;
    }

    public static boolean isUpgradedProduct() {
        return Build.VERSION.DEVICE_INITIAL_SDK_INT > 0 && Build.VERSION.DEVICE_INITIAL_SDK_INT < 34;
    }
}
