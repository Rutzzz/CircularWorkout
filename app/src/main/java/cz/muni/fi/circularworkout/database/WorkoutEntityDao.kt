package cz.muni.fi.circularworkout.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(workout: WorkoutEntity)

    @Query("DELETE FROM WorkoutEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM WorkoutEntity")
    fun getAll(): List<WorkoutEntity>

}