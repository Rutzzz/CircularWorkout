package cz.muni.fi.circularworkout.data

data class ExerciseListItem(
    var muscleGroup: String,
    var selectedExercise: Long = 0
)