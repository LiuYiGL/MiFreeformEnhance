package name.liuyi.mifreeformenhance.xposed.base.annotation

import kotlin.reflect.KClass

annotation class HookEntry(
    val scope: Array<KClass<*>>
)
