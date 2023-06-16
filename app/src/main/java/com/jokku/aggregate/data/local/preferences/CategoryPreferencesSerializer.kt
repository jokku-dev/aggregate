package com.jokku.aggregate.data.local.preferences

import androidx.datastore.core.Serializer
import com.jokku.aggregate.data.local.preferences.model.ProtoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object CategoryPreferencesSerializer : Serializer<ProtoModel> {
    override val defaultValue: ProtoModel
        get() = ProtoModel()

    override suspend fun readFrom(input: InputStream): ProtoModel {
        return try {
            Json.decodeFromString(
                deserializer = ProtoModel.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(model: ProtoModel, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = ProtoModel.serializer(),
                    value = model
                ).encodeToByteArray()
            )
        }
    }
}