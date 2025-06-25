package com.interview.orionbed.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TemperatureViewModel : ViewModel() {

    private val _temperature = MutableStateFlow(72) // Fahrenheit
    val temperature: StateFlow<Int> = _temperature

    private val _isCelsius = mutableStateOf(false)
    val isCelsius: State<Boolean> = _isCelsius

    fun increaseTemp() {
        _temperature.value += 1
    }

    fun decreaseTemp() {
        _temperature.value -= 1
    }

    fun toggleUnit() {
        _isCelsius.value = !_isCelsius.value
    }

    fun getDisplayedTemperature(): Int {
        return if (_isCelsius.value) {
            ((_temperature.value - 32) * 5f / 9f).toInt()
        } else {
            _temperature.value
        }
    }

    fun getUnitLabel(): String {
        return if (_isCelsius.value) "Celsius" else "Fahrenheit"
    }
}
