package com.example.tadatest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    lateinit var viewModel: VM
    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB
    abstract fun getInjectViewModel(): VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = getInjectViewModel()
        binding = getViewBinding()
        return binding.root
    }
}