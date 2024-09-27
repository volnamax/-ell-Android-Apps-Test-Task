package com.example.cell_android_apps_test_task.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cell_android_apps_test_task.presentation.screen.main.CellsCreateScreen
import com.example.cell_android_apps_test_task.presentation.ui.theme.BackGroundColorStart
import com.example.cell_android_apps_test_task.presentation.ui.theme.CellAndroidAppsTestTaskTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellAndroidAppsTestTaskTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Клеточное наполнение") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = BackGroundColorStart,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    content = { innerPadding ->
                        CellsCreateScreen(Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}