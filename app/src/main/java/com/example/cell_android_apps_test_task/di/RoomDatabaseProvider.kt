package com.example.cell_android_apps_test_task.di

import android.content.Context
import androidx.room.Room
import com.example.cell_android_apps_test_task.datasource.room.db.CellDataBase
import com.example.cell_android_apps_test_task.datasource.room.db.UUIDConverter

object DbProvider {
    @Volatile
    private var INSTANCE: CellDataBase? = null

    fun getDatabase(context: Context): CellDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CellDataBase::class.java,
                "cells_database"
            ).addTypeConverter(UUIDConverter::class).build()
            INSTANCE = instance
            instance
        }
    }
}