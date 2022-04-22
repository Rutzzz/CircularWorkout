package cz.muni.fi.circularworkout.ui.workout.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.WorkoutListItem
import cz.muni.fi.circularworkout.databinding.ItemWorkoutListBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository

class WorkoutListAdapter(private val onItemClick: (WorkoutListItem) -> Unit) : RecyclerView.Adapter<WorkoutListViewHolder>() {

    private val workouts: List<WorkoutListItem> by lazy {
        workoutRepository.getMockedData()
    }

    private val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutListViewHolder {
        val binding = ItemWorkoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutListViewHolder, position: Int) {
        holder.bind(workouts[position], onItemClick)
    }

    override fun getItemCount(): Int = workouts.size
}