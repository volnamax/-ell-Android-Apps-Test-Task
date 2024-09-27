package com.example.cell_android_apps_test_task.data.datasourse.service

import com.example.cell_android_apps_test_task.domain.model.Cell
import java.util.UUID


interface ICellDaoService {
    suspend fun insertCell(cell: Cell)
    suspend fun updateCell(cell: Cell)
    suspend fun getCellById(id: UUID): Cell?
    suspend fun getAllCells(): List<Cell>
    suspend fun getLastCells(limit: Int): List<Cell>
    suspend fun deleteAllCells()
    suspend fun deleteCell(cell: Cell)
}