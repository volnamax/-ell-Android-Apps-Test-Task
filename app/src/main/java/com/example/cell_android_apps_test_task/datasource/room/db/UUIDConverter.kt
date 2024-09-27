package com.example.cell_android_apps_test_task.datasource.room.db

import androidx.room.TypeConverter
import java.util.UUID


class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return if (uuid == null) null else UUID.fromString(uuid)
    }
}
