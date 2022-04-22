package cz.muni.fi.circularworkout.util

import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.MuscleGroup

fun MuscleGroup.toImageResource() = when(this) {
    MuscleGroup.BACK -> R.drawable.ic_back_male
    MuscleGroup.CHEST -> R.drawable.ic_chest_male
    MuscleGroup.LEGS -> R.drawable.ic_leg_male
}