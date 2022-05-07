package cz.muni.fi.circularworkout.data

data class WorkoutInterval(
    val info: String,
    val type: WorkoutIntervalType,
    val duration: Int,
    val exerciseOrder: Int,
    val round: Int,
)