package name.liuyi.mifreeformenhance.xposed.utils

import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage

/*
		/** The path to the module's APK. */
		public String modulePath;

		/**
		 * Always {@code true} on 32-bit ROMs. On 64-bit, it's only {@code true} for the primary
		 * process that starts the system_server.
		 */
		public boolean startsSystemServer;
 */
fun IXposedHookZygoteInit.StartupParam.print(): String {
    return "StartupParam{" +
            "modulePath='$modulePath'" +
            ", startsSystemServer=$startsSystemServer" +
            "}"
}

/*
		/** The name of the package being loaded. */
		public String packageName;

		/** The process in which the package is executed. */
		public String processName;

		/** The ClassLoader used for this package. */
		public ClassLoader classLoader;

		/** More information about the application being loaded. */
		public ApplicationInfo appInfo;

		/** Set to {@code true} if this is the first (and main) application for this process. */
		public boolean isFirstApplication;
 */
fun XC_LoadPackage.LoadPackageParam.print(): String {
    return "LoadPackageParam{" +
            "packageName='$packageName'" +
            ", processName='$processName'" +
            ", classLoader=" + classLoader.javaClass.name +
            ", appInfo=" + appInfo +
            ", isFirstApplication=$isFirstApplication" +
            "}"
}