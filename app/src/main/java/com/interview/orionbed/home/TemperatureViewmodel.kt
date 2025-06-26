package com.interview.orionbed.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.orionbed.network.model.TemperatureRequest
import com.interview.orionbed.usecase.SetTemperatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val setTemperatureUseCase: SetTemperatureUseCase
) : ViewModel() {

    private val _targetTemperature = MutableStateFlow(74)
    val targetTemperature: StateFlow<Int> = _targetTemperature

    private val _isCelsius = MutableStateFlow(false)
    val isCelsius: StateFlow<Boolean> = _isCelsius

    private val _displayedTemperature = MutableStateFlow(74)
    val displayedTemperature: StateFlow<Int> = _displayedTemperature

    private val _isWarmingOrCooling = MutableStateFlow(false)
    val isWarmingOrCooling: StateFlow<Boolean> = _isWarmingOrCooling

    private var transitionJob: Job? = null
    private var debounceJob: Job? = null

    init {
        simulateTemperatureTransition()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitTemperature(value: Int) {
        val timestamp = Instant.now().toString()
        val deviceId = getDeviceId()

        val request = TemperatureRequest(
            value = value,
            timestamp = timestamp,
            deviceId = deviceId
        )

        viewModelScope.launch {
            setTemperatureUseCase(request)
        }
    }

    private fun debounceSubmit() {
        debounceJob?.cancel() // Cancel previous pending call
        debounceJob = viewModelScope.launch {
            delay(1500) // ⏱️ Wait for 1.5 seconds of inactivity
            submitTemperature(_targetTemperature.value)
        }
    }

    private fun getDeviceId(): String {
        return "orion-bed-001"
    }

    fun increaseTemp() {
        _targetTemperature.value += 1
        simulateTemperatureTransition()
        debounceSubmit()
    }

    fun decreaseTemp() {
        _targetTemperature.value -= 1
        simulateTemperatureTransition()
        debounceSubmit()
    }

    fun toggleUnit() {
        _isCelsius.value = !_isCelsius.value
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun simulateTemperatureTransition() {
        viewModelScope.launch {
            _isWarmingOrCooling.value = true
            var temp = _displayedTemperature.value
            val target = _targetTemperature.value

            if (temp == target) {
                _isWarmingOrCooling.value = false
                return@launch
            }

            delay(1000) // Delay before starting the animation

            while (temp != target) {
                delay(1000) // Smooth step
                temp += if (temp < target) 1 else -1
                _displayedTemperature.value = temp
            }

            _isWarmingOrCooling.value = false
        }
    }
}
