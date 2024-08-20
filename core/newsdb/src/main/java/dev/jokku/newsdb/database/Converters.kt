package dev.jokku.newsdb.database

import androidx.room.TypeConverter
import java.util.Date
import kotlin.let

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}