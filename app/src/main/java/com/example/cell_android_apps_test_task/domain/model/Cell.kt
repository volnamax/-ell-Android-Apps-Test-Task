package com.example.cell_android_apps_test_task.domain.model

import java.util.UUID

data class Cell (
    val id : UUID,
    val isAlive : Boolean,
    val descriptor: String
)
