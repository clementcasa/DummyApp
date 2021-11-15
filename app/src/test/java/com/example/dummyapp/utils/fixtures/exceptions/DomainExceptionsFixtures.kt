package com.example.dummyapp.utils.fixtures.exceptions

import com.example.dummyapp.domain.exception.DomainNetworkException

class DomainExceptionsFixtures {
    
    //region DomainNetworkExceptions
    object DomainNetworkUtils {
        fun createBadRequest(message: String = "message") = DomainNetworkException.BadRequest(message)
        
        fun createUnauthorized(message: String = "message") = DomainNetworkException.Unauthorized(message)
        
        fun createForbidden(message: String = "message") = DomainNetworkException.Forbidden(message)
        
        fun createNotFound(message: String = "message") = DomainNetworkException.NotFound(message)
        
        fun createConflict(message: String = "message") = DomainNetworkException.Conflict(message)
        
        fun createInternalError(message: String = "message") = DomainNetworkException.InternalError(message)
    }
    //endregion
}