package com.diatomicsoft.kotlinhelper.model.data


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("data")
    val categoryList: List<Data>,
    @SerializedName("message")
    val message: String
)