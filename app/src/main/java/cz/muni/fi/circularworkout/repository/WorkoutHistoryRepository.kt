package cz.muni.fi.circularworkout.repository

import android.content.Context
import cz.muni.fi.circularworkout.data.WorkoutHistoryCreate
import cz.muni.fi.circularworkout.data.WorkoutHistoryItem
import cz.muni.fi.circularworkout.database.CWDatabase
import cz.muni.fi.circularworkout.database.WorkoutEntityDao
import cz.muni.fi.circularworkout.database.WorkoutHistoryEntityDao


class WorkoutHistoryRepository(
    context: Context,
    private val workoutDao: WorkoutEntityDao = CWDatabase.create(context).workoutEntityDao(),
    private val workoutHistoryDao: WorkoutHistoryEntityDao = CWDatabase.create(context).workoutHistoryEntityDao()
) {

    fun getAllItems() : List<WorkoutHistoryItem> =
        workoutHistoryDao.getAll().map {
            val workout = workoutDao.getById(it.workoutId)
            it.toWorkoutHistoryItem(workoutName = workout?.name)
        }.sortedByDescending { wh -> wh.workoutDateTime }

    fun save(workout: WorkoutHistoryCreate) = workoutHistoryDao.save(workout.toWorkoutHistoryEntity())

}