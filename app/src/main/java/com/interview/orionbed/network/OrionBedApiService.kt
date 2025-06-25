package com.interview.orionbed.network


import com.interview.orionbed.network.model.InitResponse
import retrofit2.http.GET

interface OrionApiService {

    @GET("init")
    suspend fun getInitialData(): InitResponse
}
