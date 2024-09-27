package com.example.cell_android_apps_test_task.datasource.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cell_android_apps_test_task.datasource.room.dao.CellDao
import com.example.cell_android_apps_test_task.datasource.room.entity.CellEntity

@Database(
    entities = [CellEntity::class],
    version = 1
)
@TypeConverters(UUIDConverter::class)
abstract class CellDataBase : RoomDatabase() {
    abstract fun getCellDao(): CellDao
}