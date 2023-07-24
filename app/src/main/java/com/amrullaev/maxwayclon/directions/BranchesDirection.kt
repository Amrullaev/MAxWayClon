package com.amrullaev.maxwayclon.directions

import com.amrullaev.maxwayclon.data.models.BranchData


interface BranchesDirection {

    suspend fun navigateToBranchesDetails(branchData: BranchData)
}