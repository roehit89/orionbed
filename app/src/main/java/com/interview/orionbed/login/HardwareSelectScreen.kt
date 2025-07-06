package com.interview.orionbed.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.orionbed.ui.theme.OrionGradient

@Composable
fun HardwareSelectScreen(
    viewModel: HardwareScanViewModel = hiltViewModel(),
    onDeviceSelected: (String) -> Unit
) {
    val devices = viewModel.availableDevices
    val selected by viewModel.selectedDevice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Available Devices",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 280.dp, top = 30.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(devices) { deviceId ->
                Text(
                    text = deviceId,
                    modifier = Modifier
                        .clickable {
                            viewModel.selectDevice(deviceId)
                            onDeviceSelected(deviceId)
                        }
                        .padding(12.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
