package cz.muni.fi.circularworkout.database;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.muni.fi.circularworkout.data.WorkoutHistoryDetails

@Dao
interface WorkoutHistoryEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(workout: WorkoutHistoryEntity)

    @Query("DELETE FROM WorkoutHistoryEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT WorkoutHistoryEntity.date as date, WorkoutEntity.name as name, WorkoutEntity.exercises as exercises, WorkoutEntity.exerciseTime as exerciseTime, WorkoutEntity.rounds as rounds " +
            " FROM WorkoutHistoryEntity INNER JOIN WorkoutEntity ON WorkoutHistoryEntity.workoutId=WorkoutEntity.id")
    fun getAll(): List<WorkoutHistoryDetails>

    @Query("SELECT * FROM WorkoutHistoryEntity WHERE id = :name")
    fun getById(name: String): WorkoutHistoryEntity?
}
