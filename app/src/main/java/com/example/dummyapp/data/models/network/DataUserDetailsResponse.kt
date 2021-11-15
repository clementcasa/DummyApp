package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.models.DomainModelConvertible
import com.example.dummyapp.data.utils.toDate
import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.models.DomainUserGender
import com.example.dummyapp.domain.models.DomainUserTitle
import kotlinx.serialization.Serializable

@Serializable
data class DataUserDetailsResponse(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val registerDate: String,
    val phone: String,
    val picture: String,
    val location: DataUserLocationResponse
) : DomainModelConvertible<DomainUserDetails> {
    
    override fun toDomain(): DomainUserDetails {
        val dateOfBirthDate = dateOfBirth.toDate() ?: throw DataAPIDecodeException("dateOfBirth $dateOfBirth is not in the right format")
        val registerDateDate = registerDate.toDate() ?: throw DataAPIDecodeException("registerDate $registerDate is not in the right format")
        
        return DomainUserDetails(
            id = id,
            title = DomainUserTitle(title),
            firstName = firstName,
            lastName = lastName,
            gender = DomainUserGender(gender),
            email = email,
            dateOfBirth = dateOfBirthDate,
            registerDate = registerDateDate,
            phone = phone,
            picture = picture,
            location = location.toDomain()
        )
    }
}