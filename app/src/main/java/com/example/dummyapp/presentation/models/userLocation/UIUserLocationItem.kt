package com.example.dummyapp.presentation.models.userLocation

data class UIUserLocationItem(
    val street: String,
    val city: String,
    val state: String,
    val country: String
) {
    fun getAddress(): String =
        "$street, $city, $state, $country"
}