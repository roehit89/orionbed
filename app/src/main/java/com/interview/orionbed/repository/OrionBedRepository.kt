package com.interview.orionbed.repository


import com.interview.orionbed.login.LoginResult
import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.network.model.TemperatureRequest

interface OrionBedRepository {
    suspend fun fetchInitialData(): InitResponse
    suspend fun setTemperature(request: TemperatureRequest): Result<Unit>
    suspend fun authenticateUser(
        hardwareId: String,
        username: String,
        password: String
    ): LoginResult
}
