package cz.muni.fi.circularworkout.data

data class WorkoutDetail(
    val name: String,
    val exerciseNames: List<String>,
    val exerciseTime: Int,
    val restTime: Int,
    val rounds: Int,
)