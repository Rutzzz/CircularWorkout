package cz.muni.fi.circularworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WorkoutEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val exerciseIds: List<Int>,
    val exerciseTime: Int,
    val restTime: Int,
    val rounds: Int
)