package com.interview.orionbed.stats.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.orionbed.stats.StatEntry

@Composable
fun TempCard(entry: StatEntry) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFFFF3E0),
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(entry.title, fontSize = 14.sp, color = Color(0xFFEF6C00))
            Text(entry.value, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}
