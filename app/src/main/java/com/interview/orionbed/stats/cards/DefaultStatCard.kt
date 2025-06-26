package com.interview.orionbed.stats.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.interview.orionbed.network.model.StatEntry

@Composable
fun DefaultStatCard(entry: StatEntry) {
    StatCardTemplate(
        entry = entry,
        accentColor = Color.Gray,
        icon = null, // No icon for generic card
        visual = {
            NeutralBarGraph()
        }
    )
}

@Composable
fun NeutralBarGraph() {
    Row(
        modifier = Modifier
            .width(40.dp)
            .height(32.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        listOf(8, 16, 12, 20, 10).forEach { heightDp ->
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(heightDp.dp)
                    .background(Color.Gray, shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp))
            )
        }
    }
}
