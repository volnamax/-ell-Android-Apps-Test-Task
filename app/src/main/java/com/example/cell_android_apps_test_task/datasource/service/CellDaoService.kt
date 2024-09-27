package com.example.cell_android_apps_test_task.datasource.service

import com.example.cell_android_apps_test_task.data.datasourse.service.ICellDaoService
import com.example.cell_android_apps_test_task.datasource.mapper.toDomain
import com.example.cell_android_apps_test_task.datasource.mapper.toEntity
import com.example.cell_android_apps_test_task.datasource.room.db.RoomDataBase
import com.example.cell_android_apps_test_task.domain.model.Cell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class CellDaoService(database: RoomDataBase) : ICellDaoService {
    private val cellDao = database.getCellDao()

    override suspend fun insertCell(cell: Cell) = withContext(Dispatchers.IO) {
        cellDao.insertCell(cell.toEntity())
    }

    override suspend fun updateCell(cell: Cell) = withContext(Dispatchers.IO) {
        cellDao.updateCell(cell.toEntity())
    }

    override suspend fun getCellById(id: UUID): Cell? = withContext(Dispatchers.IO) {
        val cellEntity = cellDao.getCellById(id)
        cellEntity?.toDomain()
    }

    override suspend fun getAllCells(): List<Cell> = withContext(Dispatchers.IO) {
        cellDao.getAllCells().map { it.toDomain() }
    }

    override suspend fun getLastCells(limit: Int): List<Cell> = withContext(Dispatchers.IO) {
        cellDao.getLastCells(limit).map { it.toDomain() }
    }

    override suspend fun deleteAllCells() = withContext(Dispatchers.IO) {
        cellDao.deleteAllCells()
    }

    override suspend fun deleteCell(cell: Cell) = withContext(Dispatchers.IO) {
        cellDao.deleteCell(cell.toEntity())
    }


}

