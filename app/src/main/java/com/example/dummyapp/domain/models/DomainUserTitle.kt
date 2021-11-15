package com.example.dummyapp.domain.models

enum class DomainUserTitle {
    MR,
    MS,
    MRS,
    MISS,
    DR,
    NONE;
    
    companion object {
        operator fun invoke(value: String): DomainUserTitle =
            when(value) {
                "mr" -> MR
                "ms" -> MS
                "mrs" -> MRS
                "miss" -> MISS
                "dr" -> DR
                else -> NONE
            }
    }
}