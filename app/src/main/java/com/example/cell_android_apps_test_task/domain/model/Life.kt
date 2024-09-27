package com.example.cell_android_apps_test_task.domain.model

import java.util.UUID

data class Life (
    val id : UUID,
    val isAlive: Boolean,
    val descriptor: String
)