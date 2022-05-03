package cz.muni.fi.circularworkout.util

import android.content.Context
import com.google.gson.Gson
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.Exercise

fun getExercises(context: Context): List<Exercise> {
    val inputStream = context.resources.openRawResource(R.raw.exercises)
    val txt = inputStream.bufferedReader().use { it.readText() }
    return Gson().fromJson(txt, Array<Exercise>::class.java).toList()
}

fun getExerciseOptions(exercises: List<Exercise>): Map<String, List<String>> {
    val m: MutableMap<String, MutableList<String>> = mutableMapOf()
    exercises.forEach {
        if (m.containsKey(it.category)) {
            m[it.category]?.add(it.name)
        } else {
            m[it.category] = mutableListOf(it.name)
        }
    }
    return m
}

fun getMuscleGroups(exercises: List<Exercise>) : List<String> =
    exercises.map { it.category }.distinct()


fun getExercisesForMuscleGroup(muscleGroup: String, exercises: List<Exercise>) : List<String> {
    val options = getExerciseOptions(exercises)
    return options[muscleGroup]!!
}
