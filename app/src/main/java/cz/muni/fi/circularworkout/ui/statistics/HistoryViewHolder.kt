package cz.muni.fi.circularworkout.ui.statistics

import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutHistoryItem
import cz.muni.fi.circularworkout.databinding.ItemHistoryListBinding
import java.time.format.DateTimeFormatter


class HistoryViewHolder(private val binding: ItemHistoryListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: WorkoutHistoryItem) {
        binding.workoutNameTextView.text =
            if (listItem.workoutName.startsWith("W:")) "-" else listItem.workoutName
        binding.workoutDateTextView.text =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(listItem.workoutDateTime)
    }
}
