package cz.muni.fi.circularworkout.database

import android.content.Context
import androidx.room.*

@Database(
    entities = [WorkoutEntity::class, WorkoutHistoryEntity::class],
    version = 2 ,
    autoMigrations = [
            AutoMigration (from = 1, to = 2)],
    exportSchema = false
)
@TypeConverters(Convertets::class)
abstract class CWDatabase : RoomDatabase() {

    companion object {
        private const val NAME = "cw.db"

        fun create(context: Context): CWDatabase =
            Room.databaseBuilder(context, CWDatabase::class.java, NAME)
                .allowMainThreadQueries()
                .build()
    }

    abstract fun workoutEntityDao(): WorkoutEntityDao
    abstract fun workoutHistoryEntityDao(): WorkoutHistoryEntityDao
}