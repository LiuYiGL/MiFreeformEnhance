package name.liuyi.mifreeformenhance.common

import java.lang.reflect.Field
import java.lang.reflect.Method

object Reflection {
    operator fun Class<*>.get(fieldName: String) = getFieldByName(fieldName)

    operator fun Class<*>.invoke(methodName: String, vararg parameterTypes: Class<*>) =
        getMethodByName(methodName, *parameterTypes)

    fun Class<*>.getFieldByName(fieldName: String): Field {
        var clazz: Class<*>? = this
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName).also { it.isAccessible = true }
            } catch (e: NoSuchFieldException) {
                clazz = clazz.superclass
            }
        }
        throw NoSuchFieldException("Field $fieldName not found in class $this")
    }

    fun Class<*>.getAllFields(): Array<Field> {
        var clazz: Class<*>? = this
        var fields = emptyArray<Field>()
        while (clazz != null) {
            try {
                fields += clazz.declaredFields.also { it.forEach { it.isAccessible = true } }
            } catch (e: Exception) {
                clazz = clazz.superclass
            }
        }
        return fields
    }

    fun Class<*>.getMethodByName(methodName: String, vararg parameterTypes: Class<*>): Method {
        var clazz: Class<*>? = this
        while (clazz != null) {
            try {
                return clazz.getDeclaredMethod(methodName, *parameterTypes).also { it.isAccessible = true }
            } catch (e: NoSuchMethodException) {
                clazz = clazz.superclass
            }
        }
        throw NoSuchMethodException("Method $methodName not found in class $this")
    }

    fun Class<*>.getAllMethods(): Array<Method> {
        var clazz: Class<*>? = this
        var methods = emptyArray<Method>()
        while (clazz != null) {
            try {
                methods += clazz.declaredMethods.also { it.forEach { it.isAccessible = true } }
            } catch (e: Exception) {
                clazz = clazz.superclass
            }
        }
        return methods
    }
}
