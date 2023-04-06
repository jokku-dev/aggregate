package com.jokku.aggregate.data

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object TypedPreferencesSerializer : Serializer<TypedPreferences> {
    override val defaultValue: TypedPreferences
        get() = TypedPreferences()

    override suspend fun readFrom(input: InputStream): TypedPreferences {
        return try {
            Json.decodeFromString(
                deserializer = TypedPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: TypedPreferences, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = TypedPreferences.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}