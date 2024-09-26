package com.example.cell_android_apps_test_task.domain

import java.util.UUID

data class Life (
    val id : UUID,
    val isLive: Boolean,
    val descriptor: String
)