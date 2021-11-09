package com.example.dummyapp.presentation.models.userPreview

import com.example.dummyapp.presentation.models.userTitle.UIUserTitleItem

data class UIUserPreviewItem(
    val id: String,
    val title: UIUserTitleItem,
    val firstName: String,
    val lastName: String,
    val picture: String
) {
    fun getUserFullName(): String = "$firstName $lastName"
}