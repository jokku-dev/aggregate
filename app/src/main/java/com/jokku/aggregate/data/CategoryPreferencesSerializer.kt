package com.jokku.aggregate.data

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object CategoryPreferencesSerializer : Serializer<ProtoPreferences> {
    override val defaultValue: ProtoPreferences
        get() = ProtoPreferences()

    override suspend fun readFrom(input: InputStream): ProtoPreferences {
        return try {
            Json.decodeFromString(
                deserializer = ProtoPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProtoPreferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = ProtoPreferences.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}