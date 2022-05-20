package cz.muni.fi.circularworkout.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(workout: WorkoutEntity)

    @Query("UPDATE WorkoutEntity SET isSaved = 0 WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM WorkoutEntity WHERE isSaved = 1")
    fun getAllSaved(): List<WorkoutEntity>

    @Query("SELECT * FROM WorkoutEntity")
    fun getAll(): List<WorkoutEntity>

    @Query("SELECT * FROM WorkoutEntity WHERE name = :name")
    fun getByName(name: String): WorkoutEntity?

    @Query("SELECT * FROM WorkoutEntity WHERE id = :id")
    fun getById(id: Long): WorkoutEntity?

}