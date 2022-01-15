package com.housesigma.di


import com.housesigma.MainActivityViewModel
import com.housesigma.ui.ListViewModel
import com.housesigma.ui.MapViewModel
import com.housesigma.ui.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MapViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { SettingViewModel() }
    viewModel { MainActivityViewModel() }
}

