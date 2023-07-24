package com.amrullaev.maxwayclon.viewModels

import kotlinx.coroutines.flow.SharedFlow

interface PasswordCheckoutViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val toastFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    fun check(password: String, verifySms: String)

}