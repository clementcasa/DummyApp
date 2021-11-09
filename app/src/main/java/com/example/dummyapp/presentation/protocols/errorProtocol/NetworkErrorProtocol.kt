package com.example.dummyapp.presentation.protocols.errorProtocol

import com.example.dummyapp.domain.exception.DomainNetworkException
import com.example.dummyapp.domain.exception.toDomainExceptionType

/**
 * Interface to implement callbacks when DomainNetworkException are triggered
 */
interface NetworkErrorProtocol : ErrorProtocol {
    fun onReceiveUnauthorizedError(message: String) {}
    fun onReceiveForbiddenError(message: String) {}
    fun onReceiveNotFoundError(message: String) {}
    fun onReceiveConflictError(message: String) {}
    fun onReceiveInternalError(message: String) {}
    
    override fun onReceiveError(exception: Throwable) {
        val domainException = exception.toDomainExceptionType() as? DomainNetworkException ?: return
        when (domainException) {
            is DomainNetworkException.BadRequest -> onReceiveInternalError(domainException.message)
            is DomainNetworkException.Unauthorized -> onReceiveUnauthorizedError(domainException.message)
            is DomainNetworkException.Forbidden -> onReceiveForbiddenError(domainException.message)
            is DomainNetworkException.NotFound -> onReceiveNotFoundError(domainException.message)
            is DomainNetworkException.Conflict -> onReceiveConflictError(domainException.message)
            is DomainNetworkException.InternalError -> onReceiveInternalError(domainException.message)
        }
    }
}