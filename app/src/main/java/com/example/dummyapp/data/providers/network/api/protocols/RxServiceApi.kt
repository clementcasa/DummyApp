package com.example.dummyapp.data.providers.network.api.protocols

import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.exceptions.DataNetworkException
import io.reactivex.rxjava3.core.Single
import retrofit2.Call

interface RxServiceApi {
    
    fun <T : Any> createSingle(call: Call<T>): Single<T> =
        Single.create { single ->
            call.clone().execute().apply {
                if (isSuccessful) {
                    when (val body = body()) {
                        null -> single.onError(DataAPIDecodeException(raw().request.url.toString() + " body is null"))
                        else -> single.onSuccess(body)
                    }
                } else {
                    when (val errorBody = errorBody()?.string()) {
                        "", null -> single.onError(DataAPIDecodeException(raw().request.url.toString() + " error body is null"))
                        else -> single.onError(DataNetworkException(code(), errorBody))
                    }
                }
            }
        }
}