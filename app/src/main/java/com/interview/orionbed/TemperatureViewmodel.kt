package com.interview.orionbed

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TemperatureViewModel : ViewModel() {

    // Internal mutable temperature state
    private val _temperature = MutableStateFlow(72) // Starting temperature
    val temperature: StateFlow<Int> = _temperature

    // Max and min bounds for temp control
    private val minTemp = 60
    private val maxTemp = 100

    fun increaseTemp() {
        if (_temperature.value < maxTemp) {
            _temperature.value += 1
        }
    }

    fun decreaseTemp() {
        if (_temperature.value > minTemp) {
            _temperature.value -= 1
        }
    }
}
