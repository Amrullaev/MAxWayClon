package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.ProfileScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class ProfileScreenDirectionsImpl @Inject constructor(
    private val appNavigator: Navigator
) : ProfileScreenDirection {

    override suspend fun openEditProfile() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToProfileDetails())
    }

    override suspend fun openBranches() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToBranchesScreen())
    }

    override suspend fun openSettings() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToSettingsScreen())
    }

    override suspend fun openServices() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToServiceScreen())
    }
}