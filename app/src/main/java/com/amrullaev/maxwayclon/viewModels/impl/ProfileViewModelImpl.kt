package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.directions.ProfileScreenDirection
import com.amrullaev.maxwayclon.domain.impl.ProfileUseCase
import com.amrullaev.maxwayclon.viewModels.ProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val direction: ProfileScreenDirection
) : ProfileViewModel, ViewModel() {
    override val nameFlow = MutableStateFlow("")

    override val phoneFlow = MutableStateFlow("")

    init {
       getData()
    }

    override fun editProfile() {
        viewModelScope.launch {
            direction.openEditProfile()
        }
    }

    override fun openBranches() {
        viewModelScope.launch {
            direction.openBranches()
        }
    }

    override fun openSettings() {
        viewModelScope.launch {
            direction.openSettings()
        }
    }

    override fun openServices() {
        viewModelScope.launch {
            direction.openServices()
        }
    }

    override fun getData() {
        viewModelScope.launch {
            nameFlow.emit(profileUseCase.getName())
            phoneFlow.emit(profileUseCase.getPhone())
        }
    }
}