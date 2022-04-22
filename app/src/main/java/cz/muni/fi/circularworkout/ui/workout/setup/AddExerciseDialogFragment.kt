package cz.muni.fi.circularworkout.ui.workout.setup

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.MuscleGroup

class AddExerciseDialogFragment(private val onItemPicked: (MuscleGroup) -> Unit) : DialogFragment() {

    private val muscleGroups: Array<MuscleGroup> = MuscleGroup.values()

    private fun getMuscleGroupTexts() = muscleGroups.map{ it.text }.toTypedArray()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogCustom)
            builder
                .setTitle("Choose muscle group")
                .setItems(getMuscleGroupTexts()) { _, which ->
                    println("Picked " + muscleGroups[which].text)
                    onItemPicked(muscleGroups[which])
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}