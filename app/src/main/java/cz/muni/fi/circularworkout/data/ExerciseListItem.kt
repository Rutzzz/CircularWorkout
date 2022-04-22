package cz.muni.fi.circularworkout.data

data class ExerciseListItem(
    var muscleGroup: MuscleGroup,
    var selectedExercise: Long = 0
)