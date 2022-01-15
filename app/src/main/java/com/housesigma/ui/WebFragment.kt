package com.housesigma.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.housesigma.databinding.FragmentSettingBinding
import com.housesigma.databinding.FragmentWebBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebFragment() : BaseFragment() {
    private lateinit var mBinding: FragmentWebBinding
    private val mVm: SettingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentWebBinding.inflate(inflater)
        mBinding.lifecycleOwner = this
        mBinding.web.loadUrl("https://test.housesigma.com/static/dark_test.html")
        return mBinding.root
    }

}