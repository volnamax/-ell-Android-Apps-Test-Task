package com.example.cell_android_apps_test_task.datasourse.room.dao


import androidx.room.*
import com.example.cell_android_apps_test_task.datasourse.room.entity.CellEntity
import java.util.UUID

@Dao
interface CellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCell(cell: CellEntity)

    @Update
    suspend fun updateCell(cell: CellEntity)

    @Query("SELECT * FROM cells WHERE id = :id LIMIT 1")
    suspend fun getCellById(id: UUID): CellEntity?

    @Query("SELECT * FROM cells")
    suspend fun getAllCells(): List<CellEntity>

    @Query("SELECT * FROM cells ORDER BY id DESC LIMIT :limit")
    suspend fun getLastCells(limit: Int): List<CellEntity>

    @Query("DELETE FROM cells")
    suspend fun deleteAllCells()

    @Delete
    suspend fun deleteCell(cell: CellEntity)
}
