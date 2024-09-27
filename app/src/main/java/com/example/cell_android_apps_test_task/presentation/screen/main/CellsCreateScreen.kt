package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.cell_android_apps_test_task.R
import com.example.cell_android_apps_test_task.domain.model.CellType
import com.example.cell_android_apps_test_task.presentation.model.CellViewData
import com.example.cell_android_apps_test_task.presentation.ui.theme.*
import org.koin.androidx.compose.koinViewModel
import java.util.UUID


@Composable
fun CellsCreateScreen(
    modifier: Modifier = Modifier,
    viewModel: CellsCreateViewModule = koinViewModel(),
) {
    val cells = viewModel.cells.collectAsState().value
    val listState = rememberLazyListState() // Состояние списка для прокрутки
    val scope = rememberCoroutineScope()
    // Оборачиваем в Box для добавления фона
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(BackGroundColorStart, Color.Black)  // Градиент
                )
            )
            .padding(Space16)
    ) {
        // Контент экрана
        CellsListContent(
            cells = cells,
            onCreateCell = {
                viewModel.addNewCell()
            },
            listState = listState,
            modifier = modifier
        )

        // Используем LaunchedEffect для прокрутки после добавления клетки
        LaunchedEffect(cells.size) {
            // Прокручиваем до последнего элемента (после обновления списка)
            if (cells.isNotEmpty()) {
                listState.animateScrollToItem(cells.size - 1)
            }
        }
    }
}

@Composable
fun CellsListContent(
    cells: List<CellViewData>,
    onCreateCell: () -> Unit,
    listState: LazyListState,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = Space2)
            .padding(horizontal = Space8)
    ) {
        CellsList(
            cells = cells,
            listState = listState,

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Button(
            onClick = onCreateCell,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Space4)
                .padding(vertical = Space16),
            colors = ButtonDefaults.buttonColors(containerColor = ColorButtonCreateCell),
            shape = RoundedCornerShape(Size6)
        ) {
            Text(text = stringResource(R.string.createCell), color = Color.White)
        }

    }
}

@Composable
fun CellsList(cells: List<CellViewData>, listState: LazyListState, modifier: Modifier) {
    LazyColumn(
        state = listState,  // Используем переданное состояние списка
        modifier = modifier.fillMaxSize()
    ) {
        items(cells) { cell ->
            CellItem(cell, modifier = Modifier)
        }
    }
}


@Composable
fun CellItem(cell: CellViewData, modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Space6),
        elevation = CardDefaults.cardElevation(Space4)
    ) {
        Row(
            modifier = Modifier.padding(Space8),
            horizontalArrangement = Arrangement.Start
        ) {
            // Аватарка с градиентом и смайликом
            AvatarWithEmoji(cell = cell)


            Spacer(modifier = Modifier.width(Space8))

            Column(modifier = Modifier.padding(Space16)) {
                if (cell.type == CellType.CELL)
                    Text(
                        text = if (cell.isLive) stringResource(R.string.Live) else stringResource(R.string.Die),
                        fontWeight = FontWeight.Bold,
                        fontSize = SizeSp20
                    )
                else
                    Text(
                        text = if (cell.isLive) stringResource(R.string.IsLive) else stringResource(
                            R.string.IsDie
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = SizeSp20
                    )

                Text(text = cell.descriptor)

            }
        }
    }
}


@Composable
fun AvatarWithEmoji(cell: CellViewData) {
    val gradientColors = when {
        cell.type == CellType.LIFE && cell.isLive -> listOf(Color(0xFFb817fc), Color(0xFFfdace9))
        cell.type == CellType.CELL && cell.isLive -> listOf(Color(0xFFFFA726), Color(0xFFFFD54F))
        cell.type == CellType.CELL && !cell.isLive -> listOf(Color(0xFF1E748E), Color(0xFFaefdb3))
        else -> listOf(Color(0xFFFF3B26), Color(0xFFFF9388))
    }

    Box(
        modifier = Modifier.size(Size68), // Размер аватарки
        contentAlignment = Alignment.Center // Центрируем смайлик
    ) {
        // Рисуем круг с градиентом
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                brush = Brush.verticalGradient(colors = gradientColors),
                radius = size.minDimension / 2
            )
        }

        // Смайлик поверх круга
        val emoji = when {
            cell.type == CellType.CELL && cell.isLive -> "💥"
            cell.type == CellType.CELL && !cell.isLive -> "💀"
            cell.type == CellType.LIFE && cell.isLive -> "🐣"
            else -> "🤬"
        }

        Text(text = emoji, fontSize = SizeSp32)
    }
}

@Preview(showBackground = true)
@Composable
fun CellScreenPreview() {
    CellsCreateScreen()
}


@Preview(showBackground = true)
@Composable
fun CellScreenContentPreview() {
    val cells: List<CellViewData> = listOf(
        CellViewData(
            id = UUID.randomUUID(),
            isLive = true,
            descriptor = "Клетка 1",
            type = CellType.CELL
        ),
        CellViewData(
            id = UUID.randomUUID(),
            isLive = false,
            descriptor = "Клетка 2",
            type = CellType.CELL
        ),
        CellViewData(
            id = UUID.randomUUID(),
            isLive = true,
            descriptor = "Клетка 3",
            type = CellType.CELL
        )
    )
    CellsListContent(
        cells = cells,
        onCreateCell = {},
        listState = rememberLazyListState(),
        modifier = Modifier
    )
}