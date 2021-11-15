package com.example.dummyapp.data.repositories

import com.example.dummyapp.data.exceptions.throwDomainExceptionOnError
import com.example.dummyapp.data.models.database.toDomainUserList
import com.example.dummyapp.data.providers.database.UserDao
import com.example.dummyapp.data.providers.network.api.UserServiceApi
import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.models.DomainUserList
import com.example.dummyapp.domain.repositories.UserRepository
import com.example.dummyapp.presentation.utils.NetworkSchedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userServiceApi: UserServiceApi,
    private val userDao: UserDao,
    private val networkSchedulers: NetworkSchedulers
): UserRepository {
    
    override fun getUserList(page: Int, limit: Int): Single<DomainUserList> =
        userDao.getAll(page)
            .subscribeOn(networkSchedulers.io)
            .map { it.toDomainUserList(page, limit) }
            .flatMap { domainUserList ->
                if (domainUserList.data.isEmpty()) {
                    userServiceApi.getUserList(page, limit)
                        .doOnSuccess { userListResponse ->
                            val userEntities = userListResponse.data.map { it.toEntity(page) }
                            userDao.insert(userEntities)
                        }
                        .map { it.toDomain() }
                } else {
                    return@flatMap Single.just(domainUserList)
                }
            }.throwDomainExceptionOnError()
        
    
    override fun getUserDetails(userId: String): Single<DomainUserDetails> =
        userDao.getById(userId)
            .subscribeOn(networkSchedulers.io)
            .map { it.toDomainUserDetails() }
            .onErrorResumeNext {
                userServiceApi.getUserDetails(userId)
                    .doOnSuccess { userResponse ->
                        userDao.updateUser(
                            userId = userResponse.id,
                            gender = userResponse.gender,
                            email = userResponse.email,
                            dateOfBirth = userResponse.dateOfBirth,
                            registerDate = userResponse.registerDate,
                            phone = userResponse.phone,
                            street = userResponse.location.street,
                            city = userResponse.location.city,
                            state = userResponse.location.state,
                            country = userResponse.location.country
                        )
                    }
                    .map { it.toDomain() }
            }.throwDomainExceptionOnError()
}