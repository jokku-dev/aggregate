package dev.aggregate.data.local.preferences

import androidx.datastore.core.Serializer
import dev.aggregate.data.local.preferences.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PreferencesSerializer : Serializer<UserData> {
    override val defaultValue: UserData
        get() = UserData()

    override suspend fun readFrom(input: InputStream): UserData {
        return try {
            Json.decodeFromString(
                deserializer = UserData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(model: UserData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = UserData.serializer(),
                    value = model
                ).encodeToByteArray()
            )
        }
    }
}