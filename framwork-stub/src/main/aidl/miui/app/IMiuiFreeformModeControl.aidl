package miui.app;

interface IMiuiFreeformModeControl {
        void freeformFullscreenTask(int taskId); // 全屏
        void freeformKillAll();
        void fromFreefromToMini(int taskId);
        void unPinFloatingWindowForActive(int taskId);
}