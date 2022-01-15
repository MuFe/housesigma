package com.housesigma.adapter


import androidx.databinding.library.baseAdapters.BR
import com.housesigma.R
import com.housesigma.Room
import com.housesigma.databinding.AdapterMapBinding



class MapAdapter() : BaseBindingAdapter<Room, AdapterMapBinding>() {

    override fun getLayoutResId(position: Int): Int {
        return R.layout.adapter_map
    }



    override fun onBindItem(binding: AdapterMapBinding?, item: Room) {
        if (binding != null) {
            binding.setVariable(BR.data, item)
            binding.setVariable(BR.vm, this)
            binding.executePendingBindings()
        }
    }

}
