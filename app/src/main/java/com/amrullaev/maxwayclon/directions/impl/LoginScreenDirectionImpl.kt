package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.LoginScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class LoginScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : LoginScreenDirection {
    override suspend fun navigateToPasswordChecker() {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToPasswordCheckoutScreen())
    }
}