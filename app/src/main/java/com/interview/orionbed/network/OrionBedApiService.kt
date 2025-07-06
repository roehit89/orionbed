package com.interview.orionbed.network


import com.interview.orionbed.login.LoginResult
import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.network.model.LoginRequest
import com.interview.orionbed.network.model.TemperatureRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrionApiService {

    @GET("init")
    suspend fun getInitialData(): InitResponse

    @POST("temperature")
    suspend fun setTemperature(@Body request: TemperatureRequest): Response<Unit>

    @POST("auth")
    suspend fun authenticateUser(@Body request: LoginRequest): LoginResult

}
