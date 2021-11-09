package com.example.dummyapp.domain.repositories

import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.models.DomainUserList
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getUserList(page: Int, limit: Int): Single<DomainUserList>
    fun getUserDetails(userId: String): Single<DomainUserDetails>
}