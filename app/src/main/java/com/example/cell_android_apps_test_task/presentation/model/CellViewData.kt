package com.example.cell_android_apps_test_task.presentation.model

import java.util.UUID


data class CellViewData (
    val id : UUID,
    val isLive : Boolean,
    val descriptor: String
)
