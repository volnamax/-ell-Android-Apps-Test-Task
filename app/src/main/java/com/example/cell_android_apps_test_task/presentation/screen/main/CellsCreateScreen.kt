package com.example.cell_android_apps_test_task.presentation.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import com.example.cell_android_apps_test_task.presentation.ui.theme.Size6
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space16
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space2
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space4
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space6
import com.example.cell_android_apps_test_task.presentation.ui.theme.Space8
import org.koin.androidx.compose.koinViewModel
import java.util.UUID


@Composable
fun CellsCreateScreen(
    modifier: Modifier = Modifier,
    viewModel: CellsCreateViewModule = koinViewModel(),
) {
    val cells = viewModel.cells.collectAsState().value
    CellsListContent(cells, onCreateCell = { viewModel.addNewCell() }, modifier = Modifier)
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
            .padding(vertical = Space2)
            .padding(horizontal = Space16)
    ) {
        CellsList(
            cells = cells,
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
fun CellsList(cells: List<CellViewData>, modifier: Modifier) {
    LazyColumn(
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
            .padding(Space6),
        elevation = CardDefaults.cardElevation(Space4)
    ) {
        Column(modifier = Modifier.padding(Space16)) {
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
    CellsListContent(cells = cells, onCreateCell = {}, modifier = Modifier)
}