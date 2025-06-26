package com.interview.orionbed.repository


import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.network.model.TemperatureRequest

interface OrionBedRepository {
    suspend fun fetchInitialData(): InitResponse
    suspend fun setTemperature(request: TemperatureRequest): Result<Unit>
}
