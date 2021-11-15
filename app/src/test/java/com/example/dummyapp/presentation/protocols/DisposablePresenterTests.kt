package com.example.dummyapp.presentation.protocols

import com.example.dummyapp.domain.exception.DomainNetworkException
import com.example.dummyapp.presentation.protocols.disposeProvider.DisposablePresenter
import com.example.dummyapp.presentation.protocols.errorProtocol.NetworkErrorProtocol
import com.example.dummyapp.presentation.protocols.ui.Loadable
import com.example.dummyapp.utils.fixtures.exceptions.DomainExceptionsFixtures
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

interface DisposablePresenterTestView : Loadable, NetworkErrorProtocol

class DisposablePresenterTestPresenter : DisposablePresenter<DisposablePresenterTestView> {
    
    override var attachedUnsafeView: DisposablePresenterTestView? = null
    override val disposeBag = CompositeDisposable()
    
    fun callSingleSuccess(expectedString: String, success: (String) -> Unit) {
        Single.create<String> { it.onSuccess(expectedString) }
            .subscribeOnSuccess(view) { result -> success(result) }
    }
    
    fun callSingleError(exception: DomainNetworkException, success: (String) -> Unit) {
        Single.create<String> { it.onError(exception) }
            .subscribeOnSuccess(view) { result -> success(result) }
    }
}

@RunWith(MockitoJUnitRunner::class)
class DisposablePresenterTests {
    
    private val mockView: DisposablePresenterTestView = mock()
    private val testPresenter = DisposablePresenterTestPresenter()
    
    @Before
    fun setup() {
        testPresenter.attach(mockView, mock())
    }
    
    @Test
    fun testSingleSubscribeOnSuccessWithSuccess() {
        val expectedString = "message"
        testPresenter.callSingleSuccess(expectedString) { result ->
            MatcherAssert.assertThat(result, CoreMatchers.equalTo(expectedString))
        }
        verify(mockView).onHideLoading()
        verifyNoMoreInteractions(mockView)
    }
    
    @Test
    fun testSingleSubscribeOnSuccessWithFailure() {
        val expectedDomainException = DomainExceptionsFixtures.DomainNetworkUtils.createInternalError("message")
        testPresenter.callSingleError(expectedDomainException) {
            Assert.fail("Success callback shouldn't be called on error")
        }
        verify(mockView).onHideLoading()
        verify(mockView).onReceiveError(expectedDomainException)
        verifyNoMoreInteractions(mockView)
    }
}