package com.interview.orionbed.schedules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.interview.orionbed.ui.theme.OrionGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulesScreen(viewModel: SchedulesViewModel = viewModel()) {
    val showSheet = viewModel.isBottomSheetVisible.value
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val schedules = listOf(
        ScheduleItem("Next Alarm", "10:30 PM", Icons.Default.AccessTime),
        ScheduleItem("Ideal Sleep Time", "11:00 PM – 7:00 AM", Icons.Default.DarkMode),
        ScheduleItem("Wake Up Window", "6:45 AM – 7:15 AM", Icons.Default.CalendarToday),
        ScheduleItem("Weekday Schedule", "10:30 PM – 6:30 AM", Icons.Default.CalendarToday),
        ScheduleItem("Weekend Schedule", "11:30 PM – 8:00 AM", Icons.Default.CalendarToday)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Sleep Routine",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Based on your preferences",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(36.dp))

            schedules.forEach { item ->
                ScheduleRow(item)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        FloatingActionButton(
            onClick = { viewModel.showBottomSheet() },
            containerColor = Color.White,
            contentColor = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Schedule")
        }
    }

    if (showSheet) {
        ScheduleBottomSheet(
            onDismiss = { viewModel.hideBottomSheet() },
            sheetState = sheetState
        )
    }
}

@Composable
fun ScheduleRow(item: ScheduleItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = Color.White,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp)
        )
        Column {
            Text(
                text = item.label,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Text(
                text = item.value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class ScheduleItem(
    val label: String,
    val value: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSchedulesScreen() {
    SchedulesScreen()
}
