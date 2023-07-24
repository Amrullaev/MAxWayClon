package com.amrullaev.maxwayclon.domain.impl
interface ProfileUseCase {

    suspend fun getName(): String

    suspend fun setName(name: String)

    suspend fun getPhone(): String

    suspend fun setPhone(phone: String)

    suspend fun getBirthday(): String

    suspend fun setBirthday(birthday: String)

    suspend fun getToken(): String

    suspend fun setToken(token: String)

}