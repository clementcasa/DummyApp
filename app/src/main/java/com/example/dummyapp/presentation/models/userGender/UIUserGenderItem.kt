package com.example.dummyapp.presentation.models.userGender

import android.content.Context
import com.example.dummyapp.R

enum class UIUserGenderItem {
    MALE,
    FEMALE,
    OTHER,
    NONE;
    
    companion object {
        operator fun invoke(value: String): UIUserGenderItem =
            when(value) {
                "male" -> MALE
                "female" -> FEMALE
                "other" -> OTHER
                else -> NONE
            }
    }
    
    fun getGenderTitle(context: Context): String? =
        when(this) {
            MALE -> context.getString(R.string.user_gender_male)
            FEMALE -> context.getString(R.string.user_gender_female)
            OTHER -> context.getString(R.string.user_gender_other)
            NONE -> null
        }
}