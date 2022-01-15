package com.housesigma.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.housesigma.databinding.FragmentListBinding
import com.housesigma.databinding.FragmentMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment() : BaseFragment() {
    private lateinit var mBinding: FragmentListBinding
    private val mVm: ListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentListBinding.inflate(inflater)
        mBinding.lifecycleOwner = this
        mBinding.vm = mVm
        return mBinding.root
    }
}