package com.jokku.aggregate.data.local.database

import androidx.room.TypeConverter
import com.jokku.aggregate.data.local.database.entity.LocalArticle
import com.jokku.aggregate.data.local.database.entity.LocalSource
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListConverter {

    @TypeConverter
    fun fromLocalSourcesList(value: List<LocalSource>): String {
        return Json.encodeToString(value = value)
    }

    @TypeConverter
    fun toLocalSourcesList(string: String): List<LocalSource> {
        return Json.decodeFromString(string = string)
    }
}