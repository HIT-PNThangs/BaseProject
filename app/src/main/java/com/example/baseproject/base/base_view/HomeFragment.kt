package com.example.baseproject.base.base_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.base.retrofit.TestViewModel
import com.example.baseproject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by lazy {
        TestViewModel()
    }

    override fun initView() {

    }

    override fun initData() {
        showLoading()
        viewModel.getData(onSuccess = {
            binding.tvHome.text = if (it.isNotEmpty()) it.toString() else "list empty"
            hideLoading()
        }, onFailed = {
            binding.tvHome.text = it
            hideLoading()
        })
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    override fun initListener() {

    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

}