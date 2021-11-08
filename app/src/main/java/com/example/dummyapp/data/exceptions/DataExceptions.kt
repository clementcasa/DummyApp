package com.example.dummyapp.data.exceptions

import com.example.dummyapp.domain.exception.DomainException
import com.example.dummyapp.domain.exception.DomainNetworkException
import java.net.HttpURLConnection

/**
 * Enum that represent all DataException that could be fired from data layer to domain layer
 */
sealed class DataException : Exception(), DomainExceptionConvertible
data class DataNetworkException(val code: Int, override val message: String) : DataException() {
    override fun toDomain(): DomainException = when (code) {
        HttpURLConnection.HTTP_BAD_REQUEST -> DomainNetworkException.BadRequest(message)
        HttpURLConnection.HTTP_UNAUTHORIZED -> DomainNetworkException.Unauthorized(message)
        HttpURLConnection.HTTP_FORBIDDEN -> DomainNetworkException.Forbidden(message)
        HttpURLConnection.HTTP_NOT_FOUND -> DomainNetworkException.NotFound(message)
        HttpURLConnection.HTTP_CONFLICT -> DomainNetworkException.Conflict(message)
        else -> DomainNetworkException.InternalError(message)
    }
}
data class DataAPIDecodeException(override val message: String) : DataException() {
    override fun toDomain(): DomainException =
        DomainNetworkException.InternalError(message)
}