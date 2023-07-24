package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.SplashScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class SplashScreenDirectionsImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
    }
}