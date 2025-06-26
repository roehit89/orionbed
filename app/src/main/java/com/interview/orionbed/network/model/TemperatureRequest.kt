package com.interview.orionbed.network.model

data class TemperatureRequest(
    val value: Int,
    val timestamp: String,
    val deviceId: String
)
