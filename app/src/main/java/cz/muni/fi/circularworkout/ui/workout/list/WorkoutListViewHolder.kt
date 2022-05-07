package cz.muni.fi.circularworkout.ui.workout.list


import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.databinding.ItemWorkoutListBinding

class WorkoutListViewHolder(private val onStartClicked: (name: String) -> Unit, private val binding: ItemWorkoutListBinding)
    : RecyclerView.ViewHolder(binding.root) {

        fun bind(listItem: WorkoutListItem, onItemClick: (WorkoutListItem) -> Unit) {
            binding.workoutNameTextView.text = listItem.name
            val minutes = listItem.duration.minute + listItem.duration.hour * 60
            binding.workoutDurationTextView.text = minutes.toString() + "m"
            binding.root.setOnClickListener {
                onItemClick(listItem)
            }

            binding.workoutStartButton.setOnClickListener {
                onStartClicked(listItem.name)
            }
        }
}