package com.example.dummyapp.ui.modules.main

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DummyAppApplication : Application() {}

val Context.citygoApp: DummyAppApplication
    get() = applicationContext as DummyAppApplication