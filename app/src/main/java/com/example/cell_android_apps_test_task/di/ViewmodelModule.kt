package com.example.cell_android_apps_test_task.di

import com.example.cell_android_apps_test_task.domain.repository.ICellRepository
import com.example.cell_android_apps_test_task.presentation.screen.main.CellsCreateViewModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewmodelModule = module {
    CellsCreateViewModule(
        cellRepository = get<ICellRepository>()
    )

}