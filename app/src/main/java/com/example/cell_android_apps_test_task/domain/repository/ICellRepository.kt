package com.example.cell_android_apps_test_task.domain.repository

import com.example.cell_android_apps_test_task.domain.model.Cell
import java.util.UUID


interface ICellRepository {
    suspend fun addCell(cell: Cell)
    suspend fun updateCell(cell: Cell)
    suspend fun getCell(id: UUID): Result<Cell>
    suspend fun getAllCells(): List<Cell>
    suspend fun getLastCells(limit: Int): List<Cell>
    suspend fun deleteAllCells()
    suspend fun deleteCell(cell: Cell)
}