package com.interview.orionbed.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemperatureViewModel : ViewModel() {

    private val _targetTemperature = MutableStateFlow(74)
    val targetTemperature: StateFlow<Int> = _targetTemperature

    private val _isCelsius = MutableStateFlow(false)
    val isCelsius: StateFlow<Boolean> = _isCelsius

    private val _displayedTemperature = MutableStateFlow(74)
    val displayedTemperature: StateFlow<Int> = _displayedTemperature

    private val _isWarmingOrCooling = MutableStateFlow(false)
    val isWarmingOrCooling: StateFlow<Boolean> = _isWarmingOrCooling

    init {
        simulateTemperatureTransition()
    }

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
