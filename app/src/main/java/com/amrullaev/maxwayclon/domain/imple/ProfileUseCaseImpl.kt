package com.amrullaev.maxwayclon.domain.imple

import com.amrullaev.maxwayclon.domain.impl.ProfileUseCase
import com.amrullaev.maxwayclon.repository.MaxWayRepository
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val repository: MaxWayRepository
) : ProfileUseCase {
    override suspend fun getName(): String = repository.getName()

    override suspend fun setName(name: String) = repository.setName(name)

    override suspend fun getPhone(): String = repository.getPhone()

    override suspend fun setPhone(phone: String) = repository.setPhone(phone)

    override suspend fun getBirthday(): String = repository.getBirthday()

    override suspend fun setBirthday(birthday: String) = repository.setBirthday(birthday)

    override suspend fun getToken(): String = repository.getToken()

    override suspend fun setToken(token: String) = repository.setToken(token)
}