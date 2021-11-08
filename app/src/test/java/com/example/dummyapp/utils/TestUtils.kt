package com.example.dummyapp.utils

import com.example.dummyapp.BuildConfig

// temporary solution for having full running tests, solution seems to be to create multiple test folder but seems complicated
// https://stackoverflow.com/questions/57342382/how-do-i-prevent-a-flavor-specific-unit-test-from-being-compiled-in-another-flav
fun runTestIfNotMockFlavor(testBlock: () -> Unit) {
    if (BuildConfig.FLAVOR != "mock") {
        testBlock()
    }
}