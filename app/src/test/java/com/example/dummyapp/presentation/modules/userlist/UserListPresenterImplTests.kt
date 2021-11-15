package com.example.dummyapp.presentation.modules.userlist

import com.example.dummyapp.domain.usecases.GetUserListUseCase
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import com.example.dummyapp.utils.TestNetworkSchedulers
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import com.example.dummyapp.utils.fixtures.entities.PresenterFixtures
import io.reactivex.rxjava3.core.Single
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
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
class UserListPresenterImplTests {
    private val mockView: UserListView = mock()
    private val mockGetUserList: GetUserListUseCase = mock()
    private val testNetworkSchedulers = TestNetworkSchedulers()
    private val presenter = UserListPresenterImpl(mockGetUserList, testNetworkSchedulers)
    
    @Before
    fun setup() {
        presenter.attach(mockView, mock())
    }
    
    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockGetUserList)
    }
    
    @Test
    fun testRefreshDataWithEmptyData() {
        val expectedPage = 0
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create().copy(data = listOf(), total = 0)
        whenever(mockGetUserList.invoke(any())).thenReturn(Single.just(expectedDomainUserList))
        
        presenter.refreshData()
    
        assertThat(presenter.userPreviewList, equalTo(listOf()))
        verify(mockGetUserList).invoke(expectedPage)
        verify(mockView).onShowLoading()
        verify(mockView).onHideLoading()
        verify(mockView).onShowEmptyView()
    }
    
    @Test
    fun testRefreshDataWithLoadMoreItems() {
        val expectedPage = 0
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create()
        val expectedUIUserList = listOf(PresenterFixtures.UIUserPreviewItemUtils.create())
        whenever(mockGetUserList.invoke(any())).thenReturn(Single.just(expectedDomainUserList))
        
        presenter.refreshData()
    
        assertThat(presenter.userPreviewList, equalTo(expectedUIUserList))
        verify(mockGetUserList).invoke(expectedPage)
        verify(mockView).onShowLoading()
        verify(mockView).onHideLoading()
        verify(mockView).onReceiveData(expectedUIUserList)
        verify(mockView).onShowLoadMoreButton()
    }
    
    @Test
    fun testRefreshDataWithoutLoadMoreItems() {
        val expectedPage = 0
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create().copy(total = 1)
        val expectedUIUserList = listOf(PresenterFixtures.UIUserPreviewItemUtils.create())
        whenever(mockGetUserList.invoke(any())).thenReturn(Single.just(expectedDomainUserList))
        
        presenter.refreshData()
    
        assertThat(presenter.userPreviewList, equalTo(expectedUIUserList))
        verify(mockGetUserList).invoke(expectedPage)
        verify(mockView).onShowLoading()
        verify(mockView).onHideLoading()
        verify(mockView).onReceiveData(expectedUIUserList)
    }
    
    @Test
    fun testLoadMoreItemsWithEmptyData() {
        val expectedNewPage = 1
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create().copy(data = listOf(), total = 0)
        whenever(mockGetUserList.invoke(any())).thenReturn(Single.just(expectedDomainUserList))
        
        presenter.loadMoreItems()
        
        assertThat(presenter.userPreviewList, equalTo(listOf()))
        assertThat(presenter.page, equalTo(expectedNewPage))
        verify(mockGetUserList).invoke(expectedNewPage)
        verify(mockView).onShowLoadMoreLoading()
        verify(mockView).onHideLoadMoreLoading()
    }
    
    @Test
    fun testLoadMoreItemsWithLoadMoreItems() {
        val expectedNewPage = 1
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create()
        val expectedUIUserList = listOf(PresenterFixtures.UIUserPreviewItemUtils.create())
        whenever(mockGetUserList.invoke(any())).thenReturn(Single.just(expectedDomainUserList))
        
        presenter.loadMoreItems()
    
        assertThat(presenter.page, equalTo(expectedNewPage))
        assertThat(presenter.userPreviewList, equalTo(expectedUIUserList))
        verify(mockGetUserList).invoke(expectedNewPage)
        verify(mockView).onShowLoadMoreLoading()
        verify(mockView).onHideLoadMoreLoading()
        verify(mockView).onReceiveData(expectedUIUserList)
        verify(mockView).onShowLoadMoreButton()
    }
    
    @Test
    fun testLoadMoreItemsWithoutLoadMoreItems() {
        val expectedNewPage = 1
        val expectedDomainUserList = DomainFixtures.DomainUserListUtils.create().copy(total = 1)
        val expectedUIUserList = listOf(PresenterFixtures.UIUserPreviewItemUtils.create())
        whenever(mockGetUserList.invoke(any())).thenReturn(Single.just(expectedDomainUserList))
        
        presenter.loadMoreItems()
    
        assertThat(presenter.page, equalTo(expectedNewPage))
        assertThat(presenter.userPreviewList, equalTo(expectedUIUserList))
        verify(mockGetUserList).invoke(expectedNewPage)
        verify(mockView).onShowLoadMoreLoading()
        verify(mockView).onHideLoadMoreLoading()
        verify(mockView).onReceiveData(expectedUIUserList)
    }
}