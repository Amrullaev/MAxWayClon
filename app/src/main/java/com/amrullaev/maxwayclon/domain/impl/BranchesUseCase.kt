package com.amrullaev.maxwayclon.domain.impl

import com.amrullaev.maxwayclon.data.models.BranchData
import com.amrullaev.maxwayclon.data.models.other.ResultData
import kotlinx.coroutines.flow.Flow

interface BranchesUseCase {

    fun getAllBranches(): Flow<ResultData<List<BranchData>>>

}