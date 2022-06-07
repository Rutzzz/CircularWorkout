package cz.muni.fi.circularworkout.database.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun stringToLocalDateTime(value: String) : LocalDateTime = LocalDateTime.parse(value)

    @TypeConverter
    fun localDateTimeToString(ldt: LocalDateTime) : String = ldt.toString()

}