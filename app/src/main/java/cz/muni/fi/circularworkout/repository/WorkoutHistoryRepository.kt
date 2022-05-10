package cz.muni.fi.circularworkout.repository

import android.content.Context
import cz.muni.fi.circularworkout.data.*
import cz.muni.fi.circularworkout.database.CWDatabase
import cz.muni.fi.circularworkout.database.WorkoutEntity
import cz.muni.fi.circularworkout.database.WorkoutHistoryEntityDao
import java.time.LocalDate
import java.time.MonthDay
import java.util.*


class WorkoutHistoryRepository(
    context: Context,
    private val workoutHistoryDao: WorkoutHistoryEntityDao = CWDatabase.create(context).workoutHistoryEntityDao()
) {
    fun getMockedData(count: Int = 5): List<WorkoutHistory> =
        mutableListOf<WorkoutHistory>().apply {
            repeat(count) {
                val item = WorkoutHistory(
                    name = it.toString(),
                    date = Date().toString()
                )
                add(item)
            }
        }

    fun getAll() : List<WorkoutHistory> =
        workoutHistoryDao.getAll()
            .map {
                it.toWorkoutHistory()
            }

    fun save(workout: WorkoutHistoryCreate) = workoutHistoryDao.save(workout.toWorkoutHistoryEntity(workoutId = workout.workoutId))

    fun delete(id: Long) = workoutHistoryDao.deleteById(id)

    fun getStatistics()= Statistics(123,10)


}