package cz.muni.fi.circularworkout.repository

import android.content.Context
import cz.muni.fi.circularworkout.data.Statistics
import cz.muni.fi.circularworkout.database.CWDatabase
import cz.muni.fi.circularworkout.database.WorkoutEntityDao
import cz.muni.fi.circularworkout.database.WorkoutHistoryEntityDao

class StatisticsRepository(
    context: Context,
    private val workoutDao: WorkoutEntityDao = CWDatabase.create(context).workoutEntityDao(),
    private val workoutHistoryDao: WorkoutHistoryEntityDao = CWDatabase.create(context).workoutHistoryEntityDao()
) {

    fun getStatistics(): Statistics {
        val history = workoutHistoryDao.getAll()
        val workouts = workoutDao.getAll()
        return Statistics(
            totalSeconds = history
                .map { h -> workouts.first { w -> w.id == h.workoutId } }
                .sumOf { w -> w.rounds * ( w.exerciseTime + w.restTime ) * w.exercises.size },
            totalWorkouts = history.size
        )
    }
}