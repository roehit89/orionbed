package com.interview.orionbed.usecase


import android.util.Log
import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.repository.OrionBedRepository
import javax.inject.Inject

class LoadInitialDataUseCase @Inject constructor(
    private val repository: OrionBedRepository
) {
    suspend operator fun invoke(): InitResponse {
        return repository.fetchInitialData()
    }
}
