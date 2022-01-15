package com.housesigma

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.housesigma.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mVm: MainActivityViewModel by viewModel()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mBinding.lifecycleOwner = this
        mBinding.vm = mVm
        mVm.index.value = 0
        setContentView(mBinding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        navGraph.startDestination = R.id.navigation_map
        navController.graph = navGraph
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.label) {
                getString(R.string.title_map) -> {
                    mVm.index.postValue(0)
                }
                getString(R.string.title_list) -> {
                    mVm.index.postValue(1)
                }
                getString(R.string.title_setting) -> {
                    mVm.index.postValue(2)
                }

            }
        }
        mVm.event.observe(this, { event ->
            when (event) {
                is MainActivityViewModel.ViewModelEvent.GoEvent->{
                    goOther(event.id)
                }
            }
        })
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//        recreate()
    }

    fun goOther(id: Int, bundle: Bundle = bundleOf()) {
        val navDestination = navController.graph.findNode(id)
        if (navDestination != null) {
            if (navDestination.id == navController.currentDestination!!.id) {
                return
            }
        }
        navController.navigate(id, bundle)
    }


}