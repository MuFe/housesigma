package com.housesigma.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.housesigma.BaseModel
import com.housesigma.MainActivityViewModel
import com.housesigma.Room
import com.housesigma.misc.SingleLiveEvent
import com.housesigma.network.NetworkUtil

class ListViewModel(val networkUtil: NetworkUtil):BaseModel(networkUtil) {
    private val mListData = MutableLiveData<List<Room>>()
    val listData: LiveData<List<Room>> = mListData
    val isHavePhoto = MutableLiveData<Boolean>()
    private val mEvent = SingleLiveEvent<ViewModelEvent>()
    val event: LiveData<ViewModelEvent> = mEvent
    init {
        isHavePhoto.value=false
    }
    var index: Int = 1
    var size: Int = 10
    override fun load() {
        super.load()
        loadData() { it, result ->
            networkUtil.getData(index,size) {
                mListData.postValue(it)
                if (index == 1) {
                    mListData.postValue(it)
                } else {
                    mListData.postValue(mListData.value.orEmpty() + it)
                }
                mEvent.postValue(ViewModelEvent.EndEvent)
            }
        }
    }

    fun click(){
        if(isHavePhoto.value==true){
            isHavePhoto.value=false
        } else {
            isHavePhoto.value=true
        }
        mEvent.postValue(ViewModelEvent.ShowPhotoEvent)
    }
    fun refresh(){
        index=1
        load()
    }
    fun loadMore(){
        index++
        load()
    }
    sealed class ViewModelEvent {
        object  ShowPhotoEvent : ViewModelEvent()
        object  EndEvent : ViewModelEvent()
    }
}