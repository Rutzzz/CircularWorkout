package cz.muni.fi.circularworkout.database;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutHistoryEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(workout: WorkoutHistoryEntity)

    @Query("DELETE FROM WorkoutHistoryEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM WorkoutHistoryEntity")
    fun getAll(): List<WorkoutHistoryEntity>

    @Query("SELECT * FROM WorkoutHistoryEntity WHERE id = :name")
    fun getById(name: String): WorkoutHistoryEntity?
}
