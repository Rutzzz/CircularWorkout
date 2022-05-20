package cz.muni.fi.circularworkout.ui.statistics

import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutHistoryItem
import cz.muni.fi.circularworkout.databinding.ItemHistoryListBinding


class HistoryViewHolder(private val binding: ItemHistoryListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: WorkoutHistoryItem) {
        binding.workoutNameTextView.text = listItem.workoutName
        binding.workoutDateTextView.text = listItem.workoutDateTime.toString()
    }
}
