package cz.muni.fi.circularworkout.database

import androidx.room.*

@Dao
interface WorkoutHistoryEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(workout: WorkoutHistoryEntity)

    @Transaction
    @Query("SELECT * FROM WorkoutHistoryEntity")
    fun getAll(): List<WorkoutHistoryEntity>

}
