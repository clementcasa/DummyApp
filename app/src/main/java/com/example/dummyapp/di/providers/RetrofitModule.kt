package com.example.dummyapp.di.providers

import com.example.dummyapp.BuildConfig
import com.example.dummyapp.data.providers.network.middleware.AppIdInterceptor
import com.example.dummyapp.di.utils.MainThreadUtils
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    
    @Singleton
    @Provides
    fun provideJson(): Json =
        Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    
    @Singleton
    @Provides
    fun provideJsonConverter(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())
    
    @Singleton
    @Provides
    fun provideOkHttpClient(
        appIdInterceptor: AppIdInterceptor
    ): OkHttpClient {
        MainThreadUtils.checkIfMainThread()
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(appIdInterceptor)
            .build()
    }
    
    @Singleton
    @Provides
    fun provideRetrofit(
        client: Lazy<OkHttpClient>,
        jsonFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(jsonFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .callFactory { client.get().newCall(it) }
            .build()
}