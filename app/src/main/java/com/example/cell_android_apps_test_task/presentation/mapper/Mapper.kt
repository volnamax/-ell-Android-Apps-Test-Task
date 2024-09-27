package com.example.cell_android_apps_test_task.presentation.mapper

import com.example.cell_android_apps_test_task.domain.model.Cell
import com.example.cell_android_apps_test_task.presentation.model.CellViewData

fun Cell.toViewData(): CellViewData {
    return CellViewData(
        id = this.id,
        isLive = this.isAlive,
        descriptor = this.descriptor
    )
}