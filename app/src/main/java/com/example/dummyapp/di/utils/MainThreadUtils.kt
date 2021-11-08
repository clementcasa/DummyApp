package com.example.dummyapp.di.utils

import android.os.Looper

class MainThreadUtils {
    companion object {
        fun checkIfMainThread() { check(Looper.myLooper() != Looper.getMainLooper()) { "This cannot be called from other threads. It should be on the main thread only." } }
    }
}