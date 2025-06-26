package com.interview.orionbed.stats.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.interview.orionbed.network.model.StatEntry
import androidx.compose.ui.unit.dp

@Composable
fun SleepCard(entry: StatEntry) {
    StatCardTemplate(
        entry = entry,
        accentColor = Color(0xFF64B5F6),
        icon = Icons.Default.DarkMode,
        visual = {
            SleepStarsVisual()
        }
    )
}

@Composable
fun SleepStarsVisual() {
    Canvas(modifier = Modifier.size(40.dp)) {
        // Moon
        drawCircle(
            color = Color(0xFF90CAF9),
            radius = size.minDimension / 5,
            center = Offset(x = size.width * 0.3f, y = size.height * 0.4f)
        )
        // Star
        drawCircle(
            color = Color(0xFFBBDEFB),
            radius = size.minDimension / 10,
            center = Offset(x = size.width * 0.7f, y = size.height * 0.25f)
        )
        // Another star
        drawCircle(
            color = Color(0xFFBBDEFB),
            radius = size.minDimension / 12,
            center = Offset(x = size.width * 0.55f, y = size.height * 0.15f)
        )
    }
}
