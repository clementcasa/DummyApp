package com.example.dummyapp.presentation.modules.userdetails

import com.example.dummyapp.domain.usecases.GetUserDetailsUseCase
import com.example.dummyapp.presentation.models.userDetails.UIUserDetailsItem
import com.example.dummyapp.presentation.models.userDetails.toUIItem
import com.example.dummyapp.presentation.protocols.disposeProvider.DisposablePresenter
import com.example.dummyapp.presentation.protocols.errorProtocol.NetworkErrorProtocol
import com.example.dummyapp.presentation.protocols.ui.Loadable
import com.example.dummyapp.presentation.utils.NetworkSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

interface UserDetailsView: Loadable, NetworkErrorProtocol {
    fun onReceiveUserDetails(item: UIUserDetailsItem)
}

interface UserDetailsPresenter: DisposablePresenter<UserDetailsView> {
    fun setupWithData(userId: String)
}

class UserDetailsPresenterImpl @Inject constructor(
    private val getUserDetails: GetUserDetailsUseCase,
    private val networkSchedulers: NetworkSchedulers
): UserDetailsPresenter {
    
    override var attachedUnsafeView: UserDetailsView? = null
    override val disposeBag = CompositeDisposable()
    
    override fun setupWithData(userId: String) {
        view.onShowLoading()
        getUserDetails(userId)
            .map { it. toUIItem() }
            .subscribeOn(networkSchedulers.io)
            .observeOn(networkSchedulers.main)
            .subscribeOnSuccess(view) { userDetailsItem ->
                view.onReceiveUserDetails(userDetailsItem)
            }
    }
}