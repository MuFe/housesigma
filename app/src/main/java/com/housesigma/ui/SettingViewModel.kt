package com.housesigma.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.housesigma.BaseModel
import com.housesigma.MainActivityViewModel
import com.housesigma.Room
import com.housesigma.misc.SingleLiveEvent
import com.housesigma.network.NetworkUtil

class SettingViewModel():BaseModel() {
    private val mListData = MutableLiveData<List<Room>>()
    val listData: LiveData<List<Room>> = mListData
    private val mEvent = SingleLiveEvent<ViewModelEvent>()
    val event: LiveData<ViewModelEvent> = mEvent

    sealed class ViewModelEvent {
        object  ShowPhotoEvent : ViewModelEvent()
        object  EndEvent : ViewModelEvent()
    }
}