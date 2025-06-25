package com.interview.orionbed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.orionbed.ui.theme.OrionGradient

@Composable
fun SettingsScreen() {
    val toggleItems = remember { mutableStateMapOf("Dark Mode" to true, "Sync with Google Fit" to true) }

    val settings = listOf(
        SettingItem("Sleep Reminder", Icons.Default.Notifications),
        SettingItem("Alarm Tone", Icons.Default.MusicNote),
        SettingItem("Smart Wake Window", Icons.Default.Timer),
        SettingItem("Vibration Feedback", Icons.Default.Vibration),
        SettingItem("Sleep Goals", Icons.Default.Flag),
        SettingItem("Dark Mode", Icons.Default.DarkMode, isToggle = true),
        SettingItem("Language", Icons.Default.Language),
        SettingItem("Sync with Google Fit", Icons.Default.FitnessCenter, isToggle = true),
        SettingItem("Export Sleep Data", Icons.Default.FileDownload),
        SettingItem("About App", Icons.Default.Info)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
        ) {
            settings.forEach { item ->
                SettingRow(
                    item = item,
                    toggleState = toggleItems[item.title] ?: false,
                    onToggleChange = { toggleItems[item.title] = it }
                )
                Divider(color = Color.White.copy(alpha = 0.1f), thickness = 0.5.dp)
            }
        }
    }
}

data class SettingItem(
    val title: String,
    val icon: ImageVector,
    val isToggle: Boolean = false
)

@Composable
fun SettingRow(
    item: SettingItem,
    toggleState: Boolean,
    onToggleChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(item.icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )
        if (item.isToggle) {
            Switch(
                checked = toggleState,
                onCheckedChange = onToggleChange
            )
        }
    }
}
