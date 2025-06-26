package com.interview.orionbed.stats.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.interview.orionbed.network.model.StatEntry

@Composable
fun TempCard(entry: StatEntry) {
    StatCardTemplate(
        entry = entry,
        accentColor = Color(0xFFFFB74D),
        icon = Icons.Default.Thermostat,
        visual = {
            ThermometerVisual()
        }
    )
}

@Composable
fun ThermometerVisual() {
    Canvas(modifier = Modifier.size(width = 24.dp, height = 48.dp)) {
        val width = size.width
        val height = size.height

        // Thermometer stem
        drawRoundRect(
            color = Color.Gray,
            topLeft = Offset(x = width / 3, y = 0f),
            size = androidx.compose.ui.geometry.Size(width / 3, height * 0.75f),
            cornerRadius = CornerRadius(4f)
        )

        // Thermometer bulb
        drawCircle(
            color = Color(0xFFFF8A65),
            radius = width / 2,
            center = Offset(x = width / 2, y = height * 0.9f)
        )
    }
}
