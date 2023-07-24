package com.amrullaev.maxwayclon.domain.impl

import com.amrullaev.maxwayclon.data.models.ClientData
import com.amrullaev.maxwayclon.data.models.dto.ClientDto
import com.amrullaev.maxwayclon.data.models.other.ResultData


interface LoginUseCase {

    suspend fun loginUser(clientDto: ClientDto): ResultData<ClientData>

    suspend fun logOutUser(clientData: ClientData): ResultData<ClientData>

}