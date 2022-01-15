package com.housesigma


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.housesigma.misc.SingleLiveEvent


class MainActivityViewModel(
) : BaseModel() {
    val index = MutableLiveData<Int>()
    private val mEvent = SingleLiveEvent<ViewModelEvent>()
    val event: LiveData<ViewModelEvent> = mEvent
    override fun load() {
        super.load()


    }



    fun goMap() {
        mEvent.postValue(ViewModelEvent.GoEvent(R.id.navigation_map))
    }

    fun goList() {
        mEvent.postValue(ViewModelEvent.GoEvent(R.id.navigation_list))
    }


    fun goData() {

    }

    sealed class ViewModelEvent {
        data class  GoEvent(val id:Int) : ViewModelEvent()
    }
}