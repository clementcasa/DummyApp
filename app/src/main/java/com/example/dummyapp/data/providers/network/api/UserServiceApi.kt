package com.example.dummyapp.data.providers.network.api

import com.example.dummyapp.data.models.network.DataUserDetailsResponse
import com.example.dummyapp.data.models.network.DataUserListResponse
import io.reactivex.rxjava3.core.Single

interface UserServiceApi {
    fun getUserList(page: Int, limit: Int): Single<DataUserListResponse>
    fun getUserDetails(userId: String): Single<DataUserDetailsResponse>
}