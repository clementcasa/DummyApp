package com.example.dummyapp.di.providers

import com.example.dummyapp.data.providers.network.api.UserServiceApi
import com.example.dummyapp.data.providers.network.api.UserServiceApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ServiceApiModule {
    @Binds
    abstract fun bindUserServiceApi(userServiceApi: UserServiceApiImpl): UserServiceApi
}