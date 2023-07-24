package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.dto.ClientDto
import com.amrullaev.maxwayclon.directions.LoginScreenDirection
import com.amrullaev.maxwayclon.domain.impl.LoginUseCase
import com.amrullaev.maxwayclon.domain.impl.ProfileUseCase
import com.amrullaev.maxwayclon.viewModels.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val direction: LoginScreenDirection,
    private val profileUseCase: ProfileUseCase
) : LoginViewModel, ViewModel() {

    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val toastFlow = MutableSharedFlow<String>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()

    override fun login(phone: String, name: String) {
        viewModelScope.launch {
            if (phone.length < 17) {
                toastFlow.emit("Phone number input incorrect")
                cancel()
            }
            if (name.isEmpty()) {
                toastFlow.emit("Name is required")
                cancel()
            }
            loginUseCase.loginUser(ClientDto(phone, name))
                .onSuccess {
                    profileUseCase.setName(it.name)
                    profileUseCase.setPhone(it.phone)
                    direction.navigateToPasswordChecker()
                }
        }
    }
}