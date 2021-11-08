package com.example.dummyapp.data.providers.network.api.protocols

import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.exceptions.DataNetworkException
import com.example.dummyapp.utils.fixtures.exceptions.DataExceptionsFixtures
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class RxServiceApiTests : RxServiceApi {
    data class DumbObject(val p1: Int, val p2: String)
    
    private val errorMessage = "Exception occured"
    
    @Test
    fun testToSingleApiSuccess() {
        val mockCall: Call<DumbObject> = mock(defaultAnswer = Mockito.RETURNS_SELF)
        val expectedData = DumbObject(1, "")
        var actualData: DumbObject? = null
        whenever(mockCall.clone().execute()).thenReturn(Response.success(expectedData))
        
        RxServiceApiTests()
                .createSingle(mockCall)
                .subscribe({
                    actualData = it
                }, { })
        assertThat(actualData, equalTo(expectedData))
        
        verify(mockCall).execute()
        verify(mockCall, times(2)).clone()
        verifyNoMoreInteractions(mockCall)
    }
    
    @Test
    fun testToSingleApiFailureWithNullBody() {
        val mockCall: Call<DumbObject> = mock(defaultAnswer = Mockito.RETURNS_SELF)
        var actualException: DataAPIDecodeException? = null
        val expectedException = DataAPIDecodeException("http://localhost/ body is null")
        whenever(mockCall.clone().execute()).thenReturn(Response.success(null))
        
        RxServiceApiTests()
                .createSingle(mockCall)
                .subscribe({ }, {
                    actualException = it as? DataAPIDecodeException
                })
    
        assertThat(actualException, equalTo(expectedException))
        verify(mockCall).execute()
        verify(mockCall, times(2)).clone()
        verifyNoMoreInteractions(mockCall)
    }
    
    @Test
    fun testToSingleApiFailure() {
        val mockCall: Call<DumbObject> = mock(defaultAnswer = Mockito.RETURNS_SELF)
        val expectedException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(404, errorMessage)
        var actualException: DataNetworkException? = null
        whenever(mockCall.clone().execute()).thenReturn(Response.error(404, errorMessage.toResponseBody()))

        RxServiceApiTests()
                .createSingle(mockCall)
                .subscribe({ }, {
                    actualException = it as? DataNetworkException
                })
    
        assertThat(actualException, equalTo(expectedException))
        verify(mockCall).execute()
        verify(mockCall, times(2)).clone()
        verifyNoMoreInteractions(mockCall)
    }
}