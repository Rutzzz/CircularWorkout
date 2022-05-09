package cz.muni.fi.circularworkout.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = WorkoutEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("workoutId"),
        onDelete = CASCADE)))
class WorkoutHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val historyId: Long,
    val workoutId: Long,
    @Embedded
    val workout : WorkoutEntity
)