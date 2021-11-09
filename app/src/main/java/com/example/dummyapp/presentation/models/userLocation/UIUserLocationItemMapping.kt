package com.example.dummyapp.presentation.models.userLocation

import com.example.dummyapp.domain.models.DomainUserLocation

fun DomainUserLocation.toUIItem(): UIUserLocationItem =
    UIUserLocationItem(street, city, state, country)