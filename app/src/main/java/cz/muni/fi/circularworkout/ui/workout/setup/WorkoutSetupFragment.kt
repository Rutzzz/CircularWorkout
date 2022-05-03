package cz.muni.fi.circularworkout.ui.workout.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.WorkoutCreate
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutSetupBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository
import cz.muni.fi.circularworkout.util.getExercises
import cz.muni.fi.circularworkout.util.getMuscleGroups


class WorkoutSetupFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutSetupBinding

    private val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutSetupBinding.inflate(layoutInflater, container, false)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_solid)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.title = "New workout"
        val pauseAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.pause,
            android.R.layout.simple_spinner_item
        )
        val roundsAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.rounds,
            android.R.layout.simple_spinner_item
        )
        val exerciseAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.exercise_time,
            android.R.layout.simple_spinner_item
        )
        binding.pause.optionsGroupTextView.setText("Pause")
        binding.pause.exerciseSpinner.adapter = pauseAdapter
        binding.rounds.optionsGroupTextView.setText("Rounds")
        binding.rounds.exerciseSpinner.adapter = roundsAdapter
        binding.exerciseTime.optionsGroupTextView.setText("Exercise time")
        binding.exerciseTime.exerciseSpinner.adapter = exerciseAdapter
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ExerciseListAdapter(requireContext())
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.START or ItemTouchHelper.END) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition
                    adapter.swapItems(from, to)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val pos = viewHolder.adapterPosition
                    adapter.removeItem(pos)
                }

                override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                    super.onSelectedChanged(viewHolder, actionState)
                    if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                        viewHolder?.itemView?.alpha = 0.5f
                    }

                }

                override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                    super.clearView(recyclerView, viewHolder)
                    viewHolder.itemView.alpha = 1.0f
                }

            }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)

        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemTouchHelper.attachToRecyclerView(binding.exerciseRecyclerView)
        binding.exerciseRecyclerView.adapter = adapter

        binding.exerciseAddButton.setOnClickListener {
            val dialog = AddExerciseDialogFragment( onItemPicked = {
                adapter.addItem(it)
            }, muscleGroups = getMuscleGroups(getExercises(requireContext())))
            activity?.supportFragmentManager?.let {
                dialog.show(it, "test")
            }
        }

        binding.saveButton.setOnClickListener {
            val dialog = SaveWorkoutDialogFragment( onNamePicked = { name ->
                if (!workoutRepository.workoutWithNameExists(name)) {
                    val newWorkout = WorkoutCreate(
                        name = name,
                        exercises = adapter.getSelectedExercises(),
                        exerciseTime = binding.exerciseTime.exerciseSpinner.selectedItem.toString().toInt(),
                        restTime = binding.pause.exerciseSpinner.selectedItem.toString().toInt(),
                        rounds = binding.rounds.exerciseSpinner.selectedItem.toString().toInt()
                    )
                    workoutRepository.create(newWorkout)
                    binding.saveButton.isEnabled = false
                    Toast.makeText(requireContext(), "Workout successfully saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Workout with that name already exists", Toast.LENGTH_SHORT).show()
                }

            })
            activity?.supportFragmentManager?.let {
                dialog.show(it, "save")
            }
        }
    }
}