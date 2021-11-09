package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.models.DomainModelConvertible
import com.example.dummyapp.domain.models.DomainUserList
import kotlinx.serialization.Serializable

@Serializable
data class DataUserListResponse(
    val data: List<DataUserPreviewResponse>,
    val total: Int,
    val page: Int,
    val limit: Int
) : DomainModelConvertible<DomainUserList> {
    
    override fun toDomain(): DomainUserList =
        DomainUserList(
            data = data.map { it.toDomain() },
            total = total,
            page = page,
            limit = limit
        )
}