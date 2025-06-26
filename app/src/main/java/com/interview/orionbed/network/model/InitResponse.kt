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
    @Json(name = "type") val rawType: String
) {
    val type: StatType
        get() = StatType.fromString(rawType)
}


@JsonClass(generateAdapter = true)
data class ScheduleItem(
    val title: String,
    val time: String,
    val icon: String
)


sealed class StatType(val rawValue: String) {
    object Sleep : StatType("Sleep")
    object Temperature : StatType("Temperature")
    object HeartRate : StatType("HeartRate")
    object Breathing : StatType("Breathing")
    data class Unknown(val actual: String) : StatType(actual)

    companion object {
        fun fromString(value: String): StatType = when (value) {
            "Sleep" -> Sleep
            "Temperature" -> Temperature
            "HeartRate" -> HeartRate
            "Breathing" -> Breathing
            else -> Unknown(value)
        }
    }
}