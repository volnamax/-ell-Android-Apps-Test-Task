package com.example.cell_android_apps_test_task.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cell_android_apps_test_task.presentation.screen.main.CellsCreateScreen
import com.example.cell_android_apps_test_task.presentation.ui.theme.CellAndroidAppsTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CellAndroidAppsTestTaskTheme {
                // Вызовем отдельный экран CellScreen
                CellsCreateScreen()
            }
        }
    }
}