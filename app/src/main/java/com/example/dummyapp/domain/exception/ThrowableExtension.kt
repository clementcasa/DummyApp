package com.example.dummyapp.domain.exception

import io.reactivex.rxjava3.exceptions.CompositeException

fun Throwable.toDomainExceptionType(): DomainException? =
    when (this) {
        is CompositeException -> this.exceptions.last() as? DomainException
        else -> this as? DomainException
    }