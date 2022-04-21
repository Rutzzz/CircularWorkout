package cz.muni.fi.circularworkout.repository

import cz.muni.fi.circularworkout.data.WorkoutListItem
import java.time.LocalDate
import java.time.LocalTime

class WorkoutRepository {
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

}