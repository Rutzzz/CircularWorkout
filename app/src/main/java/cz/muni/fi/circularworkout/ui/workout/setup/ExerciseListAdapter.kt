package cz.muni.fi.circularworkout.ui.workout.setup

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.data.ExerciseListItem
import cz.muni.fi.circularworkout.databinding.ItemExerciseListBinding
import cz.muni.fi.circularworkout.util.getExerciseOptions
import cz.muni.fi.circularworkout.util.getExercises
import cz.muni.fi.circularworkout.util.getMuscleGroups
import java.util.*

class ExerciseListAdapter(private val context: Context,
                          private val onItemCountChanged: (Int) -> Unit) : RecyclerView.Adapter<ExerciseListViewHolder>() {

    private val exercises: MutableList<ExerciseListItem> by lazy {
        getMuscleGroups(getExercises(context)).map {
            ExerciseListItem(
                muscleGroup = it,
                selectedExercise = 0L
            )
        }.toMutableList()
    }

    private val options: Map<String, List<String>> by lazy {
        getExerciseOptions(getExercises(context))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val binding = ItemExerciseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseListViewHolder(getExercises(context), binding)
    }

    override fun onBindViewHolder(holder: ExerciseListViewHolder, position: Int) {
        val item = exercises[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = exercises.size

    fun swapItems(from: Int, to: Int) {
        Collections.swap(exercises, from, to)
        notifyItemMoved(from, to)
    }

    fun removeItem(pos: Int) {
        exercises.removeAt(pos)
        notifyItemRemoved(pos)
        onItemCountChanged(itemCount)
    }

    fun addItem(muscleGroup: String) {
        exercises.add(ExerciseListItem(muscleGroup))
        notifyItemInserted(exercises.size - 1)
        onItemCountChanged(itemCount)
    }

    fun getSelectedExercises() : List<String> = exercises.map {
        val exercisesForMuscleGroup = options[it.muscleGroup]!!
        exercisesForMuscleGroup[it.selectedExercise.toInt()]
    }
}