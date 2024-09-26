package com.example.cell_android_apps_test_task.domain.repository

import com.example.cell_android_apps_test_task.domain.model.Cell

interface ICellRepository {
    suspend fun addCell(cell: Cell): Unit

}