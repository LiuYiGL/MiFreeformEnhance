package miui.app;
import miui.app.IFreeformCallback;
import miui.app.MiuiFreeFormManager;

interface IMiuiFreeFormManager {
    void registerFreeformCallback(IFreeformCallback iFreeformCallback);

    void unregisterFreeformCallback(IFreeformCallback iFreeformCallback);

    void addFullScreenTasksBehindHome(int i2);

    void clearFullScreenTasksBehindHome();

    void dispatchFreeFormStackModeChanged(int i2, int i3);

    List<MiuiFreeFormManager.MiuiFreeFormStackInfo> getAllFreeFormStackInfosOnDisplay(int i2);

    List<MiuiFreeFormManager.MiuiFreeFormStackInfo> getAllFrontFreeFormStackInfosOnDesktopMode(int i2);

    List<MiuiFreeFormManager.MiuiFreeFormStackInfo> getAllPinedFreeFormStackInfosOnDisplay(int i2);

    MiuiFreeFormManager.MiuiFreeFormStackInfo getFreeFormStackInfoByActivity(IBinder iBinder);

    MiuiFreeFormManager.MiuiFreeFormStackInfo getFreeFormStackInfoByStackId(int i2);

    MiuiFreeFormManager.MiuiFreeFormStackInfo getFreeFormStackInfoByWindow(IBinder iBinder);

    MiuiFreeFormManager.MiuiFreeFormStackInfo getFreeFormStackToAvoid(int i2, String str);

    Rect getFreeformLastPosition(int i2);

    float getFreeformLastScale(int i2);

    float getFreeformRectDesktop(in Rect rect, float f2, int i2, boolean z2);

    boolean hasFreeformDesktopMemory(int i2);

    boolean hasMiuiFreeformOnShellFeature();

    boolean isFrontFreeFormStackInfo(int i2);

    boolean isFullScreenStrategyNeededInDesktopMode(int i2);

    boolean isHideStackFromFullScreen(int i2);

    boolean isSplitRootTask(int i2);

    boolean isSupportPin();

    void notifyCameraStateChanged(String str, int i2);

    boolean openCameraInFreeForm(String str);

    void removeFullScreenTasksBehindHome(int i2);

    void setFrontFreeFormStackInfo(int i2, boolean z2);

    void setHideStackFromFullScreen(int i2, boolean z2);

    boolean shouldForegroundPin(int i2);

    int startSmallFreeformFromNotification();

    void updateAutoLayoutModeStatus(boolean z2);

}