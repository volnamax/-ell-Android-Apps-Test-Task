package com.example.cell_android_apps_test_task.domain.model

import java.util.UUID

data class Cell (
    val id : UUID,
    val isAlive : Boolean,
    val type: CellType, // (Клетка или Жизнь) todo предполагалось  изначально две сущности жизнь и клетка, но с точки зрения упрощения написания кода на данном этапе было выбрано использовать одну сущность
    val descriptor: String
)

enum class CellType {
    CELL,
    LIFE
}