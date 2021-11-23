package com.example.todoapp2.database

import androidx.room.TypeConverter
import java.time.LocalDateTime

object TaskConverter {
    @TypeConverter
    fun toDateTime(dateString: String): LocalDateTime? {
        return if(dateString == null) null else LocalDateTime.parse(dateString)
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime): String? {
        return date?.toString()
    }
}