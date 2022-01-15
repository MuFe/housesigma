package com.housesigma.network

import com.housesigma.BaseData
import com.housesigma.Room

import retrofit2.http.*

interface ApiService {
    @GET("/appApi/test")
    suspend fun data(): BaseData<List<Room>>


}