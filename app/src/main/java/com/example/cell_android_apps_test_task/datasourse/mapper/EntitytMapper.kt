package com.example.cell_android_apps_test_task.datasourse.mapper

import com.example.cell_android_apps_test_task.datasourse.room.entity.CellEntity
import com.example.cell_android_apps_test_task.domain.model.Cell

fun Cell.toEntity(): CellEntity {
    return CellEntity(
        id = this.id,
        isLive = this.isLive,
        descriptor = this.descriptor
    )
}

fun CellEntity.toDomain(): Cell {
    return Cell(
        id = this.id,
        isLive = this.isLive,
        descriptor = this.descriptor
    )
}