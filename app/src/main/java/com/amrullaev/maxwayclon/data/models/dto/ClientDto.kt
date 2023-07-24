package com.amrullaev.maxwayclon.data.models.dto

import com.amrullaev.maxwayclon.data.models.Address

data class ClientDto(
    val fullName: String = "",
    val phoneNumber: String = "",
    val address: Address? = null
)
