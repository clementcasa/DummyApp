package com.example.dummyapp.utils.fixtures.exceptions

import com.example.dummyapp.domain.exception.DomainNetworkException

class DomainExceptionsFixtures {
    
    //region DomainNetworkExceptions
    object DomainNetworkUtils {
        fun createBadRequest(message: String) = DomainNetworkException.BadRequest(message)
        
        fun createUnauthorized(message: String) = DomainNetworkException.Unauthorized(message)
        
        fun createForbidden(message: String) = DomainNetworkException.Forbidden(message)
        
        fun createNotFound(message: String) = DomainNetworkException.NotFound(message)
        
        fun createConflict(message: String) = DomainNetworkException.Conflict(message)
        
        fun createInternalError(message: String) = DomainNetworkException.InternalError(message)
    }
    //endregion
}