package com.diatomicsoft.kotlinhelper.model.source

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.diatomicsoft.kotlinhelper.model.data.Categories
import com.diatomicsoft.kotlinhelper.model.data.Data
import com.diatomicsoft.kotlinhelper.model.networking.api.CategoriesApiInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoriesPagingSource(private val api: CategoriesApiInterface) : RxPagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Data>> {
        val position = params.key ?: 1
        return api.getCategories(position)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it.categoryList, position)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }
    private fun toLoadResult(data: List<Data>, position: Int): LoadResult<Int, Data> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (data.isEmpty()) null else position + 1
        )
    }

}