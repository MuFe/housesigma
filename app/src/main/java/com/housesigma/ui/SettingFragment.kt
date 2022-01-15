package com.housesigma.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.housesigma.BaseModel
import com.housesigma.databinding.FragmentSettingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment() : BaseFragment() {
    private lateinit var mBinding: FragmentSettingBinding
    private val mVm: SettingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSettingBinding.inflate(inflater)
        mBinding.lifecycleOwner = this
        mBinding.vm = mVm
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVm.event.observe(viewLifecycleOwner, { event ->
            when (event) {
                SettingViewModel.ViewModelEvent.AutoEvent ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    requireActivity().recreate()
                }
                SettingViewModel.ViewModelEvent.DayEvent ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    requireActivity().recreate()
                }
                SettingViewModel.ViewModelEvent.NightEvent ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    requireActivity().recreate()
                }
            }

        })
    }

    override fun getBaseModel(): BaseModel? {
        return mVm
    }
}