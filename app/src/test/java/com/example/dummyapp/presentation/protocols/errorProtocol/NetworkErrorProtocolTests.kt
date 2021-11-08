package com.example.dummyapp.presentation.protocols.errorProtocol

import com.example.dummyapp.domain.exception.DomainNetworkException
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

class TestNetworkErrorProtocol : NetworkErrorProtocol {
    private var onReceiveUnauthorizedCalled = false
    private var onReceiveForbiddenCalled = false
    private var onReceiveNotFoundCalled = false
    private var onReceiveConflictCalled = false
    private var onReceiveInternalErrorCalled = false
    
    override fun onReceiveUnauthorizedError(message: String) {
        onReceiveUnauthorizedCalled = true
    }
    
    override fun onReceiveNotFoundError(message: String) {
        onReceiveNotFoundCalled = true
    }
    
    override fun onReceiveForbiddenError(message: String) {
        onReceiveForbiddenCalled = true
    }
    
    override fun onReceiveConflictError(message: String) {
        onReceiveConflictCalled = true
    }
    
    override fun onReceiveInternalError(message: String) {
        onReceiveInternalErrorCalled = true
    }
    
    fun resetExpectedCall() {
        onReceiveUnauthorizedCalled = false
        onReceiveForbiddenCalled = false
        onReceiveNotFoundCalled = false
        onReceiveConflictCalled = false
        onReceiveInternalErrorCalled = false
    }
    
    fun isOnReceiveUnauthorizedErrorCalled(): Boolean {
        return onReceiveUnauthorizedCalled &&
                !onReceiveForbiddenCalled &&
                !onReceiveNotFoundCalled &&
                !onReceiveConflictCalled &&
                !onReceiveInternalErrorCalled
    }
    
    fun isOnReceiveForbiddenErrorCalled(): Boolean {
        return !onReceiveUnauthorizedCalled &&
                onReceiveForbiddenCalled &&
                !onReceiveNotFoundCalled &&
                !onReceiveConflictCalled &&
                !onReceiveInternalErrorCalled
    }
    
    fun isOnReceiveNotFoundErrorCalled(): Boolean {
        return !onReceiveUnauthorizedCalled &&
                !onReceiveForbiddenCalled &&
                onReceiveNotFoundCalled &&
                !onReceiveConflictCalled &&
                !onReceiveInternalErrorCalled
    }
    
    fun isOnReceiveConflictErrorCalled(): Boolean {
        return !onReceiveUnauthorizedCalled &&
                !onReceiveForbiddenCalled &&
                !onReceiveNotFoundCalled &&
                onReceiveConflictCalled &&
                !onReceiveInternalErrorCalled
    }
    
    fun isOnReceiveInternalErrorCalled(): Boolean {
        return !onReceiveUnauthorizedCalled &&
                !onReceiveForbiddenCalled &&
                !onReceiveNotFoundCalled &&
                !onReceiveConflictCalled &&
                onReceiveInternalErrorCalled
    }
}

@RunWith(MockitoJUnitRunner::class)
class NetworkErrorProtocolTests {
    private val testView = TestNetworkErrorProtocol()
    
    @Test
    fun testOnReceiveError() {
        DomainNetworkException::class.nestedClasses.forEach {
            when (it) {
                DomainNetworkException.BadRequest::class -> {
                    testView.onReceiveError(DomainNetworkException.BadRequest("expected message"))
                    assertThat(testView.isOnReceiveInternalErrorCalled(), equalTo(true))
                }
                DomainNetworkException.Unauthorized::class -> {
                    testView.onReceiveError(DomainNetworkException.Unauthorized("expected message"))
                    assertThat(testView.isOnReceiveUnauthorizedErrorCalled(), equalTo(true))
                }
                DomainNetworkException.Forbidden::class -> {
                    testView.onReceiveError(DomainNetworkException.Forbidden("expected message"))
                    assertThat(testView.isOnReceiveForbiddenErrorCalled(), equalTo(true))
                }
                DomainNetworkException.NotFound::class -> {
                    testView.onReceiveError(DomainNetworkException.NotFound("expected message"))
                    assertThat(testView.isOnReceiveNotFoundErrorCalled(), equalTo(true))
                }
                DomainNetworkException.Conflict::class -> {
                    testView.onReceiveError(DomainNetworkException.Conflict("expected message"))
                    assertThat(testView.isOnReceiveConflictErrorCalled(), equalTo(true))
                }
                DomainNetworkException.InternalError::class -> {
                    testView.onReceiveError(DomainNetworkException.InternalError("expected message"))
                    assertThat(testView.isOnReceiveInternalErrorCalled(), equalTo(true))
                }
                else -> Assert.fail("Missing test for new value of enum DomainNetworkException.Error")
            }
            testView.resetExpectedCall()
        }
    }
}