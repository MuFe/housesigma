package com.housesigma.di



import com.housesigma.network.NetworkUtil
import org.koin.dsl.module

val commonModule = module {
    factory { NetworkUtil(get()) }
}
