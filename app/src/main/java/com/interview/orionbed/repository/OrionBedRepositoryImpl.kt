package com.interview.orionbed.repository

import com.interview.orionbed.login.LoginResult
import com.interview.orionbed.network.OrionApiService
import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.network.model.LoginRequest
import com.interview.orionbed.network.model.TemperatureRequest
import javax.inject.Inject

class OrionBedRepositoryImpl @Inject constructor(
    private val service: OrionApiService
) : OrionBedRepository {

    override suspend fun fetchInitialData(): InitResponse {
        return service.getInitialData()
    }

    override suspend fun setTemperature(request: TemperatureRequest): Result<Unit> {
        return try {
            val response = service.setTemperature(request)
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Error: ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun authenticateUser(
        hardwareId: String,
        username: String,
        password: String
    ): LoginResult {
        val request = LoginRequest(hardwareId, username, password)
        return try {
            service.authenticateUser(request)
        } catch (e: Exception) {
            LoginResult.ERROR
        }
    }
}

