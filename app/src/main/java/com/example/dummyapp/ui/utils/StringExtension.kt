package com.example.dummyapp.ui.utils

import java.util.Locale

fun String.nullWhenEmpty() = if (isEmpty()) null else this