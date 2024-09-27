package com.example.cell_android_apps_test_task.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cell_android_apps_test_task.presentation.screen.main.CellsCreateScreen
import com.example.cell_android_apps_test_task.presentation.ui.theme.CellAndroidAppsTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CellAndroidAppsTestTaskTheme {
                CellsCreateScreen()
            }
        }
    }
}