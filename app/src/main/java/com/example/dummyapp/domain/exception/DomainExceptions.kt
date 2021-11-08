package com.example.dummyapp.domain.exception

/**
 * Enum used to define all DomainExceptions used in Domain. The conversion is made from the Data layer
 */
sealed class DomainException : Exception()
sealed class DomainNetworkException(override val message: String) : DomainException() {
    data class BadRequest(override val message: String) : DomainNetworkException(message)
    data class Unauthorized(override val message: String) : DomainNetworkException(message)
    data class Forbidden(override val message: String) : DomainNetworkException(message)
    data class NotFound(override val message: String) : DomainNetworkException(message)
    data class Conflict(override val message: String) : DomainNetworkException(message)
    data class InternalError(override val message: String) : DomainNetworkException(message)
}