package com.example.baseproject.base.base_view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), LifecycleObserver {
    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateViewBinding(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        lifecycle.addObserver(this)
        initView()
        initData()
        initListener()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()
    abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onDestroy() {
        _binding = null
        lifecycle.removeObserver(this)
        super.onDestroy()
    }
}