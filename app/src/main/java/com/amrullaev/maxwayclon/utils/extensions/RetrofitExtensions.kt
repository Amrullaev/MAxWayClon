package com.amrullaev.maxwayclon.utils.extensions

import com.amrullaev.maxwayclon.data.models.BaseResponse
import com.amrullaev.maxwayclon.data.models.other.ResultData
import retrofit2.Response


fun <T> Response<BaseResponse<T>>.func(): ResultData<T> {
    val data = this
    if (data.isSuccessful) {
        if (data.body() != null) {
            val body = data.body()!!
            return if (body.successful) {
                ResultData.Success(body.data)
            } else {
                ResultData.Message(body.message!!)
            }
        }
    }
    return ResultData.Error(Throwable(data.errorBody()!!.string()))
}