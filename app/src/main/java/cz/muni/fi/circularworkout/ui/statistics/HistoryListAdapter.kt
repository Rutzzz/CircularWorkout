package cz.muni.fi.circularworkout.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutHistory
import cz.muni.fi.circularworkout.databinding.ItemHistoryListBinding
import cz.muni.fi.circularworkout.repository.WorkoutHistoryRepository

class HistoryListAdapter (
    private val workoutHistoryRepository: WorkoutHistoryRepository,
): RecyclerView.Adapter<HistoryViewHolder>() {

    private val history: List<WorkoutHistory> by lazy {
        workoutHistoryRepository.getMockedData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(history[position])
    }


    override fun getItemCount(): Int = history.size


}


