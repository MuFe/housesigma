package com.housesigma.network

import com.housesigma.BaseData
import com.housesigma.Room

import retrofit2.http.*

interface ApiService {
    @GET("/appApi/test")
    suspend fun data(@Query("size") size:Int,@Query("page") page:Int,): BaseData<List<Room>>


}