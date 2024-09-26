package com.example.cell_android_apps_test_task.data.datasourse.service

import com.example.cell_android_apps_test_task.domain.model.Cell


interface ICellDaoService {
    suspend fun insertCell(cell: Cell)
}