package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.models.DomainModelConvertible
import com.example.dummyapp.data.models.database.UserEntity
import com.example.dummyapp.domain.models.DomainUserPreview
import com.example.dummyapp.domain.models.DomainUserTitle
import kotlinx.serialization.Serializable

@Serializable
data class DataUserPreviewResponse(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String
) : DomainModelConvertible<DomainUserPreview> {
    
    override fun toDomain(): DomainUserPreview =
        DomainUserPreview(
            id = id,
            title = DomainUserTitle(title),
            firstName = firstName,
            lastName = lastName,
            picture = picture
        )
    
    fun toEntity(page: Int): UserEntity =
        UserEntity(
            id = id,
            page = page,
            title = title,
            firstName = firstName,
            lastName = lastName,
            gender = null,
            email = null,
            dateOfBirth = null,
            registerDate = null,
            phone = null,
            picture = picture,
            location = null,
        )
}