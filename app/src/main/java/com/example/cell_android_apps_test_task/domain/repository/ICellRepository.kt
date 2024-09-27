package com.example.cell_android_apps_test_task.domain.repository

import com.example.cell_android_apps_test_task.domain.model.Cell
import kotlinx.coroutines.flow.Flow
import java.util.UUID


interface ICellRepository {
    suspend fun addCell(cell: Cell)
    suspend fun getCell(id: UUID): Result<Cell>
    suspend fun deleteCell(cell: Cell)
    fun getAllCells(): List<Cell>
}