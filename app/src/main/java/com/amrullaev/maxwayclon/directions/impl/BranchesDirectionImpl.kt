package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.data.models.BranchData
import com.amrullaev.maxwayclon.directions.BranchesDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class BranchesDirectionImpl @Inject constructor(private val navigator: Navigator) :
    BranchesDirection {
    override suspend fun navigateToBranchesDetails(branchData: BranchData) {
        navigator.navigateTo(
            BranchesScreenDirections.actionBranchesScreenToBranchDetailsScreen(
                branchData
            )
        )
    }
}