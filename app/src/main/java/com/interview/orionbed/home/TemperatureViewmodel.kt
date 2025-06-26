package com.interview.orionbed.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemperatureViewModel : ViewModel() {

    private val _temperature = MutableStateFlow(74)

    private val _targetTemperature = MutableStateFlow(74)
    val targetTemperature: StateFlow<Int> = _targetTemperature

    private val _isCelsius = MutableStateFlow(false)
    val isCelsius: StateFlow<Boolean> = _isCelsius

    private val _isWarmingOrCooling = MutableStateFlow(false)
    val isWarmingOrCooling: StateFlow<Boolean> = _isWarmingOrCooling

    fun increaseTemp() {
        _targetTemperature.value += 1
        simulateTemperatureTransition()
    }

    fun decreaseTemp() {
        _targetTemperature.value -= 1
        simulateTemperatureTransition()
    }

    fun toggleUnit() {
        _isCelsius.value = !_isCelsius.value
    }

    private fun simulateTemperatureTransition() {
        viewModelScope.launch {
            _isWarmingOrCooling.value = true
            var temp = _temperature.value
            val target = _targetTemperature.value

            while (temp != target) {
                delay(60)
                temp += if (temp < target) 1 else -1
                _temperature.value = temp
            }

            _isWarmingOrCooling.value = false
        }
    }
}
