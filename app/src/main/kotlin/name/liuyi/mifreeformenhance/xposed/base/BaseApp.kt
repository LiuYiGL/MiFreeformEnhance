package name.liuyi.mifreeformenhance.xposed.base

import android.content.SharedPreferences
import androidx.core.content.edit
import dalvik.system.DexFile
import dalvik.system.DexPathList
import dalvik.system.PathClassLoader
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.callbacks.XC_LoadPackage
import name.liuyi.mifreeformenhance.BuildConfig
import name.liuyi.mifreeformenhance.common.Reflection.get
import name.liuyi.mifreeformenhance.common.XLogManager
import name.liuyi.mifreeformenhance.provider.RemotePreferences
import name.liuyi.mifreeformenhance.xposed.base.annotation.HookEntry
import name.liuyi.mifreeformenhance.xposed.base.interfaces.IBaseHook
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.isSubclassOf

open class BaseApp : XLogManager.LogScope {
    private lateinit var _startupParam: IXposedHookZygoteInit.StartupParam
    private lateinit var _lpparam: XC_LoadPackage.LoadPackageParam
    private lateinit var _prefs: SharedPreferences

    val startupParam: IXposedHookZygoteInit.StartupParam
        get() = _startupParam

    val lpparam: XC_LoadPackage.LoadPackageParam
        get() = _lpparam

    val prefs: SharedPreferences
        get() = _prefs

    fun attachParam(startupParam: IXposedHookZygoteInit.StartupParam, lpparam: XC_LoadPackage.LoadPackageParam) {
        d("attach")

        _startupParam = startupParam
        _lpparam = lpparam
        _prefs = RemotePreferences(XSharedPreferences(BuildConfig.APPLICATION_ID))

        val loader = PathClassLoader(startupParam.modulePath, ClassLoader.getSystemClassLoader())
        val pathList = loader.javaClass["pathList"][loader] as DexPathList
        val dexElements = pathList.javaClass["dexElements"][pathList] as Array<*>
        for (dexElement in dexElements) {
            if (dexElement != null) {
                val dexFile = dexElement.javaClass["dexFile"][dexElement] as DexFile?
                if (dexFile != null) {
                    val enumeration = dexFile.entries()
                    while (enumeration.hasMoreElements()) {
                        val className = enumeration.nextElement()
                        runCatching {
                            if (className.startsWith("${BuildConfig.APPLICATION_ID}.xposed.hook.")
                                && !className.contains('$')
                            ) {
                                val kClass = runCatching {
                                    Class.forName(className, false, javaClass.classLoader)
                                }.getOrNull()?.kotlin
                                if (kClass == null) {
                                    w("load hook class $className not found")
                                } else if (!kClass.hasAnnotation<HookEntry>()) {
                                    w("hook class $className not annotated with @HookEntry")
                                } else {
                                    val hookEntry = kClass.findAnnotation<HookEntry>()
                                    if (hookEntry != null && hookEntry.scope.contains(javaClass.kotlin)) {
                                        i("load hook class $className")
                                        if (kClass.isSubclassOf(IBaseHook::class)) {
                                            val hook = kClass.objectInstance as IBaseHook
                                            runCatching {
                                                hook.onStartup(this)
                                            }.onFailure {
                                                hook.clean()
                                                throw it
                                            }
                                        }
                                    }
                                }
                            }
                        }.onFailure {
                            e("load hook class $className error", it)
                        }
                    }
                }
            }
        }
    }
}