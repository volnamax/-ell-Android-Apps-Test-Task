package com.example.cell_android_apps_test_task.datasourse.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cell_android_apps_test_task.datasourse.room.dao.CellDao
import com.example.cell_android_apps_test_task.datasourse.room.entity.CellEntity

@Database(
    entities = [CellEntity::class],
    version = 1
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun getCellDao(): CellDao
}