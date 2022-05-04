package cz.muni.fi.circularworkout.util

import cz.muni.fi.circularworkout.R
import java.lang.IllegalArgumentException

fun muscleGroupToImageResource(muscleGroup: String) = when(muscleGroup) {
    "BACK" -> R.drawable.ic_back_male
    "CHEST" -> R.drawable.ic_chest_male
    "LEGS" -> R.drawable.ic_leg_male
    "ABS" -> R.drawable.ic_leg_male
    else -> throw IllegalArgumentException("Unknown muscle group")
}