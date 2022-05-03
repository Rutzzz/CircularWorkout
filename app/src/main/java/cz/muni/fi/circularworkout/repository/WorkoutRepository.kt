package cz.muni.fi.circularworkout.repository

import android.content.Context
import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.database.CWDatabase
import cz.muni.fi.circularworkout.database.WorkoutEntityDao
import java.time.LocalDate
import java.time.LocalTime

class WorkoutRepository(
    context: Context,
    private val workoutDao: WorkoutEntityDao = CWDatabase.create(context).workoutEntityDao()
) {
    fun getMockedData(count: Int = 5): List<WorkoutListItem> =
        mutableListOf<WorkoutListItem>().apply {
            repeat(count) {
                val item = WorkoutListItem(
                    id = it.toLong(),
                    name =
                    LocalDate.now().plusDays(it.toLong()).dayOfWeek.name
                        .lowercase().replaceFirstChar { c -> c.uppercaseChar() }
                            + " workout",
                    duration = LocalTime.of(0, 20 + it)
                )
                add(item)
            }
        }

    fun getAll() : List<WorkoutListItem> =
        workoutDao.getAll()
            .map {
                it.toWorkoutListItem()
            }

}