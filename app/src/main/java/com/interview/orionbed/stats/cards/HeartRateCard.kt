package com.interview.orionbed.stats.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.interview.orionbed.network.model.StatEntry

@Composable
fun HeartRateCard(entry: StatEntry) {
    StatCardTemplate(
        entry = entry,
        accentColor = Color(0xFFE57373),
        icon = Icons.Default.Favorite,
        visual = {
            HeartPulseGraph()
        }
    )
}

@Composable
fun HeartPulseGraph() {
    Canvas(modifier = Modifier.size(48.dp)) {
        val height = size.height
        val width = size.width
        val path = Path().apply {
            moveTo(0f, height / 2)
            lineTo(width * 0.2f, height / 2)
            lineTo(width * 0.3f, height * 0.1f)
            lineTo(width * 0.4f, height * 0.9f)
            lineTo(width * 0.5f, height * 0.5f)
            lineTo(width * 0.7f, height * 0.5f)
            lineTo(width * 0.8f, height * 0.2f)
            lineTo(width, height / 2)
        }
        drawPath(path, color = Color.Red, style = Stroke(width = 4f))
    }
}
