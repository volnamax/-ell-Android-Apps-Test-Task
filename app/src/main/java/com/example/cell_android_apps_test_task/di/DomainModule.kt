package com.example.cell_android_apps_test_task.di

import com.example.cell_android_apps_test_task.data.datasourse.service.ICellDaoService
import com.example.cell_android_apps_test_task.data.repository.CellRepository
import com.example.cell_android_apps_test_task.domain.repository.ICellRepository
import org.koin.dsl.module

val domainModule = module {
    factory<ICellRepository> {
        CellRepository(
            cellDaoService = get<ICellDaoService>()
        )
    }
}