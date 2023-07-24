package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.PasswordCheckoutScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class PasswordCheckoutScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : PasswordCheckoutScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(PasswordCheckoutScreenDirections.actionPasswordCheckoutScreenToMainScreen())
    }
}