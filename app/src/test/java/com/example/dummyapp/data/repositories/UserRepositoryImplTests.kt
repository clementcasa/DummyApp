package com.example.dummyapp.data.repositories

import androidx.room.rxjava3.EmptyResultSetException
import com.example.dummyapp.data.fixtures.DataFixtures
import com.example.dummyapp.data.fixtures.FixturesConstants
import com.example.dummyapp.data.providers.database.UserDao
import com.example.dummyapp.data.providers.network.api.UserServiceApi
import com.example.dummyapp.domain.exception.DomainNetworkException
import com.example.dummyapp.domain.exception.toDomainExceptionType
import com.example.dummyapp.utils.TestNetworkSchedulers
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import com.example.dummyapp.utils.fixtures.exceptions.DataExceptionsFixtures
import com.example.dummyapp.utils.fixtures.exceptions.DomainExceptionsFixtures
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTests {
    private val mockUserServiceApi: UserServiceApi = mock()
    private val mockUserDao: UserDao = mock()
    private val testNetworkSchedulers = TestNetworkSchedulers()
    private val repository = UserRepositoryImpl(mockUserServiceApi, mockUserDao, testNetworkSchedulers)
    
    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockUserServiceApi, mockUserDao)
    }
    
    @Test
    fun testGetUserListFromDatabaseSuccess() {
        val expectedPage = FixturesConstants.UserList.page
        val expectedLimit = FixturesConstants.UserList.limit
        val expectedUserEntityList = listOf(DataFixtures.UserEntityUtils.createFromPreview())
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create()
        whenever(mockUserDao.getAll(any())).thenReturn(Single.just(expectedUserEntityList))
        
        repository.getUserList(expectedPage, expectedLimit)
            .test()
            .assertValue(expectedDomainUserList)
        
        verify(mockUserDao).getAll(expectedPage)
    }
    
    @Test
    fun testGetUserListFromApiSuccess() {
        val expectedPage = FixturesConstants.UserList.page
        val expectedLimit = FixturesConstants.UserList.limit
        val expectedUserListResponse = DataFixtures.DataUserListResponseUtils.create()
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create()
        val expectedUserEntities = listOf(DataFixtures.UserEntityUtils.createFromPreview())
        whenever(mockUserDao.getAll(any())).thenReturn(Single.just(listOf()))
        whenever(mockUserServiceApi.getUserList(any(), any())).thenReturn(Single.just(expectedUserListResponse))
    
        repository.getUserList(expectedPage, expectedLimit)
                .test()
                .assertValue(expectedDomainUserList)
    
        verify(mockUserDao).getAll(expectedPage)
        verify(mockUserServiceApi).getUserList(expectedPage, expectedLimit)
        verify(mockUserDao).insert(expectedUserEntities)
    }
    
    @Test
    fun testGetUserListFromApiFailure() {
        val expectedPage = FixturesConstants.UserList.page
        val expectedLimit = FixturesConstants.UserList.limit
        val expectedDataException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(500)
        val expectedDomainException = DomainExceptionsFixtures.DomainNetworkUtils.createInternalError()
        whenever(mockUserDao.getAll(any())).thenReturn(Single.just(listOf()))
        whenever(mockUserServiceApi.getUserList(any(), any())).thenReturn(Single.error(expectedDataException))
    
        repository.getUserList(expectedPage, expectedLimit)
                .test()
                .assertError { it.toDomainExceptionType() as DomainNetworkException == expectedDomainException }
    
        verify(mockUserDao).getAll(expectedPage)
        verify(mockUserServiceApi).getUserList(expectedPage, expectedLimit)
    }
    
    @Test
    fun testGetUserDetailsFromDatabaseSuccess() {
        val expectedUserId = FixturesConstants.User.id
        val expectedUserEntityList = DataFixtures.UserEntityUtils.createFromDetails()
        val expectedDomainUserList = DomainFixtures.DomainUserDetailsUtils.create()
        whenever(mockUserDao.getById(any())).thenReturn(Single.just(expectedUserEntityList))
        
        repository.getUserDetails(expectedUserId)
                .test()
                .assertValue(expectedDomainUserList)
        
        verify(mockUserDao).getById(expectedUserId)
    }
    
    @Test
    fun testGetUserDetailsFromApiSuccess() {
        val expectedUserId = FixturesConstants.User.id
        val expectedUserDetailsResponse = DataFixtures.DataUserDetailsResponseUtils.create()
        val expectedDomainUserDetails = DomainFixtures.DomainUserDetailsUtils.create()
        whenever(mockUserDao.getById(any())).thenReturn(Single.error(EmptyResultSetException("")))
        whenever(mockUserServiceApi.getUserDetails(any())).thenReturn(Single.just(expectedUserDetailsResponse))
        
        repository.getUserDetails(expectedUserId)
                .test()
                .assertValue(expectedDomainUserDetails)
        
        verify(mockUserDao).getById(expectedUserId)
        verify(mockUserServiceApi).getUserDetails(expectedUserId)
        verify(mockUserDao).updateUser(
            userId = expectedUserDetailsResponse.id,
            gender = expectedUserDetailsResponse.gender,
            email = expectedUserDetailsResponse.email,
            dateOfBirth = expectedUserDetailsResponse.dateOfBirth,
            registerDate = expectedUserDetailsResponse.registerDate,
            phone = expectedUserDetailsResponse.phone,
            street = expectedUserDetailsResponse.location.street,
            city = expectedUserDetailsResponse.location.city,
            state = expectedUserDetailsResponse.location.state,
            country = expectedUserDetailsResponse.location.country
        )
    }
    
    @Test
    fun testGetUserDetailsFromApiFailure() {
        val expectedUserId = FixturesConstants.User.id
        val expectedDataException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(500)
        val expectedDomainException = DomainExceptionsFixtures.DomainNetworkUtils.createInternalError()
        whenever(mockUserDao.getById(any())).thenReturn(Single.error(EmptyResultSetException("")))
        whenever(mockUserServiceApi.getUserDetails(any())).thenReturn(Single.error(expectedDataException))
        
        repository.getUserDetails(expectedUserId)
                .test()
                .assertError { it.toDomainExceptionType() as DomainNetworkException == expectedDomainException }
        
        verify(mockUserDao).getById(expectedUserId)
        verify(mockUserServiceApi).getUserDetails(expectedUserId)
    }
}