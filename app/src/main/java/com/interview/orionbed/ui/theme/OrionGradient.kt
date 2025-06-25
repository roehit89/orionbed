package com.interview.orionbed.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val OrionGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFF0A0F28),  // dark navy blue (bottom-left)
        Color(0xFF1C3F8B),  // primary Orion blue (middle)
        Color(0xFFCADDFD)   // light blue / almost white (top-right)
    ),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)
