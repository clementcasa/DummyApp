package com.example.dummyapp.domain.usecases

import com.example.dummyapp.domain.models.DomainUserList
import com.example.dummyapp.domain.repositories.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(page: Int): Single<DomainUserList> =
        userRepository.getUserList(page = page, limit = 10)
}