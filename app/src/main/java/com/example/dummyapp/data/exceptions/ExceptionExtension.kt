package com.example.dummyapp.data.exceptions

import androidx.annotation.VisibleForTesting
import com.example.dummyapp.domain.exception.DomainException
import com.example.dummyapp.domain.exception.DomainNetworkException
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.SerializationException

fun <T : Any> Single<T>.throwDomainExceptionOnError(): Single<T> = doOnError { throw getDomainException(it) }

@VisibleForTesting
internal fun getDomainException(error: Throwable?): DomainException =
    when (error) {
        is SerializationException -> DomainNetworkException.InternalError(error.message ?: "SerializationException")
        is DomainExceptionConvertible -> error.toDomain()
        else -> DomainNetworkException.InternalError(error?.message ?: "DomainNetworkException")
    }