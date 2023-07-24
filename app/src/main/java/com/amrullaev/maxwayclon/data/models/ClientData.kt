package com.amrullaev.maxwayclon.data.models

data class ClientData(
    val phone: String,
    val name: String,
    val address: Address? = null
)
