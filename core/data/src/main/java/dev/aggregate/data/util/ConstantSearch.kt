package dev.aggregate.data.util

import kotlinx.serialization.SerialName

inline fun <reified T : Enum<T>> findNetworkConstantBySerialName(serialName: String?): T? {
    return enumValues<T>().find { it.name == serialName }
}

inline fun <reified T : Enum<T>> getConstantSerialName(constant: T): String? {
    return constant::class.java.getField(constant.name).getAnnotation(SerialName::class.java)?.value
}
