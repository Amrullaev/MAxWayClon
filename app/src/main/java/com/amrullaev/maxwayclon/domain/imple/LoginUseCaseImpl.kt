package com.amrullaev.maxwayclon.domain.imple

import com.amrullaev.maxwayclon.data.models.ClientData
import com.amrullaev.maxwayclon.data.models.dto.ClientDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import com.amrullaev.maxwayclon.domain.impl.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override suspend fun loginUser(clientDto: ClientDto): ResultData<ClientData> {
        return ResultData.Success(
            ClientData(
                clientDto.phoneNumber,
                clientDto.fullName,
                null
            )
        )
    }

    override suspend fun logOutUser(clientData: ClientData): ResultData<ClientData> {
        TODO("Not yet implemented")
    }
}