package cz.muni.fi.circularworkout.data

import java.time.LocalTime

data class WorkoutListItem(
    val id: Long,
    val name: String,
    val duration: LocalTime
)