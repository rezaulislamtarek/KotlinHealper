package com.diatomicsoft.kotlinhelper.ui.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diatomicsoft.kotlinhelper.model.repo.PagingRepository

class PagingViewModelFactory (private val repository: PagingRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PagingExampleViewModel(repository) as T
    }
}