package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.directions.SplashScreenDirection
import com.amrullaev.maxwayclon.domain.impl.ProfileUseCase
import com.amrullaev.maxwayclon.viewModels.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val direction: SplashScreenDirection
) : SplashViewModel, ViewModel() {

    override fun navigate() {
        viewModelScope.launch {
            delay(1500)
            if (profileUseCase.getToken().isEmpty()) {
                direction.navigateToLogin()
            } else {
                direction.navigateToMain()
            }
        }
    }
}