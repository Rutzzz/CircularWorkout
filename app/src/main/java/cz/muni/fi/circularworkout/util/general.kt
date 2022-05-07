package cz.muni.fi.circularworkout.util

import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.WorkoutDetail
import cz.muni.fi.circularworkout.data.WorkoutInterval
import cz.muni.fi.circularworkout.data.WorkoutIntervalType

fun muscleGroupToImageResource(muscleGroup: String) = when(muscleGroup) {
    "BACK" -> R.drawable.ic_back_male
    "CHEST" -> R.drawable.ic_chest_male
    "LEGS" -> R.drawable.ic_leg_male
    "ABS" -> R.drawable.ic_leg_male
    else -> throw IllegalArgumentException("Unknown muscle group")
}

fun workoutToIntervals(workout: WorkoutDetail): List<WorkoutInterval> {
    val intervals = mutableListOf<WorkoutInterval>()
    repeat(workout.rounds) { round ->
        workout.exerciseNames.forEachIndexed{ i, exercise ->
            if (round != 0 || i != 0) {
                intervals.add(WorkoutInterval(
                    info = "Upcoming ($exercise)",
                    type = WorkoutIntervalType.REST,
                    duration = workout.restTime,
                    exerciseOrder = i + 1,
                    round = round + 1
                ))
            } else {
                intervals.add(WorkoutInterval(
                    info = "Upcoming ($exercise)",
                    type = WorkoutIntervalType.PREPARATION,
                    duration = 10,
                    exerciseOrder = i + 1,
                    round = round + 1
                ))
            }
            intervals.add(WorkoutInterval(
                info = exercise,
                type = WorkoutIntervalType.WORKOUT,
                duration = workout.exerciseTime,
                exerciseOrder = i + 1,
                round = round + 1
            ))
        }
    }
    return intervals
}

fun getTotalDuration(intervals: List<WorkoutInterval>) =
    intervals.sumOf { interval -> interval.duration }