package com.example.dummyapp.data.exceptions

import com.example.dummyapp.domain.exception.DomainException

/**
 * Interface used to implement toDomainExceptionType for Exceptions triggered from the Data
 */
interface DomainExceptionConvertible {
    fun toDomain(): DomainException
}