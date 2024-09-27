package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cell_android_apps_test_task.domain.model.Cell
import com.example.cell_android_apps_test_task.domain.repository.ICellRepository
import com.example.cell_android_apps_test_task.presentation.mapper.toViewData
import com.example.cell_android_apps_test_task.presentation.model.CellViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID


class CellsCreateViewModule(private val cellRepository: ICellRepository) : ViewModel() {

    // Список клеток в виде StateFlow для наблюдения за изменениями
    private val _cells = MutableStateFlow<List<CellViewData>>(emptyList())
    val cells: StateFlow<List<CellViewData>> = _cells


    // Инициализация, которая автоматически загружает клетки при запуске экрана
    init {
        loadCells()
    }

    // Функция для загрузки клеток из репозитория
    private fun loadCells() {
        viewModelScope.launch {
            val cellsFromRepo = cellRepository.getAllCells().map { it.toViewData() }
            _cells.value = cellsFromRepo
        }
    }

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

            // После добавления обновляем список клеток
            loadCells()
        }
    }
}