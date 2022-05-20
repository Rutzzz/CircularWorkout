package cz.muni.fi.circularworkout.repository

import cz.muni.fi.circularworkout.data.*
import cz.muni.fi.circularworkout.database.WorkoutEntity
import cz.muni.fi.circularworkout.database.WorkoutHistoryEntity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

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

fun WorkoutHistoryEntity.toWorkoutHistoryItem(workoutName: String) : WorkoutHistoryItem = WorkoutHistoryItem(
    id = this.id,
    workoutName = workoutName,
    workoutDateTime = this.date
)

fun WorkoutHistoryCreate.toWorkoutHistoryEntity() : WorkoutHistoryEntity = WorkoutHistoryEntity(
    id = 0,
    workoutId = workoutId,
    date = LocalDateTime.now()
)