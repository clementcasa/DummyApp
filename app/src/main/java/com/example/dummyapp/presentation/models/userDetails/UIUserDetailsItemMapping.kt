package com.example.dummyapp.presentation.models.userDetails

import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.presentation.models.userGender.toUIItem
import com.example.dummyapp.presentation.models.userLocation.toUIItem
import com.example.dummyapp.presentation.models.userTitle.toUIItem

fun DomainUserDetails.toUIItem(): UIUserDetailsItem =
    UIUserDetailsItem(id, title.toUIItem(), firstName, lastName, gender.toUIItem(), email, dateOfBirth, registerDate, phone, picture, location.toUIItem())