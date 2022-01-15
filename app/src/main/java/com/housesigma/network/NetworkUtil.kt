package com.housesigma.network


import com.housesigma.BaseModel
import com.housesigma.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class NetworkUtil(val service: ApiService) {
    var viewModelScope: CoroutineScope? = null
    var viewModel: BaseModel? = null
    fun getData(page:Int,size:Int,listener: (List<Room>) -> Unit) {
        viewModelScope?.launch {
            val user = service.data(size,page)
            if (user.code == 200) {
                listener(user.data)
            } else {
                viewModel?.onError(Resource.error(user.message, user,user.code))
            }
        }
    }
}
