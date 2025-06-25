package com.interview.orionbed.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InitResponse(
    val currentTemperature: Int,
    val recommendedBedtime: String,
    val stats: List<StatEntry>,
    val schedules: List<ScheduleItem>
)

@JsonClass(generateAdapter = true)
data class StatEntry(
    val label: String,
    val value: String,
    val type: StatType
)

@JsonClass(generateAdapter = true)
data class ScheduleItem(
    val title: String,
    val time: String,
    val icon: String
)



enum class StatType {
    @Json(name = "Sleep")
    Sleep,

    @Json(name = "Temperature")
    Temperature,

    @Json(name = "HeartRate")
    HeartRate,

    @Json(name = "Breathing")
    Breathing
}
