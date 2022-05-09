package cz.muni.fi.circularworkout.ui.statistics

import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutHistory

import cz.muni.fi.circularworkout.databinding.ItemHistoryListBinding


class HistoryViewHolder(private val binding: ItemHistoryListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: WorkoutHistory) {
        binding.workoutNameTextView.text = listItem.name
        binding.workoutDateTextView.text = "${listItem.date.day}. ${listItem.date.month}."



    }
}
