package com.housesigma.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.housesigma.BaseModel
import com.housesigma.Room
import com.housesigma.network.NetworkUtil

class MapViewModel(val networkUtil: NetworkUtil):BaseModel(networkUtil) {
    private val mListData = MutableLiveData<List<Room>>()
    val listData: LiveData<List<Room>> = mListData
    val isHide = MutableLiveData<Boolean>()
    init {
        isHide.value=true
    }
    override fun load() {
        super.load()
        loadData() { it, result ->
            networkUtil.getData(0,0) {
                mListData.postValue(it)
            }
        }
    }
}