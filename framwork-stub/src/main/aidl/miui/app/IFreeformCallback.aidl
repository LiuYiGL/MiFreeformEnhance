package miui.app;
import miui.app.MiuiFreeFormManager;

interface IFreeformCallback {
    void dispatchFreeFormStackModeChanged(int mode, in MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo);
}