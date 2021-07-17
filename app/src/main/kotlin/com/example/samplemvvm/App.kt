package com.example.samplemvvm

import android.app.Application
import com.example.samplemvvm.container.AppContainer

class App : Application() {

    val appContainer: AppContainer = AppContainer()
}