package com.example.cell_android_apps_test_task.presentation.model

import com.example.cell_android_apps_test_task.domain.model.CellType
import java.util.UUID


data class CellViewData (
    val id : UUID,
    val isLive : Boolean,
    val type: CellType,
    val descriptor: String
)
