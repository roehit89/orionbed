package com.interview.orionbed.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.interview.orionbed.network.model.StatEntry
import com.interview.orionbed.network.model.StatType
import com.interview.orionbed.stats.cards.BreathingCard
import com.interview.orionbed.stats.cards.DefaultStatCard
import com.interview.orionbed.stats.cards.HeartRateCard
import com.interview.orionbed.stats.cards.SleepCard
import com.interview.orionbed.stats.cards.TempCard
import com.interview.orionbed.ui.theme.OrionGradient

@Composable
fun StatsScreen(stats: List<StatEntry>) {
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
                    is StatType.Unknown -> DefaultStatCard(stat)
                }
            }
        }
    }
}
