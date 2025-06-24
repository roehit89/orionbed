package com.interview.orionbed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.interview.orionbed.ui.theme.OrionbedTheme

@Composable
fun TemperatureScreen(
    modifier: Modifier = Modifier,
    viewModel: TemperatureViewModel = viewModel()
) {
    val temperature by viewModel.temperature.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // SVG Orion logo in center
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.iconblack)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "Orion Logo",
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.Center)
        )

        // Temperature text in center
        Text(
            text = "$temperature°",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        // PLUS button (Surface)
        Surface(
            onClick = { viewModel.increaseTemp() },
            shape = CircleShape,
            color = Color.Black, // 🔁 Changed from theme color to black
            tonalElevation = 6.dp,
            shadowElevation = 8.dp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 75.dp, y = -117.dp)
                .size(48.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("+", fontSize = 20.sp, color = Color.White) // ✅ Use white text for contrast
            }
        }

// MINUS button (IconButton)
        IconButton(
            onClick = { viewModel.decreaseTemp() },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = -75.dp, y = 117.dp)
                .size(48.dp)
                .shadow(8.dp, shape = CircleShape)
                .background(Color.Black, CircleShape) // 🔁 Changed from theme color to black
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("-", fontSize = 20.sp, color = Color.White) // ✅ Use white text for contrast
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TemperatureScreenPreview() {
    // Wrap in your theme if you use one
    OrionbedTheme {
        TemperatureScreen()
    }
}

