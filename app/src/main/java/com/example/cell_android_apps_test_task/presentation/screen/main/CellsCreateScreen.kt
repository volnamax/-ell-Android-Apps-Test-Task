package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cell_android_apps_test_task.R
import com.example.cell_android_apps_test_task.presentation.model.CellViewData
import com.example.cell_android_apps_test_task.presentation.ui.theme.ColorButtonCreateCell
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space16
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space4
import org.koin.androidx.compose.koinViewModel
import java.util.UUID


@Composable
fun CellsCreateScreen(
    viewModel: CellsCreateViewModule = koinViewModel() // Используем ViewModel через Koin
) {
    val cells = viewModel.cells.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addNewCell() }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CellsList(cells = cells, modifier = Modifier)
        }
    }
}

@Composable
fun CellsListContent(
    cells: List<CellViewData>,
    onCreateCell: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = Space4)
    ) {
        CellsList(
            cells = cells,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Space16)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Space16),
            onClick = onCreateCell,
            colors = ButtonDefaults.buttonColors(containerColor = ColorButtonCreateCell)
        ) {
            Text(text = stringResource(R.string.createCell), color = Color.Black)
        }
    }
}

@Composable
fun CellsList(cells: List<CellViewData>, modifier: Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cells) { cell ->
            CellItem(cell)
        }
    }
}

@Composable
fun CellItem(cell: CellViewData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Клетка: ${cell.descriptor}")
            Text(text = if (cell.isLive) "Живая" else "Мертвая")
        }
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
            descriptor = "Клетка 1"
        ),
        CellViewData(
            id = UUID.randomUUID(),
            isLive = false,
            descriptor = "Клетка 2"
        ),
        CellViewData(
            id = UUID.randomUUID(),
            isLive = true,
            descriptor = "Клетка 3"
        )
    )

    CellsListContent(cells = cells, modifier = Modifier)
}