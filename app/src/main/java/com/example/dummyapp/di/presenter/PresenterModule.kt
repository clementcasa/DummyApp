package com.example.dummyapp.di.presenter

import com.example.dummyapp.presentation.modules.userdetails.UserDetailsPresenter
import com.example.dummyapp.presentation.modules.userdetails.UserDetailsPresenterImpl
import com.example.dummyapp.presentation.modules.userlist.UserListPresenter
import com.example.dummyapp.presentation.modules.userlist.UserListPresenterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PresenterModule {
    @Binds
    abstract fun bindUserDetailsPresenter(userDetailsPresenter: UserDetailsPresenterImpl): UserDetailsPresenter
    
    @Binds
    abstract fun bindUserListPresenter(userListPresenter: UserListPresenterImpl): UserListPresenter
}