package com.diatomicsoft.kotlinhelper.ui.paging3

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.diatomicsoft.kotlinhelper.model.data.Data
import com.diatomicsoft.kotlinhelper.model.repo.PagingRepository
import io.reactivex.rxjava3.core.Flowable

class PagingExampleViewModel(private val repository: PagingRepository) : ViewModel() {
    fun getCategories(): Flowable<PagingData<Data>>{
        return repository.getCategories()
    }
}