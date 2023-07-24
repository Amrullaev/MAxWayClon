package com.amrullaev.maxwayclon.domain.imple

import com.amrullaev.maxwayclon.data.models.AdsData
import com.amrullaev.maxwayclon.data.models.dto.Category
import com.amrullaev.maxwayclon.data.models.dto.ProductDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import com.amrullaev.maxwayclon.domain.impl.MainUseCase
import com.amrullaev.maxwayclon.repository.MaxWayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val maxWayRepository:MaxWayRepository
): MainUseCase {
    override fun getAllAds(): Flow<ResultData<List<AdsData>>> =
        maxWayRepository.getAllAds()

    override fun getAllCategories(): Flow<ResultData<List<Category>>> =
        maxWayRepository.getAllCategories()

    override fun getAllProducts(): Flow<ResultData<List<ProductDto>>> =
        maxWayRepository.getAllProducts()

    override fun searchProducts(): Flow<ResultData<List<ProductDto>>> =
        maxWayRepository.searchProducts()

}