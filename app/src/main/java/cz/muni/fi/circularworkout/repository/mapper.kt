package cz.muni.fi.circularworkout.repository

import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.database.WorkoutEntity
import java.time.LocalTime

fun getDuration(workout: WorkoutEntity): LocalTime {
    val minutes: Int = workout.exercises.size * (workout.exerciseTime + workout.restTime) * workout.rounds
    return LocalTime.of(minutes / 60, minutes % 60)
}

fun WorkoutEntity.toWorkoutListItem() : WorkoutListItem = WorkoutListItem(
    id = this.id,
    name = this.name,
    duration = getDuration(this)
)