package com.example.dummyapp.data.providers.network.api

import com.example.dummyapp.data.fixtures.DataFixtures
import com.example.dummyapp.data.models.network.DataUserDetailsResponse
import com.example.dummyapp.data.models.network.DataUserListResponse
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserServiceApiImpl @Inject constructor(): UserServiceApi {
    
    override fun getUserList(page: Int, limit: Int): Single<DataUserListResponse> =
        Single.timer(1, TimeUnit.SECONDS).flatMap {
            Single.just(DataFixtures.DataUserListResponseUtils.createRandom())
        }
    
    override fun getUserDetails(userId: String): Single<DataUserDetailsResponse> =
        Single.timer(1, TimeUnit.SECONDS).flatMap {
            Single.just(DataFixtures.DataUserDetailsResponseUtils.createRandom())
        }
}