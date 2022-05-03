package cz.muni.fi.circularworkout.data

data class WorkoutCreate(
    val name: String,
    val exercises: List<String>,
    val exerciseTime: Int,
    val restTime: Int,
    val rounds: Int
)