package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.models.DomainModelConvertible
import com.example.dummyapp.domain.models.DomainUserLocation
import kotlinx.serialization.Serializable

@Serializable
data class DataUserLocationResponse(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String?
) : DomainModelConvertible<DomainUserLocation> {
    
    override fun toDomain(): DomainUserLocation =
        DomainUserLocation(
            street = street,
            city = city,
            state = state,
            country = country,
            timezone = timezone
        )
}