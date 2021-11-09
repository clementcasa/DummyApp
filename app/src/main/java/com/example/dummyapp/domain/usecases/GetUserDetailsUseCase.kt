package com.example.dummyapp.domain.usecases

import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.repositories.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Single<DomainUserDetails> =
        userRepository.getUserDetails(userId)
}