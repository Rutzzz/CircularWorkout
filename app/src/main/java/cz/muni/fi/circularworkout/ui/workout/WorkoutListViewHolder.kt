package cz.muni.fi.circularworkout.ui.workout

import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.databinding.ItemWorkoutListBinding

class WorkoutListViewHolder(private val binding: ItemWorkoutListBinding)
    : RecyclerView.ViewHolder(binding.root) {

        fun bind(listItem: WorkoutListItem) {
            binding.workoutNameTextView.text = listItem.name
            binding.workoutDurationTextView.text = listItem.duration.minute.toString() + "m"
        }
}