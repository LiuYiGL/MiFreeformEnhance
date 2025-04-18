package miui.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ActivityThread;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.util.MiuiMultiWindowUtils;
import android.util.Singleton;
import android.util.Slog;
import android.view.View;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
@SuppressWarnings("all")
public class MiuiFreeFormManager {
    public static final int ACTION_ELUDE_TO_FREEFORM = 19;
    public static final int ACTION_FREEFORM_END_RESIZE = 7;
    public static final int ACTION_FREEFORM_PINED = 9;
    public static final int ACTION_FREEFORM_PINED_TO_MINIFREEFORM_PINED = 15;
    public static final int ACTION_FREEFORM_START_RESIZE = 6;
    public static final int ACTION_FREEFORM_TO_APPEAR = 21;
    public static final int ACTION_FREEFORM_TO_ELUDE = 18;
    public static final int ACTION_FREEFORM_TO_FRONT = 20;
    public static final int ACTION_FREEFORM_TO_FULLSCREEN = 3;
    public static final int ACTION_FREEFORM_TO_MINIFREEFORM = 2;
    public static final int ACTION_FREEFORM_UNPINED = 11;
    public static final int ACTION_FREEFORM_UPDATE_CAPTION_VISIBILITY = 8;
    public static final int ACTION_FULLSCREEN_TO_FREEFORM = 0;
    public static final int ACTION_FULLSCREEN_TO_MINIFREEFORM = 1;
    public static final int ACTION_MINIFREEFORM_PINED = 10;
    public static final int ACTION_MINIFREEFORM_PINED_TO_FREEFORM_PINED = 14;
    public static final int ACTION_MINIFREEFORM_TO_FREEFORM = 4;
    public static final int ACTION_MINIFREEFORM_TO_FULLSCREEN = 5;
    public static final int ACTION_MINI_FREEFORM_UNPINED = 12;
    public static final int ACTION_MINI_PIN_TO_FULLSCREEN = 17;
    public static final int ACTION_NORMAL_PIN_TO_FULLSCREEN = 16;
    public static final int ACTION_REMOVE_FLOATING_PIN_WINDOW = 13;
    public static final int BACKGROUND_PIN = 2;
    public static final int CLICK_FREFORM_TIP_TYPE = 7;
    public static final int CONTROLLER_FREFORM_TIP_TYPE = 9;
    public static final int DOCK_TIP_TYPE_FREEFORM = 5;
    public static final int DOCK_TIP_TYPE_MULTI_TASK = 6;
    public static final int DOCK_TIP_TYPE_SPLIT = 4;
    public static final int FOREGROUND_PIN = 1;
    public static final int FREEFORM_DIALOG_TYPE = 10;
    public static final int FREEFORM_MODE_MINI_PIN = 3;
    public static final int FREEFORM_MODE_NORMAL_PIN = 2;
    public static final int FREFORM_TIP_TYPE = 0;
    public static final int GESTURE_WINDOWING_MODE_FREEFORM = 0;
    public static final int GESTURE_WINDOWING_MODE_SMALL_FREEFORM = 1;
    public static final int GESTURE_WINDOWING_MODE_UNDEFINED = -1;
    public static final int NOTIFICATION_TIP_TYPE = 2;
    public static final int PIN_TIP_TYPE = 3;
    public static final int SIDEBAR_TIP_TYPE = 1;
    public static final int SIDEDOCK_FREFORM_TIP_TYPE = 8;
    public static final int SPLIT_DIALOG_TYPE = 11;
    private static final String TAG = "MiuiFreeFormManager";
    public static final int UNPIN = 0;
    private static final boolean IS_INTERNATIONAL_BUILD = SystemProperties.get("ro.product.mod_device", "").contains("_global");
    private static final Singleton<IMiuiFreeFormManager> IMiuiFreeFormManagerSingleton = null;

    private static boolean hasbind = false;
    private static IInterface mService = null;
    private static final Object mTipLock = new Object();

    private static IMiuiFreeFormManager getService() {
        try {
            return IMiuiFreeFormManagerSingleton.get();
        } catch (Error e2) {
            return null;
        }
    }

    public static void initFreeformGuideTipBinder(ServiceConnection serviceConnection, int type) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
        }
        synchronized (mTipLock) {
            try {
                Intent intent = new Intent().setAction("com.miui.freeform.MiuiFreeFormGuideTipService").setPackage("com.miui.freeform");
                hasbind = ActivityThread.currentApplication().getApplicationContext().bindService(intent, serviceConnection, 1);
            } catch (Exception e2) {
                Log.d(TAG, " failed to init Freeform Guide Tip Binder");
            }
        }
    }

    public static void showFreeFormGuideDialog(final int type) {
//        IMiuiFreeFormGuideTipServices iMiuiFreeFormGuideTipServices;
//        if (getMiuiFreeformVersion() == 2) {
//            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
//        }
//        try {
//            ServiceConnection serviceConnection = new ServiceConnection() { // from class: miui.app.MiuiFreeFormManager.2
//                @Override // android.content.ServiceConnection
//                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                    MiuiFreeFormManager.mService = IMiuiFreeFormGuideTipServices.Stub.asInterface(iBinder);
//                    try {
//                        if (MiuiFreeFormManager.mService != null) {
//                            MiuiFreeFormManager.mService.showFreeFormGuideDialog(type);
//                        }
//                    } catch (RemoteException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//                @Override // android.content.ServiceConnection
//                public void onServiceDisconnected(ComponentName componentName) {
//                    MiuiFreeFormManager.mService = null;
//                    MiuiFreeFormManager.hasbind = false;
//                }
//            };
//            if (!hasbind && !isUnbindService(type)) {
//                initFreeformGuideTipBinder(serviceConnection, type);
//            } else if (hasbind && !isUnbindService(type) && (iMiuiFreeFormGuideTipServices = mService) != null) {
//                iMiuiFreeFormGuideTipServices.showFreeFormGuideDialog(type);
//            } else {
//                unBindTipService(serviceConnection, type);
//            }
//        } catch (Exception e2) {
//            Log.d(TAG, " failed to show dialog");
//        }
        throw new RuntimeException("Stub!!");
    }

    public static void showPinTipView(final int type, final int x2, final int y2, final int pinHeight) {
//        IMiuiFreeFormGuideTipServices iMiuiFreeFormGuideTipServices;
//        if (getMiuiFreeformVersion() == 2) {
//            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
//            return;
//        }
//        try {
//            ServiceConnection serviceConnection = new ServiceConnection() { // from class: miui.app.MiuiFreeFormManager.3
//                @Override // android.content.ServiceConnection
//                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                    MiuiFreeFormManager.mService = IMiuiFreeFormGuideTipServices.Stub.asInterface(iBinder);
//                    try {
//                        if (MiuiFreeFormManager.mService != null) {
//                            MiuiFreeFormManager.mService.showPinTipView(type, x2, y2, pinHeight);
//                        }
//                    } catch (RemoteException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//                @Override // android.content.ServiceConnection
//                public void onServiceDisconnected(ComponentName componentName) {
//                    MiuiFreeFormManager.mService = null;
//                    MiuiFreeFormManager.hasbind = false;
//                }
//            };
//            if (!hasbind && !isUnbindService(type)) {
//                initFreeformGuideTipBinder(serviceConnection, type);
//            } else if (hasbind && !isUnbindService(type) && (iMiuiFreeFormGuideTipServices = mService) != null) {
//                iMiuiFreeFormGuideTipServices.showPinTipView(type, x2, y2, pinHeight);
//            } else {
//                unBindTipService(serviceConnection, type);
//            }
//        } catch (Exception e2) {
//            Log.d(TAG, " failed showFreeFormTipView");
//        }
        throw new RuntimeException("Stub!!");
    }

    public static boolean shouldForegroundPin(int taskId) {
        try {
            return getService().shouldForegroundPin(taskId);
        } catch (Exception e2) {
            Log.d(TAG, " failed shouldForegroundPin");
            return false;
        }
    }

    public static void notifyCameraStateChanged(String packageName, int cameraState) {
        try {
            getService().notifyCameraStateChanged(packageName, cameraState);
        } catch (Exception e2) {
            Log.d(TAG, " failed notifyCameraStateChanged packageName=" + packageName);
        }
    }

    public static boolean hasMiuiFreeformOnShellFeature() {
        try {
            return getService().hasMiuiFreeformOnShellFeature();
        } catch (Exception e2) {
            Log.d(TAG, " failed hasMiuiFreeformOnShellFeature");
            return false;
        }
    }

    public static boolean openCameraInFreeForm(String packageName) {
        try {
            return getService().openCameraInFreeForm(packageName);
        } catch (Exception e2) {
            Log.d(TAG, " failed cameraOpenedInFreeForm packageName=" + packageName, new RuntimeException(TAG));
            return false;
        }
    }

    public static MiuiFreeFormStackInfo getFreeFormStackToAvoid(int displayId, String packageName) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            return getService().getFreeFormStackToAvoid(displayId, packageName);
        } catch (Exception e2) {
            Log.d(TAG, " failed getFreeFormStackToAvoid displayId=" + displayId);
            return null;
        }
    }

    public static List<MiuiFreeFormStackInfo> getAllFreeFormStackInfosOnDisplay(int displayId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            return getService().getAllFreeFormStackInfosOnDisplay(displayId);
        } catch (Exception e2) {
            Log.d(TAG, " failed getAllFreeFormStackInfosOnDisplay displayId=" + displayId);
            return null;
        }
    }

    public static List<MiuiFreeFormStackInfo> getAllFrontFreeFormStackInfosOnDesktopMode(int displayId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            List<MiuiFreeFormStackInfo> listMiuiFreeFormStackInfo = getService().getAllFrontFreeFormStackInfosOnDesktopMode(displayId);
            return listMiuiFreeFormStackInfo;
        } catch (Exception e2) {
            Log.d(TAG, " failed getAllFrontFreeFormStackInfosOnDesktopMode displayId=" + displayId);
            return null;
        }
    }

    public static MiuiFreeFormStackInfo getFreeFormStackInfoByStackId(int stackId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            return getService().getFreeFormStackInfoByStackId(stackId);
        } catch (Exception e2) {
            Log.d(TAG, " failed getFreeFormStackInfoByStackId stackId=" + stackId);
            return null;
        }
    }

    public static MiuiFreeFormStackInfo getFreeFormStackInfoByView(View view) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            IBinder wtoken = view.getWindowToken();
            if (wtoken != null) {
                return getService().getFreeFormStackInfoByWindow(wtoken);
            }
            Method method = View.class.getDeclaredMethod("getAttachedActivity", new Class[0]);
            method.setAccessible(true);
            Activity activity = (Activity) method.invoke(view, new Object[0]);
            if (activity != null) {
                return getFreeFormStackInfoByActivity(activity);
            }
            return null;
        } catch (Exception e2) {
            Log.d(TAG, " failed getFreeFormStackInfo view=" + view);
            return null;
        }
    }

    public static MiuiFreeFormStackInfo getFreeFormStackInfoByActivity(Activity activity) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            Method method = Activity.class.getDeclaredMethod("getActivityToken", new Class[0]);
            method.setAccessible(true);
            IBinder token = (IBinder) method.invoke(activity, new Object[0]);
            if (token != null) {
                return getService().getFreeFormStackInfoByActivity(token);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, " failed getFreeFormStackInfo view=" + activity);
        return null;
    }

    public static void registerFreeformCallback(IFreeformCallback freeformCallback) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
        } else if (freeformCallback != null) {
            try {
                getService().registerFreeformCallback(freeformCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void unregisterFreeformCallback(IFreeformCallback freeformCallback) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
        } else if (freeformCallback != null) {
            try {
                getService().unregisterFreeformCallback(freeformCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean isSupportPin() {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return false;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                return miuiFreeFormManagerService.isSupportPin();
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed isSuppoertPin");
        }
        return false;
    }

    public static int startSmallFreeformFromNotification(Context context) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return 0;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                return miuiFreeFormManagerService.startSmallFreeformFromNotification();
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed startSmallFreeformFromNotification ");
        }
        return 0;
    }

    public static boolean isSupportBubbleNotification() {
        return !IS_INTERNATIONAL_BUILD && isSupportPin();
    }

    public static void showFreeFormTipView(final int type, final int x2, final int y2, final int arrowMode) {
//        IMiuiFreeFormGuideTipServices iMiuiFreeFormGuideTipServices;
//        if (getMiuiFreeformVersion() == 2) {
//            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
//            return;
//        }
//        try {
//            ServiceConnection serviceConnection = new ServiceConnection() { // from class: miui.app.MiuiFreeFormManager.4
//                @Override // android.content.ServiceConnection
//                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                    MiuiFreeFormManager.mService = IMiuiFreeFormGuideTipServices.Stub.asInterface(iBinder);
//                    try {
//                        if (MiuiFreeFormManager.mService != null) {
//                            MiuiFreeFormManager.mService.showFreeFormTipView(type, x2, y2, arrowMode);
//                        }
//                    } catch (RemoteException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//                @Override // android.content.ServiceConnection
//                public void onServiceDisconnected(ComponentName componentName) {
//                    MiuiFreeFormManager.mService = null;
//                    MiuiFreeFormManager.hasbind = false;
//                }
//            };
//            if (!hasbind && !isUnbindService(type)) {
//                initFreeformGuideTipBinder(serviceConnection, type);
//            } else if (hasbind && !isUnbindService(type) && (iMiuiFreeFormGuideTipServices = mService) != null) {
//                iMiuiFreeFormGuideTipServices.showFreeFormTipView(type, x2, y2, arrowMode);
//            } else {
//                unBindTipService(serviceConnection, type);
//            }
//        } catch (Exception e2) {
//            Log.d(TAG, " failed showFreeFormTipView");
//        }
        throw new RuntimeException("Stub!!");
    }

    public static void showFreeFormTipViewByClassName(final int type, final int x2, final int y2, final int arrowMode, final String className) {
//        IMiuiFreeFormGuideTipServices iMiuiFreeFormGuideTipServices;
//        if (getMiuiFreeformVersion() == 2) {
//            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
//            return;
//        }
//        try {
//            ServiceConnection serviceConnection = new ServiceConnection() { // from class: miui.app.MiuiFreeFormManager.5
//                @Override // android.content.ServiceConnection
//                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                    MiuiFreeFormManager.mService = IMiuiFreeFormGuideTipServices.Stub.asInterface(iBinder);
//                    try {
//                        if (MiuiFreeFormManager.mService != null) {
//                            MiuiFreeFormManager.mService.showFreeFormTipViewByClassName(type, x2, y2, arrowMode, className);
//                        }
//                    } catch (RemoteException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//                @Override // android.content.ServiceConnection
//                public void onServiceDisconnected(ComponentName componentName) {
//                    MiuiFreeFormManager.mService = null;
//                    MiuiFreeFormManager.hasbind = false;
//                }
//            };
//            if (!hasbind && !isUnbindService(type)) {
//                initFreeformGuideTipBinder(serviceConnection, type);
//            } else if (hasbind && !isUnbindService(type) && (iMiuiFreeFormGuideTipServices = mService) != null) {
//                iMiuiFreeFormGuideTipServices.showFreeFormTipViewByClassName(type, x2, y2, arrowMode, className);
//            } else {
//                unBindTipService(serviceConnection, type);
//            }
//        } catch (Exception e2) {
//            Log.d(TAG, " failed showFreeFormTipViewByClassName");
//        }
        throw new RuntimeException("Stub!!");
    }

    public static void removeFreeFormTipView(final int type) {
//        IMiuiFreeFormGuideTipServices iMiuiFreeFormGuideTipServices;
//        if (getMiuiFreeformVersion() == 2) {
//            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
//            return;
//        }
//        try {
//            ServiceConnection serviceConnection = new ServiceConnection() { // from class: miui.app.MiuiFreeFormManager.6
//                @Override // android.content.ServiceConnection
//                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                    MiuiFreeFormManager.mService = IMiuiFreeFormGuideTipServices.Stub.asInterface(iBinder);
//                    try {
//                        if (MiuiFreeFormManager.mService != null) {
//                            MiuiFreeFormManager.mService.removeFreeFormTipView(type);
//                        }
//                    } catch (RemoteException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//                @Override // android.content.ServiceConnection
//                public void onServiceDisconnected(ComponentName componentName) {
//                    MiuiFreeFormManager.mService = null;
//                    MiuiFreeFormManager.hasbind = false;
//                }
//            };
//            if (!hasbind && !isUnbindService(type)) {
//                initFreeformGuideTipBinder(serviceConnection, type);
//            } else if (hasbind && !isUnbindService(type) && (iMiuiFreeFormGuideTipServices = mService) != null) {
//                iMiuiFreeFormGuideTipServices.removeFreeFormTipView(type);
//            } else {
//                unBindTipService(serviceConnection, type);
//            }
//        } catch (Exception e2) {
//            Log.d(TAG, " failed removeFreeFormTipView");
//        }
        throw new RuntimeException("Stub!!");
    }

    public static boolean hasTipViewType(final int type) {
//        IMiuiFreeFormGuideTipServices iMiuiFreeFormGuideTipServices;
//        if (getMiuiFreeformVersion() == 2) {
//            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
//            return false;
//        }
//        try {
//            ServiceConnection serviceConnection = new ServiceConnection() { // from class: miui.app.MiuiFreeFormManager.7
//                @Override // android.content.ServiceConnection
//                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                    MiuiFreeFormManager.mService = IMiuiFreeFormGuideTipServices.Stub.asInterface(iBinder);
//                    try {
//                        if (MiuiFreeFormManager.mService != null) {
//                            MiuiFreeFormManager.mService.hasTipViewType(type);
//                        }
//                    } catch (RemoteException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//                @Override // android.content.ServiceConnection
//                public void onServiceDisconnected(ComponentName componentName) {
//                    MiuiFreeFormManager.mService = null;
//                    MiuiFreeFormManager.hasbind = false;
//                }
//            };
//            if (!hasbind && !isUnbindService(type)) {
//                initFreeformGuideTipBinder(serviceConnection, type);
//            } else if (hasbind && !isUnbindService(type) && (iMiuiFreeFormGuideTipServices = mService) != null) {
//                iMiuiFreeFormGuideTipServices.hasTipViewType(type);
//            } else {
//                unBindTipService(serviceConnection, type);
//            }
//        } catch (Exception e2) {
//            Log.d(TAG, " failed hasTipViewType ");
//        }
//        return false;
        throw new RuntimeException("Stub!!");
    }

    public static int getShowGuide(Context context, String typeName) {
        return Settings.Secure.getIntForUser(context.getContentResolver(), typeName, 0, -2);
    }

    public static boolean isUnbindService(int type) {
        Context context = ActivityThread.currentApplication().getApplicationContext();
        switch (type) {
            case 0:
            case 10:
                return getShowGuide(context, "show_guide_freeform") > 3;
            case 1:
                return getShowGuide(context, "show_guide_sidebar") > 1;
            case 2:
                return getShowGuide(context, "show_guide_notification") > 1;
            case 3:
                return getShowGuide(context, "show_guide_pin") > 1;
            case 4:
                return getShowGuide(context, "show_guide_dock_split") > 1;
            case 5:
                return getShowGuide(context, "show_guide_dock_freeform") > 1;
            case 6:
                return getShowGuide(context, "show_guide_dock_multi_task") > 1;
            case 7:
                return getShowGuide(context, "show_guide_freeform_click") > 1;
            case 8:
                return getShowGuide(context, "show_guide_side_dock") > 1;
            case 9:
                return getShowGuide(context, "show_guide_controller") > 1;
            case 11:
                return getShowGuide(context, "show_guide_split") > 1;
            default:
                return false;
        }
    }

    public static void unBindTipService(ServiceConnection serviceConnection, int type) {
        try {
            if (hasbind && isUnbindService(type)) {
                ActivityThread.currentApplication().getApplicationContext().unbindService(serviceConnection);
            }
        } catch (Exception e2) {
            Log.d(TAG, "failed unBindTipService");
        }
    }

    public static List<MiuiFreeFormStackInfo> getAllPinedFreeFormStackInfosOnDisplay(int displayId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return new ArrayList();
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                return miuiFreeFormManagerService.getAllPinedFreeFormStackInfosOnDisplay(displayId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed getAllFreeFormStackInfosOnDisplay displayId=" + displayId);
        }
        return new ArrayList();
    }

    public static int getMiuiFreeformVersion() {
        if (getService() != null) {
            return 3;
        }
        return 2;
    }

    public static int getMiuiFreeformStackShowState(Context context) {
        if (getMiuiFreeformVersion() != 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
        }
        return Settings.Secure.getInt(context.getContentResolver(), MiuiMultiWindowUtils.KEY_FREEFORM_WINDOW_STATE, -1);
    }

    public static String getMiuiFreeformStackPackageName(Context context) {
        if (getMiuiFreeformVersion() != 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
        }
        return Settings.Secure.getString(context.getContentResolver(), MiuiMultiWindowUtils.FREEFORM_PACKAGE_NAME);
    }

    public static boolean supportFreeform() {
//        return MiuiFreeformStub.getInstance().supportFreeform();
        throw new RuntimeException("Stub!!");
    }

    public static ActivityOptions getActivityOptions(Context context, String freeformPkg, boolean noCheck, boolean isMiniFreeformMode) {
//        return MiuiFreeformStub.getInstance().getActivityOptions(context, freeformPkg, noCheck, isMiniFreeformMode);
        throw new RuntimeException("Stub!!");
    }

    public static float getFreeformLastScale(int taskId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return 1.0f;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " getFreeformLastScale taskId=" + taskId);
                return miuiFreeFormManagerService.getFreeformLastScale(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed getFreeformLastScale taskId=" + taskId);
        }
        return 1.0f;
    }

    public static Rect getFreeformLastPosition(int taskId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return null;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " getFreeformLastPosition taskId=" + taskId);
                return miuiFreeFormManagerService.getFreeformLastPosition(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed getFreeformLastPosition taskId=" + taskId);
        }
        return null;
    }

    public static float getFreeformRectDesktop(Rect outBounds, float scale, int taskId, boolean isDefaultPosition) {
        if (getMiuiFreeformVersion() == 2) {
            throw new UnsupportedOperationException("非MIUI13小窗版本,请用MIUI12适配方案进行适配");
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " getFreeformRectDesktop taskId=" + taskId + ", isDefaultPosition=" + isDefaultPosition);
                return miuiFreeFormManagerService.getFreeformRectDesktop(outBounds, scale, taskId, isDefaultPosition);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed getFreeformRectDesktop taskId=" + taskId + ", isDefaultPosition=" + isDefaultPosition);
        }
        return scale;
    }

    public static boolean isSplitRootTask(int taskId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return false;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " isSplitRootTask taskId=" + taskId);
                return miuiFreeFormManagerService.isSplitRootTask(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed isSplitRootTask taskId=" + taskId);
        }
        return false;
    }

    public static boolean hasFreeformDesktopMemory(int taskId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return false;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " hasFreeformDesktopMemory taskId=" + taskId);
                return miuiFreeFormManagerService.hasFreeformDesktopMemory(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed hasFreeformDesktopMemory taskId=" + taskId);
        }
        return false;
    }

    public static void setHideStackFromFullScreen(int taskId, boolean hiden) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " setHideStackFromFullScreen taskId=" + taskId + ", hiden=" + hiden);
                miuiFreeFormManagerService.setHideStackFromFullScreen(taskId, hiden);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed setHideStackFromFullScreen taskId=" + taskId);
        }
    }

    public static boolean isHideStackFromFullScreen(int taskId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return false;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " isHideStackFromFullScreen taskId=" + taskId);
                return miuiFreeFormManagerService.isHideStackFromFullScreen(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed isHideStackFromFullScreen taskId=" + taskId);
        }
        return false;
    }

    public static void setFrontFreeFormStackInfo(int taskId, boolean isFront) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " setFrontFreeFormStackInfo taskId=" + taskId + ", isFront=" + isFront);
                miuiFreeFormManagerService.setFrontFreeFormStackInfo(taskId, isFront);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed setFrontFreeFormStackInfo taskId=" + taskId);
        }
    }

    public static boolean isFrontFreeFormStackInfo(int taskId) {
        if (getMiuiFreeformVersion() == 2) {
            Log.d(TAG, "非MIUI13小窗版本,请用MIUI12适配方案进行适配");
            return false;
        }
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " isFrontFreeFormStackInfo taskId=" + taskId);
                return miuiFreeFormManagerService.isFrontFreeFormStackInfo(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed isFrontFreeFormStackInfo taskId=" + taskId);
        }
        return false;
    }

    public static void addFullScreenTasksBehindHome(int taskId) {
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " addFullScreenTasksBehindHome appBehindHome=" + taskId);
                miuiFreeFormManagerService.addFullScreenTasksBehindHome(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed addFullScreenTasksBehindHome appBehindHome=" + taskId);
        }
    }

    public static void removeFullScreenTasksBehindHome(int taskId) {
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " removeFullScreenTasksBehindHome appBehindHome=" + taskId);
                miuiFreeFormManagerService.removeFullScreenTasksBehindHome(taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed removeFullScreenTasksBehindHome appBehindHome=" + taskId);
        }
    }

    public static void clearFullScreenTasksBehindHome() {
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " clearFullScreenTasksBehindHome ");
                miuiFreeFormManagerService.clearFullScreenTasksBehindHome();
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed clearFullScreenTasksBehindHome");
        }
    }

    public static boolean isFullScreenStrategyNeededInDesktopMode(int taskId) {
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Slog.i(TAG, " isFullScreenStrategyNeededInDesktopMode taskId=" + taskId);
                return miuiFreeFormManagerService.isFullScreenStrategyNeededInDesktopMode(taskId);
            }
            return false;
        } catch (Exception e2) {
            Log.d(TAG, " failed isFullScreenStrategyNeededInDesktopMode taskId=" + taskId);
            return false;
        }
    }

    public static void updateAutoLayoutModeStatus(boolean isOn) {
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " updateAutoLayoutModeStatus to " + isOn);
                miuiFreeFormManagerService.updateAutoLayoutModeStatus(isOn);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed updateAutoLayoutModeStatus " + isOn);
        }
    }

    /* loaded from: classes5.dex */
    public static final class MiuiFreeFormInfoChange implements Parcelable {
        public static final int CHANGE_MIUIFREEFORM_DISPATCH_CHANGE = 256;
        public static final int CHANGE_MIUIFREEFORM_EXITING = 32;
        public static final int CHANGE_MIUIFREEFORM_IS_FOREGROUND_PIN = 2048;
        public static final int CHANGE_MIUIFREEFORM_LANDSCAPE = 4;
        public static final int CHANGE_MIUIFREEFORM_MODE = 1;
        public static final int CHANGE_MIUIFREEFORM_NEED_ANIMATION = 4096;
        public static final int CHANGE_MIUIFREEFORM_PINED_ACTIVE_TIME = 64;
        public static final int CHANGE_MIUIFREEFORM_PIN_POS = 16;
        public static final int CHANGE_MIUIFREEFORM_SCALE = 2;
        public static final int CHANGE_MIUIFREEFORM_TASK_HIDDEN = 8;
        public static final int CHANGE_MIUIFREEFORM_TO_FULLSCREEN = 128;
        public static final int CHANGE_MIUIFREEFORM_TO_FULLSCREEN_BY_BOTTOM_CAPTION = 512;
        public static final int CHANGE_MIUIMINIFREEFORM_TO_FULLSCREEN_BY_BOTTOM_CAPTION = 1024;
        public static final Creator<MiuiFreeFormInfoChange> CREATOR = new Creator<MiuiFreeFormInfoChange>() { // from class: miui.app.MiuiFreeFormManager.MiuiFreeFormInfoChange.1
            @Override // android.os.Parcelable.Creator
            public MiuiFreeFormInfoChange createFromParcel(Parcel source) {
                return new MiuiFreeFormInfoChange(source);
            }

            @Override // android.os.Parcelable.Creator
            public MiuiFreeFormInfoChange[] newArray(int size) {
                return new MiuiFreeFormInfoChange[size];
            }
        };
        private int changeMask;
        private float freeFormScale;
        private int freeformChange;
        private boolean freeformExiting;
        private boolean freeformTaskHidden;
        private boolean isForegroundPin;
        private boolean isLandcapeFreeform;
        private boolean isNeedAnimation;
        private long pinActiveTime;
        private Rect pinFloatingWindowPos;
        private int windowState;

        public MiuiFreeFormInfoChange() {
            this.pinFloatingWindowPos = new Rect();
            this.freeformChange = 0;
            this.changeMask = 0;
        }

        private MiuiFreeFormInfoChange(Parcel source) {
            this.pinFloatingWindowPos = new Rect();
            this.freeformChange = 0;
            this.changeMask = 0;
            readFromParcel(source);
        }

        public MiuiFreeFormInfoChange setNeedAnimation(boolean needAnimation) {
            this.changeMask |= 4096;
            this.isNeedAnimation = needAnimation;
            return this;
        }

        public boolean getIsNeedAnimation() {
            if ((this.changeMask & 4096) == 0) {
                throw new RuntimeException("MiuiFreeformMode not set. Check CHANGE_MIUIFREEFORM_NEED_ANIMATION first");
            }
            return this.isNeedAnimation;
        }

        public MiuiFreeFormInfoChange setFreeformChange(int change) {
            this.changeMask |= 256;
            this.freeformChange |= change;
            return this;
        }

        public int getFreeformChange() {
            if ((this.changeMask & 256) == 0) {
                throw new RuntimeException("MiuiFreeformMode not set. Check CHANGE_MIUIFREEFORM_DISPATCH_CHANGE first");
            }
            return this.freeformChange;
        }

        public MiuiFreeFormInfoChange freeformToFullscreen() {
            this.changeMask |= 128;
            return this;
        }

        public MiuiFreeFormInfoChange freeformToFullscreenByBottomCaption() {
            this.changeMask |= 512;
            return this;
        }

        public MiuiFreeFormInfoChange miniFreeformToFullscreenByBottomCaption() {
            this.changeMask |= 1024;
            return this;
        }

        public MiuiFreeFormInfoChange setPinActiveTime(long activeTime) {
            this.changeMask |= 64;
            this.pinActiveTime = activeTime;
            return this;
        }

        public long getPinActiveTime() {
            if ((this.changeMask & 64) == 0) {
                throw new RuntimeException("MiuiFreeformMode not set. Check CHANGE_MIUIFREEFORM_PINED_ACTIVE_TIME first");
            }
            return this.pinActiveTime;
        }

        public MiuiFreeFormInfoChange setIsForegroundPin(boolean foregroundPin) {
            this.changeMask |= 2048;
            this.isForegroundPin = foregroundPin;
            return this;
        }

        public boolean getIsForegroundPin() {
            if ((this.changeMask & 2048) == 0) {
                throw new RuntimeException("MiuiFreeformMode not set. Check CHANGE_MIUIFREEFORM_IS_FOREGROUND_PIN first");
            }
            return this.isForegroundPin;
        }

        public MiuiFreeFormInfoChange setMiuiFreeformMode(int mode) {
            this.changeMask |= 1;
            this.windowState = mode;
            return this;
        }

        public int getMiuiFreeformMode() {
            if ((this.changeMask & 1) == 0) {
                throw new RuntimeException("MiuiFreeformMode not set. Check CHANGE_MIUIFREEFORM_MODE first");
            }
            return this.windowState;
        }

        public MiuiFreeFormInfoChange setMiuiFreeformScale(float scale) {
            if (Float.isNaN(scale)) {
                Slog.w(MiuiFreeFormManager.TAG, "setMiuiFreeformScale is NaN!", new Throwable());
                return this;
            }
            this.changeMask |= 2;
            this.freeFormScale = scale;
            return this;
        }

        public float getMiuiFreeformScale() {
            if ((this.changeMask & 2) == 0) {
                throw new RuntimeException("MiuiFreeformScale not set. Check CHANGE_MIUIFREEFORM_SCALE first");
            }
            return this.freeFormScale;
        }

        public MiuiFreeFormInfoChange setMiuiFreeformOrientation(boolean landcapeFreeform) {
            this.changeMask |= 4;
            this.isLandcapeFreeform = landcapeFreeform;
            return this;
        }

        public boolean getMiuiFreeformOrientation() {
            if ((this.changeMask & 4) == 0) {
                throw new RuntimeException("MiuiFreeformOrentation not set. Check CHANGE_MIUIFREEFORM_LANDSCAPE first");
            }
            return this.isLandcapeFreeform;
        }

        public MiuiFreeFormInfoChange setMiuiFreeformTaskHiden(boolean hide) {
            this.changeMask |= 8;
            this.freeformTaskHidden = hide;
            return this;
        }

        public boolean getMiuiFreeformTaskHiden() {
            if ((this.changeMask & 8) == 0) {
                throw new RuntimeException("MiuiFreeformTaskHiden not set. Check CHANGE_MIUIFREEFORM_TASK_HIDDEN first");
            }
            return this.freeformTaskHidden;
        }

        public MiuiFreeFormInfoChange setMiuiFreeformPinPos(Rect pinPos) {
            this.changeMask |= 16;
            this.pinFloatingWindowPos.set(pinPos);
            return this;
        }

        public Rect getMiuiFreeformPinPos() {
            if ((this.changeMask & 16) == 0) {
                throw new RuntimeException("MiuiFreeformPinPos not set. Check CHANGE_MIUIFREEFORM_PIN_POS first");
            }
            return this.pinFloatingWindowPos;
        }

        public MiuiFreeFormInfoChange setMiuiFreeformExiting(boolean exiting) {
            this.changeMask |= 32;
            this.freeformExiting = exiting;
            return this;
        }

        public boolean getMiuiFreeformExiting() {
            if ((this.changeMask & 32) == 0) {
                throw new RuntimeException("MiuiFreeformExiting not set. Check CHANGE_MIUIFREEFORM_EXITING first");
            }
            return this.freeformExiting;
        }

        public int getChangeMask() {
            return this.changeMask;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.windowState);
            dest.writeFloat(this.freeFormScale);
            dest.writeInt(this.pinFloatingWindowPos.left);
            dest.writeInt(this.pinFloatingWindowPos.top);
            dest.writeInt(this.pinFloatingWindowPos.right);
            dest.writeInt(this.pinFloatingWindowPos.bottom);
            dest.writeBoolean(this.isLandcapeFreeform);
            dest.writeBoolean(this.freeformTaskHidden);
            dest.writeBoolean(this.freeformExiting);
            dest.writeLong(this.pinActiveTime);
            dest.writeInt(this.freeformChange);
            dest.writeBoolean(this.isNeedAnimation);
            dest.writeInt(this.changeMask);
        }

        public void readFromParcel(Parcel source) {
            this.windowState = source.readInt();
            this.freeFormScale = source.readFloat();
            this.pinFloatingWindowPos = new Rect(source.readInt(), source.readInt(), source.readInt(), source.readInt());
            this.isLandcapeFreeform = source.readBoolean();
            this.freeformTaskHidden = source.readBoolean();
            this.freeformExiting = source.readBoolean();
            this.pinActiveTime = source.readLong();
            this.freeformChange = source.readInt();
            this.isNeedAnimation = source.readBoolean();
            this.changeMask = source.readInt();
        }

        public String toString() {
            return "MiuiFreeFormInfoChange{windowState=" + this.windowState + ", freeFormScale=" + this.freeFormScale + ", pinFloatingWindowPos=" + this.pinFloatingWindowPos + ", isLandcapeFreeform=" + this.isLandcapeFreeform + ", freeformTaskHidden=" + this.freeformTaskHidden + ", freeformExiting=" + this.freeformExiting + ", pinActiveTime=" + this.pinActiveTime + ", freeformChange=" + this.freeformChange + ", changeMask=" + this.changeMask + '}';
        }
    }

    /* loaded from: classes5.dex */
    public static final class MiuiFreeFormStackInfo implements Parcelable {
        public static final Creator<MiuiFreeFormStackInfo> CREATOR = new Creator<MiuiFreeFormStackInfo>() { // from class: miui.app.MiuiFreeFormManager.MiuiFreeFormStackInfo.1
            @Override // android.os.Parcelable.Creator
            public MiuiFreeFormStackInfo createFromParcel(Parcel source) {
                return new MiuiFreeFormStackInfo(source);
            }

            @Override // android.os.Parcelable.Creator
            public MiuiFreeFormStackInfo[] newArray(int size) {
                return new MiuiFreeFormStackInfo[size];
            }
        };
        public Rect bounds;
        public Configuration configuration;
        public int cornerPosition;
        public int displayId;
        public Rect enterMiniFreeformFromRect;
        public String enterMiniFreeformReason;
        public float freeFormScale;
        public boolean freeformExiting;
        public boolean freeformTaskHidden;
        public boolean hadHideStackFormFullScreen;
        public boolean inPinMode;
        public boolean isForegroundPin;
        public boolean isLandcapeFreeform;
        public boolean isNormalFreeForm;
        public boolean needAnimation;
        public String packageName;
        public Rect pinFloatingWindowPos;
        public boolean runningPinAnim;
        public Rect smallWindowBounds;
        public int stackId;
        public long timestamp;
        public int userId;
        public int windowState;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.stackId);
            dest.writeInt(this.bounds.left);
            dest.writeInt(this.bounds.top);
            dest.writeInt(this.bounds.right);
            dest.writeInt(this.bounds.bottom);
            dest.writeInt(this.displayId);
            dest.writeInt(this.userId);
            dest.writeInt(this.windowState);
            dest.writeFloat(this.freeFormScale);
            dest.writeString(this.packageName);
            this.configuration.writeToParcel(dest, flags);
            dest.writeInt(this.smallWindowBounds.left);
            dest.writeInt(this.smallWindowBounds.top);
            dest.writeInt(this.smallWindowBounds.right);
            dest.writeInt(this.smallWindowBounds.bottom);
            dest.writeBoolean(this.inPinMode);
            dest.writeBoolean(this.isForegroundPin);
            dest.writeInt(this.pinFloatingWindowPos.left);
            dest.writeInt(this.pinFloatingWindowPos.top);
            dest.writeInt(this.pinFloatingWindowPos.right);
            dest.writeInt(this.pinFloatingWindowPos.bottom);
            dest.writeBoolean(this.isNormalFreeForm);
            dest.writeLong(this.timestamp);
            dest.writeInt(this.cornerPosition);
            dest.writeInt(this.enterMiniFreeformFromRect.left);
            dest.writeInt(this.enterMiniFreeformFromRect.top);
            dest.writeInt(this.enterMiniFreeformFromRect.right);
            dest.writeInt(this.enterMiniFreeformFromRect.bottom);
            dest.writeString(this.enterMiniFreeformReason);
            dest.writeBoolean(this.isLandcapeFreeform);
            dest.writeBoolean(this.freeformTaskHidden);
            dest.writeBoolean(this.freeformExiting);
            dest.writeBoolean(this.hadHideStackFormFullScreen);
            dest.writeBoolean(this.needAnimation);
        }

        public void readFromParcel(Parcel source) {
            this.stackId = source.readInt();
            this.bounds = new Rect(source.readInt(), source.readInt(), source.readInt(), source.readInt());
            this.displayId = source.readInt();
            this.userId = source.readInt();
            this.windowState = source.readInt();
            this.freeFormScale = source.readFloat();
            this.packageName = source.readString();
            this.configuration.readFromParcel(source);
            this.smallWindowBounds = new Rect(source.readInt(), source.readInt(), source.readInt(), source.readInt());
            this.inPinMode = source.readBoolean();
            this.isForegroundPin = source.readBoolean();
            this.pinFloatingWindowPos = new Rect(source.readInt(), source.readInt(), source.readInt(), source.readInt());
            this.isNormalFreeForm = source.readBoolean();
            this.timestamp = source.readLong();
            this.cornerPosition = source.readInt();
            this.enterMiniFreeformFromRect = new Rect(source.readInt(), source.readInt(), source.readInt(), source.readInt());
            this.enterMiniFreeformReason = source.readString();
            this.isLandcapeFreeform = source.readBoolean();
            this.freeformTaskHidden = source.readBoolean();
            this.freeformExiting = source.readBoolean();
            this.hadHideStackFormFullScreen = source.readBoolean();
            this.needAnimation = source.readBoolean();
        }

        public MiuiFreeFormStackInfo() {
            this.bounds = new Rect();
            this.configuration = new Configuration();
            this.smallWindowBounds = new Rect();
            this.pinFloatingWindowPos = new Rect();
            this.isNormalFreeForm = true;
            this.enterMiniFreeformFromRect = new Rect();
            this.enterMiniFreeformReason = "";
        }

        private MiuiFreeFormStackInfo(Parcel source) {
            this.bounds = new Rect();
            this.configuration = new Configuration();
            this.smallWindowBounds = new Rect();
            this.pinFloatingWindowPos = new Rect();
            this.isNormalFreeForm = true;
            this.enterMiniFreeformFromRect = new Rect();
            this.enterMiniFreeformReason = "";
            readFromParcel(source);
        }

        public String toString(String prefix) {
            StringBuilder sb = new StringBuilder(256);
            sb.append(prefix);
            sb.append("Stack id=");
            sb.append(this.stackId);
            sb.append(" bounds=");
            sb.append(this.bounds.toShortString());
            sb.append(" displayId=");
            sb.append(this.displayId);
            sb.append(" userId=");
            sb.append(this.userId);
            sb.append(" windowState=");
            sb.append(this.windowState);
            sb.append(" freeFormScale=");
            sb.append(this.freeFormScale);
            sb.append(" packageName=");
            sb.append(this.packageName);
            if (isInMiniFreeFormMode()) {
                sb.append(" smallWindowBounds=");
                sb.append(this.smallWindowBounds.toShortString());
            }
            sb.append(" configuration=");
            sb.append(this.configuration);
            sb.append(" inPinMode=");
            sb.append(this.inPinMode);
            sb.append(" isForegroundPin=");
            sb.append(this.isForegroundPin);
            sb.append(" pinFloatingWindowPos=");
            sb.append(this.pinFloatingWindowPos);
            sb.append(" isNormalFreeForm=");
            sb.append(this.isNormalFreeForm);
            sb.append(" timestamp=");
            sb.append(this.timestamp);
            sb.append(" cornerPosition=");
            sb.append(this.cornerPosition);
            sb.append(" enterMiniFreeformFromRect=");
            sb.append(this.enterMiniFreeformFromRect.toShortString());
            sb.append(" enterMiniFreeformReason=");
            sb.append(this.enterMiniFreeformReason);
            sb.append(" isLandcapeFreeform=");
            sb.append(this.isLandcapeFreeform);
            sb.append(" freeformTaskHidden=");
            sb.append(this.freeformTaskHidden);
            sb.append(" freeformExiting=");
            sb.append(this.freeformExiting);
            sb.append(" hadHideStackFormFullScreen=");
            sb.append(this.hadHideStackFormFullScreen);
            sb.append(" needAnimation=");
            sb.append(this.needAnimation);
            return sb.toString();
        }

        public boolean isInMiniFreeFormMode() {
            return this.windowState == 1;
        }

        public boolean isInFreeFormMode() {
            return this.windowState == 0;
        }

        public String toString() {
            return toString("");
        }
    }

    public static String actionToString(int action) {
        switch (action) {
            case 0:
                return "ACTION_FULLSCREEN_TO_FREEFORM";
            case 1:
                return "ACTION_FULLSCREEN_TO_MINIFREEFORM";
            case 2:
                return "ACTION_FREEFORM_TO_MINIFREEFORM";
            case 3:
                return "ACTION_FREEFORM_TO_FULLSCREEN";
            case 4:
                return "ACTION_MINIFREEFORM_TO_FREEFORM";
            case 5:
                return "ACTION_MINIFREEFORM_TO_FULLSCREEN";
            case 6:
                return "ACTION_FREEFORM_START_RESIZE";
            case 7:
                return "ACTION_FREEFORM_END_RESIZE";
            case 8:
                return "ACTION_FREEFORM_UPDATE_CAPTION_VISIBILITY";
            case 9:
                return "ACTION_FREEFORM_PINED";
            case 10:
                return "ACTION_MINIFREEFORM_PINED";
            case 11:
                return "ACTION_FREEFORM_UNPINED";
            case 12:
                return "ACTION_MINI_FREEFORM_UNPINED";
            case 13:
                return "ACTION_REMOVE_FLOATING_PIN_WINDOW";
            case 14:
                return "ACTION_MINIFREEFORM_PINED_TO_FREEFORM_PINED";
            case 15:
                return "ACTION_FREEFORM_PINED_TO_MINIFREEFORM_PINED";
            case 16:
                return "ACTION_NORMAL_PIN_TO_FULLSCREEN";
            case 17:
                return "ACTION_MINI_PIN_TO_FULLSCREEN";
            case 18:
                return "ACTION_FREEFORM_TO_ELUDE";
            case 19:
                return "ACTION_ELUDE_TO_FREEFORM";
            default:
                return null;
        }
    }

    public static void dispatchFreeFormStackModeChanged(int action, int taskId) {
        try {
            IMiuiFreeFormManager miuiFreeFormManagerService = getService();
            if (miuiFreeFormManagerService != null) {
                Log.d(TAG, " dispatchFreeFormStackModeChanged ");
                miuiFreeFormManagerService.dispatchFreeFormStackModeChanged(action, taskId);
            }
        } catch (Exception e2) {
            Log.d(TAG, " failed dispatchFreeFormStackModeChanged");
        }
    }

    public static int getMaxMiuiFreeFormStackCountForFlashBack(String packageName, boolean isLaunchFlashBack) {
        return 0;
    }
}
