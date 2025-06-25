package com.interview.orionbed.repository

import com.interview.orionbed.network.model.InitResponse


interface OrionBedRepository {
    suspend fun fetchInitialData(): InitResponse
}