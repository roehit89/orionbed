package com.interview.orionbed.repository

import com.interview.orionbed.network.OrionApiService
import com.interview.orionbed.network.model.InitResponse
import javax.inject.Inject

class OrionBedRepositoryImpl @Inject constructor(private val service: OrionApiService) :
    OrionBedRepository {

    override suspend fun fetchInitialData(): InitResponse {
        return service.getInitialData()
    }
}