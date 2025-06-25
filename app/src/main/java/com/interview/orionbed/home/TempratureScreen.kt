package com.interview.orionbed.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.interview.orionbed.R
import com.interview.orionbed.ui.theme.OrionGradient

@Composable
fun TemperatureScreen(
    modifier: Modifier = Modifier,
    viewModel: TemperatureViewModel = viewModel()
) {
    val temperature by viewModel.temperature.collectAsState() // Needed for recomposition
    val displayedTemp = viewModel.getDisplayedTemperature()

    // Animate when temp changes
    val animatedTemp by animateIntAsState(
        targetValue = displayedTemp,
        label = "Temp"
    )

    val scaleAnim = remember { Animatable(1f) }
    LaunchedEffect(temperature) {
        scaleAnim.snapTo(1.05f)
        scaleAnim.animateTo(1f, animationSpec = tween(300))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.iconblack)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "Orion Logo",
                modifier = Modifier
                    .size(280.dp)
                    .scale(scaleAnim.value)
                    .align(Alignment.Center)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$animatedTemp°",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = viewModel.getUnitLabel(),
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.clickable { viewModel.toggleUnit() }
                )
            }


            Surface(
                onClick = { viewModel.increaseTemp() },
                shape = CircleShape,
                color = Color.Black,
                tonalElevation = 4.dp,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
                    .offset(x = 75.dp, y = -117.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Increase",
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Surface(
                onClick = { viewModel.decreaseTemp() },
                shape = CircleShape,
                color = Color.Black,
                tonalElevation = 4.dp,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
                    .offset(x = -75.dp, y = 117.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = "Decrease",
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
