package com.diatomicsoft.kotlinhelper.ui.paging3

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diatomicsoft.kotlinhelper.R
import com.diatomicsoft.kotlinhelper.databinding.ItemCategoryBinding
import com.diatomicsoft.kotlinhelper.model.data.Data

class CategoriesAdapter() :
    PagingDataAdapter<Data, CategoriesAdapter.ViewHolder>(
        COMPARATOR
    ) {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Data) {

            Glide.with(binding.root.context)
                .load("https://www.iquote.diatomicsoft.com${category.image}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_round_dangerous_24)
                .into(binding.imageView)

            binding.title.text = category.categoryName
            binding.total.text = "${category.wallpaperCount} Images"

            //binding.root.setOnClickListener { listener.onClick(category) }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnCategoryClickListener {
        fun onClick(category: Data)
    }


}