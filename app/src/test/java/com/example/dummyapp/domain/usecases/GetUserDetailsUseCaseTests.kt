package com.example.dummyapp.domain.usecases

import com.example.dummyapp.domain.repositories.UserRepository
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
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
class GetUserDetailsUseCaseTests {
    private val mockUserRepository: UserRepository = mock()
    private val useCase = GetUserDetailsUseCase(mockUserRepository)
    
    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockUserRepository)
    }
    
    @Test
    fun testInvoke() {
        val expectedUserId = "userId"
        val expectedDomainUserDetails = DomainFixtures.DomainUserDetailsUtils.create()
        whenever(mockUserRepository.getUserDetails(any())).thenReturn(Single.just(expectedDomainUserDetails))
        
        useCase.invoke(expectedUserId)
            .test()
            .assertValue(expectedDomainUserDetails)
        
        verify(mockUserRepository).getUserDetails(expectedUserId)
    }
}