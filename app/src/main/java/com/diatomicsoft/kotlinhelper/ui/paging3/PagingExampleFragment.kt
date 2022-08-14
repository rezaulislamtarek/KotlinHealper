package com.diatomicsoft.kotlinhelper.ui.paging3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.diatomicsoft.kotlinhelper.R
import com.diatomicsoft.kotlinhelper.base.BaseFragment
import com.diatomicsoft.kotlinhelper.databinding.FragmentPagingExampleBinding
import com.diatomicsoft.kotlinhelper.databinding.ItemSomethingWentWrongBinding
import kotlin.math.roundToInt

class PagingExampleFragment : BaseFragment<FragmentPagingExampleBinding, PagingExampleViewModel>() {
    private lateinit var adapter: CategoriesAdapter
    private lateinit var errorBinding: ItemSomethingWentWrongBinding
    override fun generateViewBinding(): FragmentPagingExampleBinding {
        return FragmentPagingExampleBinding.inflate(layoutInflater)
    }

    override fun generateViewModel(): PagingExampleViewModel {
        return ViewModelProvider(this, Injection.provideCategoriesViewModel(requireContext())).get(PagingExampleViewModel::class.java)
    }

    override fun init() {
        errorBinding = binding!!.somethingWentWrong

        //binding?.hello?.text = "Android"
        initAdapter()
        initListeners()
    }

    override fun initObserver() {
        super.initObserver()


        viewModel.getCategories().subscribe{
            adapter.submitData(lifecycle,it)
        }
    }

    private fun initAdapter() {


        adapter = CategoriesAdapter()

        binding?.rvPaging?.layoutManager = GridLayoutManager(requireContext(), 1)
        binding?.rvPaging?.adapter = adapter
        binding?.rvPaging?.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(16), true))
        binding?.rvPaging?.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CategoriesLoadingStateAdapter(adapter),
            footer = CategoriesLoadingStateAdapter(adapter)
        )

        //Handle network error before load data
        adapter.addLoadStateListener { loadState ->
            binding?.progress?.isVisible = loadState.refresh is LoadState.Loading
            manageErrors(loadState)
        }
    }

    private fun manageErrors(loadState: CombinedLoadStates) {
        errorBinding.root.isVisible = loadState.refresh is LoadState.Error

        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error

        errorState?.let {
            /* val errorText = resources.getString(R.string.error) + it.error.toString()
             binding.errorText.text = errorText*/
        }
    }


    private fun initListeners() {
        errorBinding.retry.setOnClickListener {
            adapter.retry()
        }
    }


    private fun dpToPx(dp: Int): Int {
        val r = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }


}