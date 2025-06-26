package com.interview.orionbed.usecase


import com.interview.orionbed.network.model.TemperatureRequest
import com.interview.orionbed.repository.OrionBedRepository
import javax.inject.Inject

class SetTemperatureUseCase @Inject constructor(
    private val repository: OrionBedRepository
) {
    suspend operator fun invoke(request: TemperatureRequest): Result<Unit> {
        return repository.setTemperature(request)
    }
}

