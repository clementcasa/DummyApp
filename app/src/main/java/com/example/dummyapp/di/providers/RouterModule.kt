package com.example.dummyapp.di.providers

import com.example.dummyapp.data.providers.network.router.UserRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class RouterModule {
    @Provides
    fun provideUserRouter(retrofit: Retrofit): UserRouter = retrofit.create(UserRouter::class.java)
}