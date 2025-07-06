package com.interview.orionbed.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val hardwareId: String,
    val username: String,
    val password: String
)
