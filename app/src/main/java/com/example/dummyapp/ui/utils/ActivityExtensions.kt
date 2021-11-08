package com.example.dummyapp.ui.utils

import android.app.Activity
import android.view.ViewGroup

val Activity.activityRootView: ViewGroup?
    get() = findViewById(android.R.id.content)