package com.amrullaev.maxwayclon.data.models


data class BaseResponse<T>(
    val successful: Boolean,
    val message: String? = "",
    val data: T,
    val status: Int = 200
)
