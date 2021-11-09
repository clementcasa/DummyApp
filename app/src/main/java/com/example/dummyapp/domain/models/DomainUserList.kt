package com.example.dummyapp.domain.models

data class DomainUserList(
    val data: List<DomainUserPreview>,
    val total: Int,
    val page: Int,
    val limit: Int
)