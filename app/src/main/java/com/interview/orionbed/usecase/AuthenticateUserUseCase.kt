package com.interview.orionbed.usecase

import com.interview.orionbed.login.LoginResult
import com.interview.orionbed.repository.OrionBedRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val repository: OrionBedRepository
) {
    suspend operator fun invoke(
        hardwareId: String,
        username: String,
        password: String
    ): LoginResult {
        return try {
            repository.authenticateUser(hardwareId, username, password)
        } catch (e: Exception) {
            LoginResult.ERROR
        }
    }
}
