package cz.muni.fi.circularworkout.ui.workout.setup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.ExerciseListItem
import cz.muni.fi.circularworkout.data.MuscleGroup
import cz.muni.fi.circularworkout.databinding.ItemExerciseListBinding
import java.util.*

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListViewHolder>() {

    private val exercises: MutableList<ExerciseListItem> by lazy {
        mutableListOf(
            ExerciseListItem(MuscleGroup.CHEST),
            ExerciseListItem(MuscleGroup.BACK),
            ExerciseListItem(MuscleGroup.LEGS)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val binding = ItemExerciseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseListViewHolder, position: Int) {
        val item = exercises[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = exercises.size

    fun swapItems(from: Int, to: Int) {
        Collections.swap(exercises, from, to)
    }

    fun removeItem(pos: Int) {
        exercises.removeAt(pos)
    }
}