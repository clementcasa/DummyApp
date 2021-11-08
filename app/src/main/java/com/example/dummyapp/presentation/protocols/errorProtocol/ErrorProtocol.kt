package com.example.dummyapp.presentation.protocols.errorProtocol

/**
 * Interface to implement callbacks when DomainException are triggered
 */
interface ErrorProtocol {
    fun onReceiveError(exception: Throwable) {}
}