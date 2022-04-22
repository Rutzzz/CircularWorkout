package cz.muni.fi.circularworkout.ui.workout.setup

import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.ExerciseListItem
import cz.muni.fi.circularworkout.databinding.ItemExerciseListBinding
import cz.muni.fi.circularworkout.util.toImageResource

class ExerciseListViewHolder(private val binding: ItemExerciseListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: ExerciseListItem) {
        binding.muscleGroupTextView.text = listItem.muscleGroup.text

        val exercises = listItem.muscleGroup.exercises
        val adapter =
            ArrayAdapter(binding.exerciseSpinner.context, android.R.layout.simple_spinner_item, exercises)
        adapter.setDropDownViewResource(R.layout.simple_spinner_item)
        binding.exerciseSpinner.adapter = adapter
        val resource = listItem.muscleGroup.toImageResource()
        binding.muscleGroupImageView.setImageResource(resource)
    }
}