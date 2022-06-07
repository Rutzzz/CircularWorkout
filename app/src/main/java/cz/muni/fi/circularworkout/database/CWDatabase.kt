package cz.muni.fi.circularworkout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.muni.fi.circularworkout.database.converters.JsonConverter
import cz.muni.fi.circularworkout.database.converters.LocalDateTimeConverter


@Database(
    entities = [WorkoutEntity::class, WorkoutHistoryEntity::class],
    version = 5
)
@TypeConverters(JsonConverter::class, LocalDateTimeConverter::class)
abstract class CWDatabase : RoomDatabase() {

    companion object {
        private const val NAME = "cw.db"

        fun create(context: Context): CWDatabase =
            Room.databaseBuilder(context, CWDatabase::class.java, NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun workoutEntityDao(): WorkoutEntityDao
    abstract fun workoutHistoryEntityDao(): WorkoutHistoryEntityDao
}