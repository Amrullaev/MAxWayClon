package com.amrullaev.maxwayclon.repository

import com.amrullaev.maxwayclon.data.models.AdsData
import com.amrullaev.maxwayclon.data.models.BranchData
import com.amrullaev.maxwayclon.data.models.ClientData
import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.data.models.dto.Category
import com.amrullaev.maxwayclon.data.models.dto.ClientDto
import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import com.amrullaev.maxwayclon.data.models.dto.ProductDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import kotlinx.coroutines.flow.Flow


interface MaxWayRepository {

    fun loginUser(clientDto: ClientDto): Flow<ResultData<ClientData>>

    fun unRegisterUser(clientData: ClientData): Flow<ResultData<ClientData>>

    fun getAllAds(): Flow<ResultData<List<AdsData>>>

    fun getAllCategories(): Flow<ResultData<List<Category>>>

    fun getAllProducts(): Flow<ResultData<List<ProductDto>>>

    fun searchProducts(): Flow<ResultData<List<ProductDto>>>

    fun getOrdersHistory(): Flow<ResultData<List<OrderData>>>

    fun getActiveOrders(): Flow<ResultData<List<OrderData>>>

    fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>>

    fun getAllBranches(): Flow<ResultData<List<BranchData>>>

    suspend fun getName(): String

    suspend fun setName(name: String)

    suspend fun getPhone(): String

    suspend fun setPhone(phone: String)

    suspend fun getBirthday(): String

    suspend fun setBirthday(birthday: String)

    suspend fun getToken(): String

    suspend fun setToken(token: String)

}
