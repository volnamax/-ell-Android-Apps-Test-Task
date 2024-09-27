package com.example.cell_android_apps_test_task.datasource.mapper

import com.example.cell_android_apps_test_task.datasource.room.entity.CellEntity
import com.example.cell_android_apps_test_task.domain.model.Cell

fun Cell.toEntity(): CellEntity {
    return CellEntity(
        id = this.id,
        isAlive = this.isAlive,
        type = this.type,
        descriptor = this.descriptor
    )
}

fun CellEntity.toDomain(): Cell {
    return Cell(
        id = this.id,
        isAlive = this.isAlive,
        type= this.type,
        descriptor = this.descriptor
    )
}