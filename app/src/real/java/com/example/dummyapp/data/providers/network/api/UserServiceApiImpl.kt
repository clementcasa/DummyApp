package com.example.dummyapp.data.providers.network.api

import com.example.dummyapp.data.models.network.DataUserDetailsResponse
import com.example.dummyapp.data.models.network.DataUserListResponse
import com.example.dummyapp.data.providers.network.api.protocols.RxServiceApi
import com.example.dummyapp.data.providers.network.router.UserRouter
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserServiceApiImpl @Inject constructor(
    private val userRouter: UserRouter
): UserServiceApi, RxServiceApi {
    
    override fun getUserList(page: Int, limit: Int): Single<DataUserListResponse> =
        createSingle(userRouter.getUserList(page, limit))
    
    override fun getUserDetails(userId: String): Single<DataUserDetailsResponse> =
        createSingle(userRouter.getUserDetails(userId))
}