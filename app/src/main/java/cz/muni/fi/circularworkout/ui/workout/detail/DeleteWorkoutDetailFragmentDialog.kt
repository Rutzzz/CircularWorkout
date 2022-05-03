package cz.muni.fi.circularworkout.ui.workout.detail

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import cz.muni.fi.circularworkout.R

class DeleteWorkoutDetailFragmentDialog (private val onNamePicked: (String) -> Unit) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val input = EditText(it)
            input.inputType = InputType.TYPE_CLASS_TEXT
            val builder = AlertDialog.Builder(it, R.style.AlertDialogCustom)
            builder
                .setTitle("Delete your workout")
                .setView(input)
                .setPositiveButton("Delete") { _, _ ->

                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
