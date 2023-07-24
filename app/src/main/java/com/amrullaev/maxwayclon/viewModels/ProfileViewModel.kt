package com.amrullaev.maxwayclon.viewModels

import kotlinx.coroutines.flow.StateFlow

interface ProfileViewModel {

    val nameFlow: StateFlow<String>

    val phoneFlow: StateFlow<String>

    fun editProfile()

    fun openBranches()

    fun openSettings()

    fun openServices()

    fun getData()
}