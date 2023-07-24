package com.amrullaev.maxwayclon.data.models.dto

import com.amrullaev.maxwayclon.data.models.CategoryData

data class Category(
    val createdDate: String,
    val id: Long,
    val information: String,
    val name: String
){
    fun toCategoryData() = CategoryData(id, name, information, createdDate)
}