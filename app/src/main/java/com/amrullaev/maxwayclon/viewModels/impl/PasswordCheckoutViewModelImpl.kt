package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.directions.PasswordCheckoutScreenDirection
import com.amrullaev.maxwayclon.domain.impl.ProfileUseCase
import com.amrullaev.maxwayclon.viewModels.PasswordCheckoutViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class PasswordCheckoutViewModelImpl @Inject constructor(
    private val direction: PasswordCheckoutScreenDirection,
    private val useCase: ProfileUseCase
) : PasswordCheckoutViewModel, ViewModel() {
    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val toastFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()

    override fun check(password: String, verifySms: String) {
        viewModelScope.launch {
            if (password == verifySms) {
                toastFlow.emit("Account crated")
                direction.navigateToMain()
                useCase.setToken("bir zat")
            } else {
                toastFlow.emit("Sms not verified")
            }
        }
    }

}