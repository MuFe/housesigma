package com.housesigma.adapter


import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.housesigma.R
import com.housesigma.Room
import com.housesigma.databinding.AdapterListBinding
import com.housesigma.databinding.AdapterMapBinding



class ListAdapter() : BaseBindingAdapter<Room, AdapterListBinding>() {
    val isHavePhoto = MutableLiveData<Boolean>()
    override fun getLayoutResId(position: Int): Int {
        return R.layout.adapter_list
    }

    override fun onBindItem(binding: AdapterListBinding?, item: Room) {
        if (binding != null) {
            binding.setVariable(BR.data, item)
            binding.setVariable(BR.vm, this)
            binding.executePendingBindings()
        }
    }


}
