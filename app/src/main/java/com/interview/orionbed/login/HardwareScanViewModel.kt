package com.interview.orionbed.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class HardwareScanViewModel @Inject constructor() : ViewModel() {
    val availableDevices = listOf("orion-001", "orion-002", "orion-003")

    private val _selectedDevice = MutableStateFlow<String?>(null)
    val selectedDevice: StateFlow<String?> = _selectedDevice

    fun selectDevice(id: String) {
        _selectedDevice.value = id
    }
}
