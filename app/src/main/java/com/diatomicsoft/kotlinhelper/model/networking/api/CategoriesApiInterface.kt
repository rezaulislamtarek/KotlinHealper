package com.diatomicsoft.kotlinhelper.model.networking.api

import com.diatomicsoft.kotlinhelper.model.data.Categories
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApiInterface {
    @GET("api/category/allobject/{page_no}/3")
    fun getCategories(@Path("page_no") page: Int): Single<Categories>
}