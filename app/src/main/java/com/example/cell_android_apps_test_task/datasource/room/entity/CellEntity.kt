package com.example.cell_android_apps_test_task.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cell_android_apps_test_task.domain.model.CellType
import java.util.UUID

@Entity(tableName = "cells")
data class CellEntity(
    @PrimaryKey val id: UUID,
    val isAlive : Boolean,
    val descriptor: String,
    val type: CellType,
)