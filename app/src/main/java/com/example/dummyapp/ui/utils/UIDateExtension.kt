package com.example.dummyapp.ui.utils

import android.content.Context
import android.text.format.DateUtils
import java.util.Date

/**
 * Convert date to String with format : 23/05/1991 (FR) ; 5/23/1991 (US)
 */
fun Date.getFormatWithDayMonthAndYear(context: Context?): String =
        DateUtils.formatDateTime(context, this.time, DateUtils.FORMAT_NUMERIC_DATE or DateUtils.FORMAT_SHOW_YEAR)