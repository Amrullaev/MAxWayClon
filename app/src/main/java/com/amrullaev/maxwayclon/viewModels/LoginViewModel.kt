package com.amrullaev.maxwayclon.viewModels

import kotlinx.coroutines.flow.SharedFlow

interface LoginViewModel {
    val loadingFlow: SharedFlow<Boolean>

    val toastFlow: SharedFlow<String>

    val messageFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    fun login(phone: String, name: String)

}