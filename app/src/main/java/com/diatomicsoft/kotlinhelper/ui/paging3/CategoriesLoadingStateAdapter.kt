package com.diatomicsoft.kotlinhelper.ui.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diatomicsoft.kotlinhelper.R
import com.diatomicsoft.kotlinhelper.databinding.ItemCategoryLoadingProgressBinding

class CategoriesLoadingStateAdapter(private val adapter: CategoriesAdapter) :
    LoadStateAdapter<CategoriesLoadingStateAdapter.LoadingStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder = LoadingStateViewHolder(
        ItemCategoryLoadingProgressBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_loading_progress, parent, false)
        )
    ) { adapter.retry() }

    class LoadingStateViewHolder(
        private val binding: ItemCategoryLoadingProgressBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                //errorMsg.text = (loadState as? LoadState.Error)?.error?.message

            }
        }
    }

}