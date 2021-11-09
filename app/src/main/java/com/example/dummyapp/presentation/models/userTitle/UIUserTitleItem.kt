package com.example.dummyapp.presentation.models.userTitle

import android.content.Context
import com.example.dummyapp.R

enum class UIUserTitleItem {
    MR,
    MS,
    MRS,
    MISS,
    DR,
    NONE;
    
    companion object {
        operator fun invoke(value: String): UIUserTitleItem =
            when(value) {
                "mr" -> MR
                "ms" -> MS
                "mrs" -> MRS
                "miss" -> MISS
                "dr" -> DR
                else -> NONE
            }
    }
    
    fun getTitleText(context: Context): String? =
        when(this) {
            MR -> context.getString(R.string.user_title_mr)
            MS -> context.getString(R.string.user_title_ms)
            MRS ->context.getString(R.string.user_title_mrs)
            MISS -> context.getString(R.string.user_title_miss)
            DR -> context.getString(R.string.user_title_dr)
            NONE -> null
        }
}