package com.interview.orionbed.network


import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.network.model.TemperatureRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response

interface OrionApiService {

    @GET("init")
    suspend fun getInitialData(): InitResponse

    @POST("temperature")
    suspend fun setTemperature(@Body request: TemperatureRequest): Response<Unit>

}
