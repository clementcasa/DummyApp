package com.example.dummyapp.domain.models

data class DomainUserPreview(
    val id: String,
    val title: DomainUserTitle,
    val firstName: String,
    val lastName: String,
    val picture: String
)