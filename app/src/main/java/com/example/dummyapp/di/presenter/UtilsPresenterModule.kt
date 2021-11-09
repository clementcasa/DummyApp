package com.example.dummyapp.di.presenter

import com.example.dummyapp.presentation.utils.NetworkSchedulers
import com.example.dummyapp.presentation.utils.NetworkSchedulersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsPresenterModule {
    @Binds
    abstract fun bindNetworkSchedulers(networkSchedulers: NetworkSchedulersImpl): NetworkSchedulers
}
