package com.amrullaev.maxwayclon.domain.impl

import com.amrullaev.maxwayclon.data.models.AdsData
import com.amrullaev.maxwayclon.data.models.dto.Category
import com.amrullaev.maxwayclon.data.models.dto.ProductDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    fun getAllAds(): Flow<ResultData<List<AdsData>>>

    fun getAllCategories(): Flow<ResultData<List<Category>>>

    fun getAllProducts(): Flow<ResultData<List<ProductDto>>>

    fun searchProducts(): Flow<ResultData<List<ProductDto>>>

}