package com.example.dummyapp.presentation.models.userDetails

import com.example.dummyapp.presentation.models.userGender.UIUserGenderItem
import com.example.dummyapp.presentation.models.userLocation.UIUserLocationItem
import com.example.dummyapp.presentation.models.userTitle.UIUserTitleItem
import java.util.Date

data class UIUserDetailsItem(
    val id: String,
    val title: UIUserTitleItem,
    val firstName: String,
    val lastName: String,
    val gender: UIUserGenderItem,
    val email: String,
    val dateOfBirth: Date,
    val registerDate: Date,
    val phone: String,
    val picture: String,
    val location: UIUserLocationItem
)