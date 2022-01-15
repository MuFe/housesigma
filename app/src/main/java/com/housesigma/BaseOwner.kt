package com.housesigma




interface BaseOwner {
    fun getBaseModel():BaseModel?
    fun navigateUp()
    fun navigate(id:Int)
}
