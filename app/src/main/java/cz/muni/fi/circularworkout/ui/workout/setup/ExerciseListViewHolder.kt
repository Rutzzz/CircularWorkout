package cz.muni.fi.circularworkout.ui.workout.setup

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.Exercise
import cz.muni.fi.circularworkout.data.ExerciseListItem
import cz.muni.fi.circularworkout.databinding.ItemExerciseListBinding
import cz.muni.fi.circularworkout.util.getExercisesForMuscleGroup
import cz.muni.fi.circularworkout.util.muscleGroupToImageResource

class ExerciseListViewHolder(private val exercises: List<Exercise>, private val binding: ItemExerciseListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: ExerciseListItem) {
        binding.muscleGroupTextView.text = listItem.muscleGroup
        val adapter =
            ArrayAdapter(binding.exerciseSpinner.context,
                android.R.layout.simple_spinner_item,
                getExercisesForMuscleGroup(listItem.muscleGroup, exercises))
        adapter.setDropDownViewResource(R.layout.simple_spinner_item)
        binding.exerciseSpinner.adapter = adapter
        binding.exerciseSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                listItem.selectedExercise = pos.toLong()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        val resource = muscleGroupToImageResource(listItem.muscleGroup)
        binding.muscleGroupImageView.setImageResource(resource)
    }
}