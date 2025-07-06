package com.interview.orionbed.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.orionbed.usecase.AuthenticateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class LoginResult {
    PRIMARY_SETUP,
    SECONDARY_APPROVED,
    SECONDARY_PENDING, // TODO: Handle later
    SECONDARY_DENIED,   // TODO: Handle later
    ERROR
}

@HiltViewModel
class CredentialViewModel @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase
) : ViewModel() {

    fun submitCredentials(
        hardwareId: String,
        username: String,
        password: String,
        onContinue: (LoginResult) -> Unit
    ) {
        viewModelScope.launch {
            val result = authenticateUserUseCase(hardwareId, username, password)
            onContinue(result)
        }
    }
}

