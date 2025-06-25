package com.interview.orionbed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.orionbed.cards.BreathingCard
import com.interview.orionbed.cards.DefaultStatCard
import com.interview.orionbed.cards.HeartRateCard
import com.interview.orionbed.cards.SleepCard
import com.interview.orionbed.cards.TempCard
import com.interview.orionbed.ui.theme.OrionGradient



@Composable
fun StatsScreen() {
    val stats = listOf(
        StatEntry("Sleep Score", "89", StatType.Sleep),
        StatEntry("Avg Temp", "70°F", StatType.Temperature),
        StatEntry("Heart Rate", "62 bpm", StatType.HeartRate),
        StatEntry("Breathing Rate", "15 bpm", StatType.Breathing),
        StatEntry("Sleep Score", "89", StatType.Sleep),
        StatEntry("Avg Temp", "70°F", StatType.Temperature),
        StatEntry("Heart Rate", "62 bpm", StatType.HeartRate),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(stats.size) { index ->
                val stat = stats[index]
                when (stat.type) {
                    is StatType.Sleep -> SleepCard(stat)
                    is StatType.Temperature -> TempCard(stat)
                    is StatType.HeartRate -> HeartRateCard(stat)
                    is StatType.Breathing -> BreathingCard(stat)
                    StatType.Unknown -> DefaultStatCard(stat)
                }
            }
        }
    }
}


@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 6.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Color(0xFF4A4A4A),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class StatEntry(
    val title: String,
    val value: String,
    val type: StatType
)

sealed class StatType(val key: String) {
    object Sleep : StatType("sleep")
    object Temperature : StatType("temperature")
    object HeartRate : StatType("heartRate")
    object Breathing : StatType("breathing")
    object Unknown : StatType("unknown")

    companion object {
        fun fromKey(key: String): StatType = when (key.lowercase()) {
            "sleep" -> Sleep
            "temperature" -> Temperature
            "heartrate" -> HeartRate
            "breathing" -> Breathing
            else -> Unknown
        }
    }
}



