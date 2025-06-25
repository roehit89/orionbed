package com.interview.orionbed.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemperatureViewModel : ViewModel() {

    private val _temperature = MutableStateFlow(74) // current (actual)
    val temperature: StateFlow<Int> = _temperature

    private val _targetTemperature = MutableStateFlow(74) // shown on screen
    val targetTemperature: StateFlow<Int> = _targetTemperature

    private val _isCelsius = MutableStateFlow(false)
    val isCelsius: StateFlow<Boolean> = _isCelsius

    private val _isWarmingOrCooling = MutableStateFlow(false)
    val isWarmingOrCooling: StateFlow<Boolean> = _isWarmingOrCooling

    private var simulationJob: Job? = null
    private var debounceJob: Job? = null

    fun increaseTemp() {
        _targetTemperature.value += 1
        restartDebounce()
    }

    fun decreaseTemp() {
        _targetTemperature.value -= 1
        restartDebounce()
    }

    fun toggleUnit() {
        _isCelsius.value = !_isCelsius.value
    }

    private fun restartDebounce() {
        debounceJob?.cancel()
        _isWarmingOrCooling.value = false

        debounceJob = viewModelScope.launch {
            delay(2000)
            _isWarmingOrCooling.value = true
            startSimulation()
        }
    }

    private fun startSimulation() {
        simulationJob?.cancel()
        simulationJob = viewModelScope.launch {
            while (_temperature.value != _targetTemperature.value) {
                delay(1000)
                val current = _temperature.value
                val target = _targetTemperature.value
                val step = if (target > current) 1 else -1
                _temperature.value += step
            }
            _isWarmingOrCooling.value = false
        }
    }
}
