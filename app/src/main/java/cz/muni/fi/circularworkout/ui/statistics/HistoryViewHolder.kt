package cz.muni.fi.circularworkout.ui.statistics

import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutHistory
import cz.muni.fi.circularworkout.data.WorkoutHistoryDetails

import cz.muni.fi.circularworkout.databinding.ItemHistoryListBinding
import java.text.SimpleDateFormat


class HistoryViewHolder(private val binding: ItemHistoryListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: WorkoutHistoryDetails) {
        binding.workoutNameTextView.text = listItem.name
        binding.workoutDateTextView.text = listItem.date.toString()
    }
}
