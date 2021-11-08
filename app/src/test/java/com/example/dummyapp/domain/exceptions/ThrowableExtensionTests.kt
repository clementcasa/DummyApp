package com.example.dummyapp.domain.exceptions

import com.example.dummyapp.domain.exception.toDomainExceptionType
import com.example.dummyapp.utils.fixtures.exceptions.DataExceptionsFixtures
import com.example.dummyapp.utils.fixtures.exceptions.DomainExceptionsFixtures
import io.reactivex.rxjava3.exceptions.CompositeException
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RxThrowableExtensionKtTests {
    
    @Test
    fun testToDomainExceptionTypeWhenIsComposite() {
        val expectedException = DomainExceptionsFixtures.DomainNetworkUtils.createBadRequest("domain error")
        val compositeException: Throwable = CompositeException(DataExceptionsFixtures.DataAPIExceptionUtils.create("data error"), expectedException)
    
        assertThat(compositeException.toDomainExceptionType(), equalTo(expectedException))
    }
    
    @Test
    fun testToDomainExceptionTypeWhenIsDefaultDomainException() {
        val expectedException = DomainExceptionsFixtures.DomainNetworkUtils.createBadRequest("domain error")
    
        assertThat(expectedException.toDomainExceptionType(), equalTo(expectedException))
    }
}