package com.example.dummyapp.domain.models

import java.util.Date

data class DomainUserDetails(
    val id: String,
    val title: DomainUserTitle,
    val firstName: String,
    val lastName: String,
    val gender: DomainUserGender,
    val email: String,
    val dateOfBirth: Date,
    val registerDate: Date,
    val phone: String,
    val picture: String,
    val location: DomainUserLocation
)