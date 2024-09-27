package com.example.cell_android_apps_test_task.data.repository

import com.example.cell_android_apps_test_task.data.datasourse.service.ICellDaoService
import com.example.cell_android_apps_test_task.domain.model.Cell
import com.example.cell_android_apps_test_task.domain.repository.ICellRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID


class CellRepository(private val cellDaoService: ICellDaoService) : ICellRepository {

    override suspend fun addCell(cell: Cell) {
        cellDaoService.insertCell(cell)
    }

    override suspend fun getCell(id: UUID): Result<Cell> {
        return runCatching {
            cellDaoService.getCellById(id) ?: throw IllegalStateException("Cell not found")
        }
    }

    override suspend fun deleteCell(cell: Cell) {
        cellDaoService.deleteCell(cell)
    }

    override suspend fun getAllCells(): List<Cell> {
        return cellDaoService.getAllCells()
    }
}