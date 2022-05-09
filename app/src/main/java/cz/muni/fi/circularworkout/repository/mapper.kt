package cz.muni.fi.circularworkout.repository

import cz.muni.fi.circularworkout.data.WorkoutCreate
import cz.muni.fi.circularworkout.data.WorkoutDetail
import cz.muni.fi.circularworkout.data.WorkoutHistory
import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.database.WorkoutEntity
import cz.muni.fi.circularworkout.database.WorkoutHistoryEntity
import java.time.LocalTime

fun getDuration(workout: WorkoutEntity): LocalTime {
    val minutes: Int = (workout.exercises.size * (workout.exerciseTime + workout.restTime) * workout.rounds) / 60
    return LocalTime.of(minutes / 60, minutes % 60)
}

fun WorkoutEntity.toWorkoutListItem() : WorkoutListItem = WorkoutListItem(
    id = this.id,
    name = this.name,
    duration = getDuration(this)
)

fun WorkoutCreate.toWorkoutEntity(isSaved: Boolean) : WorkoutEntity = WorkoutEntity(
    id = 0,
    name = this.name,
    exercises = this.exercises,
    exerciseTime = this.exerciseTime,
    restTime = this.restTime,
    rounds = this.rounds,
    isSaved = isSaved
)

fun WorkoutListItem.toWorkoutEntity() : WorkoutEntity = WorkoutEntity(
    id = 0,
    name = this.name,
    exerciseTime = this.duration.minute,
    exercises = ArrayList(0),
    restTime = 0,
    rounds = 0,
    isSaved = false
)

fun WorkoutEntity.toWorkoutDetail() : WorkoutDetail = WorkoutDetail(
    id = this.id,
    name = this.name,
    exerciseNames = this.exercises,
    exerciseTime = this.exerciseTime,
    restTime = this.restTime,
    rounds = this.rounds,
    isSaved = this.isSaved
)
