package com.amrullaev.maxwayclon.domain.imple

import com.amrullaev.maxwayclon.data.models.BranchData
import com.amrullaev.maxwayclon.data.models.other.ResultData
import com.amrullaev.maxwayclon.domain.impl.BranchesUseCase
import com.amrullaev.maxwayclon.repository.MaxWayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BranchesUseCaseImpl @Inject constructor(private val repository: MaxWayRepository) :
    BranchesUseCase {
    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> = repository.getAllBranches()
}