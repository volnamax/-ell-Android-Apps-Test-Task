package com.example.cell_android_apps_test_task.datasourse.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "cells")
data class KitEntity(
    @PrimaryKey val id: UUID,
    val isLive : Boolean,
    val descriptor: String
)