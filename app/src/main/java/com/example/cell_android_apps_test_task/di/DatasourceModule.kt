package com.example.cell_android_apps_test_task.di

import androidx.room.Room
import com.example.cell_android_apps_test_task.data.datasourse.service.ICellDaoService
import com.example.cell_android_apps_test_task.datasource.room.db.CellDataBase
import com.example.cell_android_apps_test_task.datasource.service.CellDaoService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val datasourceModule = module {

    single<CellDataBase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = CellDataBase::class.java,
            name = "cells_database"
        ).build()
    }

    factory<ICellDaoService> {
        CellDaoService(
            database = get<CellDataBase>()
        )
    }
}