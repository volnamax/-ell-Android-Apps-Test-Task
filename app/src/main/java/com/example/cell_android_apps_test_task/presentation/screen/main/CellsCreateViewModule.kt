package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cell_android_apps_test_task.domain.model.Cell
import com.example.cell_android_apps_test_task.domain.repository.ICellRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID


class CellsCreateViewModule(private val cellRepository: ICellRepository ) : ViewModel() {
    // Список клеток в виде StateFlow для наблюдения за изменениями
    private val _cells = MutableStateFlow<List<String>>(emptyList())
    val cells: StateFlow<List<String>> = _cells

    // Добавление новой клетки в базу данных
    fun addNewCell() {
        viewModelScope.launch {
            val newCell = Cell(
                id = UUID.randomUUID(),
                isAlive = (0..1).random() == 1,
                descriptor = if ((0..1).random() == 1) "Живая" else "Мертвая"
            )

            // Добавляем новую клетку в репозиторий
            cellRepository.addCell(newCell)

            // Обновляем список клеток для отображения
            val updatedCells = cellRepository.getAllCells().map { it.descriptor }
            _cells.value = updatedCells
        }
    }
}
