package com.amrullaev.maxwayclon.repository.impl

import android.os.Build
import androidx.annotation.RequiresApi
import com.amrullaev.maxwayclon.data.models.AdsData
import com.amrullaev.maxwayclon.data.models.BranchData
import com.amrullaev.maxwayclon.data.models.ClientData
import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.data.models.dto.Category
import com.amrullaev.maxwayclon.data.models.dto.ClientDto
import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import com.amrullaev.maxwayclon.data.models.dto.ProductDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import com.amrullaev.maxwayclon.data.prefs.MySharedPref
import com.amrullaev.maxwayclon.data.remote.MaxWayApi
import com.amrullaev.maxwayclon.repository.MaxWayRepository
import com.amrullaev.maxwayclon.utils.exceptions.NoInternetConnection
import com.amrullaev.maxwayclon.utils.extensions.func
import com.amrullaev.maxwayclon.utils.extensions.hasConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MaxWayRepositoryImpl @Inject constructor(
    private val maxWayApi: MaxWayApi,
    private val mySharedPref: MySharedPref
) : MaxWayRepository {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun loginUser(clientDto: ClientDto): Flow<ResultData<ClientData>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.loginUSer(clientDto).func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun unRegisterUser(clientData: ClientData): Flow<ResultData<ClientData>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.unRegisterUser().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAllAds(): Flow<ResultData<List<AdsData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllAds().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAllCategories(): Flow<ResultData<List<Category>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllCategories().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAllProducts(): Flow<ResultData<List<ProductDto>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllProducts().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun searchProducts(): Flow<ResultData<List<ProductDto>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.searchProducts("data").func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getOrdersHistory(): Flow<ResultData<List<OrderData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getOrderHistory().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getActiveOrders(): Flow<ResultData<List<OrderData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getActiveOrders().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.orderProducts(orderDto).func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllBranches().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override suspend fun getName(): String = mySharedPref.name

    override suspend fun setName(name: String) {
        mySharedPref.name = name
    }

    override suspend fun getPhone(): String = mySharedPref.phone

    override suspend fun setPhone(phone: String) {
        mySharedPref.phone = phone
    }

    override suspend fun getBirthday(): String = mySharedPref.birthday

    override suspend fun setBirthday(birthday: String) {
        mySharedPref.birthday = birthday
    }

    override suspend fun getToken(): String = mySharedPref.token

    override suspend fun setToken(token: String) {
        mySharedPref.token = token
    }


}




