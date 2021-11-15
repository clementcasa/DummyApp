package com.example.dummyapp.data.providers.network.api

import com.example.dummyapp.data.providers.network.router.UserRouter
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UserServiceApiImplTests {
    private val mockUserRouter: UserRouter = mock()
    private val serviceApi = UserServiceApiImpl(mockUserRouter)
    
    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockUserRouter)
    }
    
    @Test
    fun testGetUserList() {
        val expectedPage = 0
        val expectedLimit = 10
        whenever(mockUserRouter.getUserList(any(), any())).thenReturn(mock())
        serviceApi.getUserList(expectedPage, expectedLimit)
        verify(mockUserRouter).getUserList(expectedPage, expectedLimit)
    }
    
    @Test
    fun testGetUserDetails() {
        val expectedUserId = "userId"
        whenever(mockUserRouter.getUserDetails(any())).thenReturn(mock())
        serviceApi.getUserDetails(expectedUserId)
        verify(mockUserRouter).getUserDetails(expectedUserId)
    }
}