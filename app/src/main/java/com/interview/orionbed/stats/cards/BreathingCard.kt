package com.interview.orionbed.stats.cards

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.interview.orionbed.network.model.StatEntry

@Composable
fun BreathingCard(entry: StatEntry) {
    StatCardTemplate(
        entry = entry,
        accentColor = Color(0xFF66BB6A),
        icon = Icons.Default.Air,
        visual = {
            BreathingWave()
        }
    )
}

@Composable
fun BreathingWave() {
    androidx.compose.foundation.Canvas(modifier = androidx.compose.ui.Modifier.size(48.dp)) {
        val height = size.height
        val width = size.width
        val path = androidx.compose.ui.graphics.Path().apply {
            moveTo(0f, height / 2)
            quadraticBezierTo(width * 0.25f, 0f, width * 0.5f, height / 2)
            quadraticBezierTo(width * 0.75f, height, width, height / 2)
        }
        drawPath(path, color = Color(0xFF66BB6A), style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3f))
    }
}
