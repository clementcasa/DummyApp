package com.example.dummyapp.presentation.modules.userdetails

import com.example.dummyapp.domain.usecases.GetUserDetailsUseCase
import com.example.dummyapp.domain.usecases.GetUserListUseCase
import com.example.dummyapp.presentation.modules.userlist.UserListPresenterImpl
import com.example.dummyapp.presentation.modules.userlist.UserListView
import com.example.dummyapp.utils.TestNetworkSchedulers
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import com.example.dummyapp.utils.fixtures.entities.PresenterFixtures
import io.reactivex.rxjava3.core.Single
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UserDetailsPresenterImplTests {
    private val mockView: UserDetailsView = mock()
    private val mokGetUserDetails: GetUserDetailsUseCase = mock()
    private val testNetworkSchedulers = TestNetworkSchedulers()
    private val presenter = UserDetailsPresenterImpl(mokGetUserDetails, testNetworkSchedulers)
    
    @Before
    fun setup() {
        presenter.attach(mockView, mock())
    }
    
    @After
    fun tearDown() {
        verifyNoMoreInteractions(mokGetUserDetails)
    }
    
    @Test
    fun testSetupWithData() {
        val expectedUserId = "userId"
        val expectedDomainUserDetails = DomainFixtures.DomainUserDetailsUtils.create()
        val expectedUIUserDetailsItem = PresenterFixtures.UIUserDetailsItemUtils.create()
        whenever(mokGetUserDetails.invoke(any())).thenReturn(Single.just(expectedDomainUserDetails))
        
        presenter.setupWithData(expectedUserId)
        
        verify(mokGetUserDetails).invoke(expectedUserId)
        verify(mockView).onShowLoading()
        verify(mockView).onHideLoading()
        verify(mockView).onReceiveUserDetails(expectedUIUserDetailsItem)
    }
}