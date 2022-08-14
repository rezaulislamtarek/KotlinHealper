package com.diatomicsoft.kotlinhelper.model.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.diatomicsoft.kotlinhelper.model.data.Data
import com.diatomicsoft.kotlinhelper.model.source.CategoriesPagingSource
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PagingRepository(private val source: CategoriesPagingSource) {

    @ExperimentalCoroutinesApi
    fun getCategories(): Flowable<PagingData<Data>>
    {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 10),
            pagingSourceFactory = { source }
        ).flowable
    }

}