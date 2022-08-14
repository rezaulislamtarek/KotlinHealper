package com.diatomicsoft.kotlinhelper.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<E: ViewBinding, V: ViewModel> : Fragment() {
    var binding: E? = null
    lateinit var viewModel: V
    private var mContext: Context? = null
    abstract fun generateViewBinding(): E
    abstract fun generateViewModel(): V
    abstract fun init()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = generateViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = generateViewBinding()
        init()
        initObserver()
        initListener()
        return binding?.root
    }
    open fun initObserver(){}
    open fun initListener(){}

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}