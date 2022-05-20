package cz.muni.fi.circularworkout.data

import java.time.LocalDateTime

data class WorkoutHistoryItem(
    val id: Long,
    val workoutName: String,
    val workoutDateTime: LocalDateTime
)