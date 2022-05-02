package cz.muni.fi.circularworkout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WorkoutEntity::class],
    version = 1
)
abstract class CWDatabase : RoomDatabase() {

    companion object {
        private const val NAME = "cw.db"

        fun create(context: Context): CWDatabase =
            Room.databaseBuilder(context, CWDatabase::class.java, NAME)
                .allowMainThreadQueries()
                .build()
    }

    abstract fun workoutEntityDao(): WorkoutEntityDao

}