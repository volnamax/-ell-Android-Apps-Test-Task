package com.example.cell_android_apps_test_task

import android.app.Application
import com.example.cell_android_apps_test_task.di.datasourceModule
import com.example.cell_android_apps_test_task.di.domainModule
import com.example.cell_android_apps_test_task.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CellApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CellApp)
            modules(datasourceModule, domainModule, viewmodelModule)
        }
    }
}