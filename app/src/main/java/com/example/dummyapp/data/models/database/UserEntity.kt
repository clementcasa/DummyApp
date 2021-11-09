package com.example.dummyapp.data.models.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.models.network.DataUserLocationResponse
import com.example.dummyapp.data.utils.toDate
import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.models.DomainUserGender
import com.example.dummyapp.domain.models.DomainUserList
import com.example.dummyapp.domain.models.DomainUserPreview
import com.example.dummyapp.domain.models.DomainUserTitle

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val page: Int,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String?,
    val email: String?,
    val dateOfBirth: String?,
    val registerDate: String?,
    val phone: String?,
    val picture: String,
    @Embedded val location: DataUserLocationResponse?
) {
    fun toDomainUserPreview(): DomainUserPreview =
        DomainUserPreview(
            id = id,
            title = DomainUserTitle(title),
            firstName = firstName,
            lastName = lastName,
            picture = picture
        )
    
    fun toDomainUserDetails(): DomainUserDetails {
        val dateOfBirthDate = dateOfBirth?.toDate() ?: throw DataAPIDecodeException("dateOfBirth $dateOfBirth is not in the right format")
        val registerDateDate = registerDate?.toDate() ?: throw DataAPIDecodeException("registerDate $registerDate is not in the right format")
        if (gender != null && email != null && phone != null && location != null) {
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
        } else {
            throw DataAPIDecodeException("user info are incomplete")
        }
    }
}

fun List<UserEntity>.toDomainUserList(page: Int, limit: Int): DomainUserList =
    DomainUserList(
        data = map { it.toDomainUserPreview() },
        total = 100000, //TODO : Need to store the total in a shared pref for example
        page = page,
        limit = limit
    )
