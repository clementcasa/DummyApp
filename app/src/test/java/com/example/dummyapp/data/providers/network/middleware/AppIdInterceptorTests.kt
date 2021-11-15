package com.example.dummyapp.data.providers.network.middleware

import com.example.dummyapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AppIdInterceptorTests {
    private val mockChain: Interceptor.Chain = mock()
    private val mockRequest: Request = mock()
    private var mockNewRequest: Request = mock()
    private var mockBuilder: Request.Builder = mock()
    private var mockBuilderWithHeader: Request.Builder = mock()
    private var mockResponse: Response = mock()
    private val interceptor = AppIdInterceptor()
    
    @Test
    fun testIntercept() {
        val expectedAppId = BuildConfig.API_APP_ID
        whenever(mockChain.request()).thenReturn(mockRequest)
        whenever(mockRequest.newBuilder()).thenReturn(mockBuilder)
        whenever(mockBuilder.header(any(), any())).thenReturn(mockBuilderWithHeader)
        whenever(mockBuilderWithHeader.build()).thenReturn(mockNewRequest)
        whenever(mockChain.proceed(any())).thenReturn(mockResponse)
        
        interceptor.intercept(mockChain)
        
        verify(mockChain).request()
        verify(mockRequest).newBuilder()
        verify(mockBuilder).header("app-id", expectedAppId)
        verify(mockBuilderWithHeader).build()
        verify(mockChain).proceed(mockNewRequest)
        verifyNoMoreInteractions(mockChain, mockRequest, mockBuilder)
    }
}