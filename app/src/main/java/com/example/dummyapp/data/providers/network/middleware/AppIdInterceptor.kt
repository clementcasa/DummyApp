package com.example.dummyapp.data.providers.network.middleware

import com.example.dummyapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppIdInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header("app-id", BuildConfig.API_APP_ID)
            .build()
        return chain.proceed(newRequest)
    }
}