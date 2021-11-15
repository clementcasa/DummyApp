package com.example.dummyapp.domain.models

data class DomainUserLocation(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String?
)