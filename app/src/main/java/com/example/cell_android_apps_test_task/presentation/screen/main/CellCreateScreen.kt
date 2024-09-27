package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


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
            CellsList(cells = cells)
        }
    }
}

@Composable
fun CellsList(cells: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cells) { cell ->
            CellItem(cell)
        }
    }
}

@Composable
fun CellItem(cell: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = cell,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CellScreenPreview() {
    CellsCreateScreen()
}
