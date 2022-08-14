package com.diatomicsoft.kotlinhelper.model.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val categoryName: String,
    @SerializedName("walpaper_count")
    val wallpaperCount: Int
)