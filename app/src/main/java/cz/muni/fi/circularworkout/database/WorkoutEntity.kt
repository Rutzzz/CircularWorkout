package cz.muni.fi.circularworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val exercises: List<String>,
    val exerciseTime: Int,
    val restTime: Int,
    val rounds: Int
)