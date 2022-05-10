package cz.muni.fi.circularworkout.data

import java.util.*


data class WorkoutHistoryDetails(
    val name : String,
    val date : String,
    val exercises: List<String>,
    val exerciseTime: Int,
    val rounds: Int,
)