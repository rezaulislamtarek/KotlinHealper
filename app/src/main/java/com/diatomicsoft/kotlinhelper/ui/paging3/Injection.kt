package com.diatomicsoft.kotlinhelper.ui.paging3

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.diatomicsoft.kotlinhelper.model.networking.api.CategoriesApiInterface
import com.diatomicsoft.kotlinhelper.model.networking.healper.RetroFitInstance
import com.diatomicsoft.kotlinhelper.model.repo.PagingRepository
import com.diatomicsoft.kotlinhelper.model.source.CategoriesPagingSource

object Injection {
    fun provideCategoriesViewModel(context: Context): ViewModelProvider.Factory {

        val retrofit = RetroFitInstance.instance()

        val pagingSource =
            CategoriesPagingSource(
                retrofit.create(CategoriesApiInterface::class.java)
            )

        val repository =
            PagingRepository(
                pagingSource
            )

        return PagingViewModelFactory(
            repository
        )
    }
}