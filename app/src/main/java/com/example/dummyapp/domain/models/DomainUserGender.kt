package com.example.dummyapp.domain.models

enum class DomainUserGender {
    MALE,
    FEMALE,
    OTHER,
    NONE;
    
    companion object {
        operator fun invoke(value: String): DomainUserGender =
            when(value) {
                "male" -> MALE
                "female" -> FEMALE
                "other" -> OTHER
                else -> NONE
            }
    }
}