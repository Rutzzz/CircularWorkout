package cz.muni.fi.circularworkout.repository

import android.content.Context
import cz.muni.fi.circularworkout.data.WorkoutCreate
import cz.muni.fi.circularworkout.data.WorkoutHistory
import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.database.CWDatabase
import cz.muni.fi.circularworkout.database.WorkoutEntityDao
import cz.muni.fi.circularworkout.database.WorkoutHistoryEntityDao
import java.time.LocalDate
import java.time.LocalTime

class WorkoutHistoryRepository(
    context: Context,
    private val workoutHistoryDao: WorkoutHistoryEntityDao = CWDatabase.create(context).workoutHistoryEntityDao()
) {

    fun getAll() : List<WorkoutListItem> =
        workoutHistoryDao.getAll()
            .map {
                it.toWorkoutListItem()
            }

    fun create(workout: WorkoutHistory) = workoutHistoryDao.save(workout.toWorkoutHistoryEntity())

    fun delete(id: Long) = workoutHistoryDao.deleteById(id)

}