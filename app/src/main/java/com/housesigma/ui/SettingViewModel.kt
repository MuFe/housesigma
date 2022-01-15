package com.housesigma.ui

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.housesigma.BaseModel
import com.housesigma.MainActivityViewModel
import com.housesigma.R
import com.housesigma.Room
import com.housesigma.misc.SingleLiveEvent
import com.housesigma.network.NetworkUtil

class SettingViewModel():BaseModel() {
    private val mListData = MutableLiveData<List<Room>>()
    val listData: LiveData<List<Room>> = mListData
    private val mEvent = SingleLiveEvent<ViewModelEvent>()
    val event: LiveData<ViewModelEvent> = mEvent

    fun Auto(){
        mEvent.postValue(ViewModelEvent.AutoEvent)
    }
    fun Day(){
        mEvent.postValue(ViewModelEvent.DayEvent)
    }
    fun NIght(){
        mEvent.postValue(ViewModelEvent.NightEvent)
    }
    fun Web(){
        mBaseEvent.postValue(BaseViewModelEvent.NavigateEvent(R.id.navigation_web, bundleOf()))
    }
    sealed class ViewModelEvent {
        object  AutoEvent : ViewModelEvent()
        object  DayEvent : ViewModelEvent()
        object  NightEvent : ViewModelEvent()
    }
}