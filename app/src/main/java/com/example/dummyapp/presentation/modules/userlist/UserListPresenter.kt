package com.example.dummyapp.presentation.modules.userlist

import com.example.dummyapp.domain.usecases.GetUserListUseCase
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import com.example.dummyapp.presentation.models.userPreview.toUIItem
import com.example.dummyapp.presentation.protocols.disposeProvider.DisposablePresenter
import com.example.dummyapp.presentation.protocols.errorProtocol.NetworkErrorProtocol
import com.example.dummyapp.presentation.protocols.ui.Loadable
import com.example.dummyapp.presentation.utils.NetworkSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.ArrayList
import javax.inject.Inject

interface UserListView: Loadable, NetworkErrorProtocol {
    fun onReceiveData(userList: List<UIUserPreviewItem>)
    fun onShowEmptyView()
    fun onShowLoadMoreButton()
    fun onHideLoadModeButton()
    fun onShowLoadMoreLoading()
    fun onHideLoadMoreLoading()
}

interface UserListPresenter: DisposablePresenter<UserListView> {
    fun refreshData()
    fun loadMoreItems()
}

class UserListPresenterImpl @Inject constructor(
    private val getUserList: GetUserListUseCase,
    private val networkSchedulers: NetworkSchedulers
): UserListPresenter {
    
    override var attachedUnsafeView: UserListView? = null
    override val disposeBag = CompositeDisposable()
    
    var userPreviewList: ArrayList<UIUserPreviewItem> = arrayListOf()
    var page = 0
    
    override fun refreshData() {
        page = 0
        view.onShowLoading()
        getUserList(page)
            .subscribeOn(networkSchedulers.io)
            .observeOn(networkSchedulers.main)
            .subscribeOnSuccess(view) { domainUserList ->
                if (domainUserList.total == 0) {
                    view.onShowEmptyView()
                    return@subscribeOnSuccess
                }
                domainUserList.data.map { it.toUIItem() }.let { userPreviewItems ->
                    userPreviewList = ArrayList(userPreviewItems)
                }
                view.onReceiveData(ArrayList(userPreviewList))
                when (userPreviewList.size < domainUserList.total) {
                    true -> view.onShowLoadMoreButton()
                    false -> view.onHideLoadModeButton()
                }
            }
    }
    
    override fun loadMoreItems() {
        view.onShowLoadMoreLoading()
        page += 1
        getUserList(page)
            .subscribeOn(networkSchedulers.io)
            .observeOn(networkSchedulers.main)
            .doOnEvent { _, _ -> view.onHideLoadMoreLoading() }
            .subscribe({ domainUserList ->
                domainUserList.data.map { it.toUIItem() }.let { userPreviewItems ->
                    userPreviewList.addAll(userPreviewItems)
                }
                view.onReceiveData(ArrayList(userPreviewList))
                when (userPreviewList.size < domainUserList.total) {
                    true -> view.onShowLoadMoreButton()
                    false -> view.onHideLoadModeButton()
                }
            }, {
                page -= 1
                view.onReceiveError(it)
            })
    }
}