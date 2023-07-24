package com.amrullaev.maxwayclon.data.remote

import com.amrullaev.maxwayclon.data.models.*
import com.amrullaev.maxwayclon.data.models.dto.Category
import com.amrullaev.maxwayclon.data.models.dto.ClientDto
import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import com.amrullaev.maxwayclon.data.models.dto.ProductDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MaxWayApi {
    @POST("register")
    suspend fun loginUSer(@Body clientDto: ClientDto): Response<BaseResponse<ClientData>>

    @POST("unregister")
    suspend fun unRegisterUser(): Response<BaseResponse<ClientData>>

    @GET("path")
    fun getAllAds(): Response<BaseResponse<List<AdsData>>>

    @GET("api/v1/category/all")
    suspend fun getAllCategories(): Response<BaseResponse<List<Category>>>

    @GET("api/v1/product/all")
    suspend fun getAllProducts(): Response<BaseResponse<List<ProductDto>>>

    @GET("path")
    suspend fun searchProducts(@Query("query") query: String): Response<BaseResponse<List<ProductDto>>>

    @GET("path")
    suspend fun getOrderHistory(): Response<BaseResponse<List<OrderData>>>

    @GET("path")
    suspend fun getActiveOrders(): Response<BaseResponse<List<OrderData>>>

    @POST("path")
    suspend fun orderProducts(@Body orderDto: OrderDto): Response<BaseResponse<OrderData>>

    @GET("path")
    suspend fun getAllBranches(): Response<BaseResponse<List<BranchData>>>
}