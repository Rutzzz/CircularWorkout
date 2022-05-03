package cz.muni.fi.circularworkout.ui.workout.setup

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import cz.muni.fi.circularworkout.R

class AddExerciseDialogFragment(private val muscleGroups: List<String>,
                                private val onItemPicked: (String) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogCustom)
            builder
                .setTitle("Choose muscle group")
                .setItems(muscleGroups.toTypedArray()) { _, which ->
                    println("Picked " + muscleGroups[which])
                    onItemPicked(muscleGroups[which])
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}