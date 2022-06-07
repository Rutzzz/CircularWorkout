package cz.muni.fi.circularworkout.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class JsonConverter {

    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}