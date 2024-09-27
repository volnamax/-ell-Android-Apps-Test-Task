package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cell_android_apps_test_task.domain.model.Cell
import com.example.cell_android_apps_test_task.domain.model.CellType
import com.example.cell_android_apps_test_task.domain.repository.ICellRepository
import com.example.cell_android_apps_test_task.presentation.mapper.toViewData
import com.example.cell_android_apps_test_task.presentation.model.CellViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID


class CellsCreateViewModule(private val cellRepository: ICellRepository) : ViewModel() {
    private val _cells = MutableStateFlow<List<CellViewData>>(emptyList())
    val cells: StateFlow<List<CellViewData>> = _cells

    private var consecutiveAliveCount = 0
    private var consecutiveDeadCount = 0

    init {
        loadCells()
    }

    private fun loadCells() {
        viewModelScope.launch {
            val cellsFromRepo = cellRepository.getAllCells().map { it.toViewData() }
            _cells.value = cellsFromRepo
        }
    }

    fun addNewCell() {
        viewModelScope.launch {
            val isAlive = (0..1).random() == 1
            val descriptor = if (isAlive) "и шевелится!" else "или прикидывается"

            val newCell = Cell(
                id = UUID.randomUUID(),
                isAlive = isAlive,
                descriptor = descriptor,
                type = CellType.CELL
            )

            // Добавляем новую клетку в репозиторий
            cellRepository.addCell(newCell)

            // Обновляем список клеток
            val updatedCells = cellRepository.getAllCells().map { it.toViewData() }
            _cells.value = updatedCells

            // Проверяем условия подряд живых или мёртвых клеток
            if (isAlive) {
                consecutiveAliveCount++
                consecutiveDeadCount = 0
                // Если три подряд живые клетки — добавляем "жизнь"
                if (consecutiveAliveCount == 2) {
                    addLife()
                    consecutiveAliveCount = 0 // Сбрасываем счётчик
                }
            } else {
                consecutiveDeadCount++
                consecutiveAliveCount = 0
                // Если три подряд мёртвые клетки — убираем жизнь
                if (consecutiveDeadCount == 2) {
                    updateLifeToDeadIfExists()
                    consecutiveDeadCount = 0 // Сбрасываем счётчик
                }
            }
        }
    }

    // Добавляем "жизнь" в список клеток
    private suspend fun addLife() {
        val newLifeCell = Cell(
            id = UUID.randomUUID(),
            isAlive = true,
            descriptor = "Ку-ку!",
            type = CellType.LIFE
        )
        cellRepository.addCell(newLifeCell)

        // Перезагружаем список с добавлением "жизни"
        val cellsWithLife = cellRepository.getAllCells().map { it.toViewData() }
        _cells.value = cellsWithLife
    }

    // Превращаем последнюю клетку "жизнь" в "мёртвую жизнь", если она существует
    private suspend fun updateLifeToDeadIfExists() {
        val currentCells = _cells.value
        val lifeCell = currentCells.findLast { it.type == CellType.LIFE }

        // Если найдена "жизнь", обновляем её на "мёртвую жизнь"
        lifeCell?.let {
            val updatedDeadLifeCell = Cell(
                id = it.id,
                isAlive = false,  // Делаем клетку мёртвой
                descriptor = "Сдохла",
                type = CellType.LIFE  // Меняем тип на обычную клетку
            )

            // Используем метод updateCell для обновления клетки
            cellRepository.updateCell(updatedDeadLifeCell)

            // Перезагружаем список с обновлённой клеткой
            val cellsWithUpdatedLife = cellRepository.getAllCells().map { it.toViewData() }
            _cells.value = cellsWithUpdatedLife
        }
    }
}