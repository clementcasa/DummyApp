package com.example.dummyapp.data.providers.network.router

import com.example.dummyapp.data.models.network.DataUserDetailsResponse
import com.example.dummyapp.data.models.network.DataUserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRouter {
    
    @GET("/data/v1/user")
    fun getUserList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<DataUserListResponse>
    
    @GET("/data/v1/user/{userId}")
    fun getUserDetails(
        @Path("userId") userId: String
    ): Call<DataUserDetailsResponse>
}