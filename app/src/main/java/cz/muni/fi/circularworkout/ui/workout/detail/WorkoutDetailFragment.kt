package cz.muni.fi.circularworkout.ui.workout.detail

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.WorkoutCreate
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutDetailBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository
import cz.muni.fi.circularworkout.ui.workout.list.WorkoutListAdapter
import cz.muni.fi.circularworkout.ui.workout.setup.SaveWorkoutDialogFragment
import java.lang.IllegalStateException


class WorkoutDetailFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutDetailBinding

    private val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutDetailBinding.inflate(layoutInflater, container, false)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_solid)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workout = workoutRepository.getDetailByName(WorkoutDetailFragmentArgs.fromBundle(requireArguments()).name)
            ?: throw IllegalStateException("Workout with that name doesn't exist")
        binding.toolbar.title = workout.name
        buildExerciseLayout(workout.exerciseNames)
        binding.roundsTextView.text = "Rounds: ${workout.rounds.toString()}"
        binding.exerciseTimeTextView.text = "Exercise: ${workout.exerciseTime.toString()} s"
        binding.restTimeTextView.text = "Rest: ${workout.restTime.toString()} s"
        binding.deleteButton.setOnClickListener {
            val dialog = DeleteWorkoutDetailFragmentDialog {
                workoutRepository.delete(workout.id)
                findNavController().navigateUp()
                Toast.makeText(requireContext(), "Your workout successfully deleted", Toast.LENGTH_SHORT).show()
            }
            activity?.supportFragmentManager?.let {
                dialog.show(it, "delete")
            }
        }
        binding.startWorkoutButton.setOnClickListener {
            findNavController().navigate(WorkoutDetailFragmentDirections
                .actionDetailFragmentToPlayFragment(workout.name))
        }
    }

    private fun buildExerciseLayout(exerciseNames: List<String>) =
        exerciseNames.forEachIndexed { i, exercise ->
        val txtView = TextView(requireContext())
        txtView.text = exercise
        txtView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        txtView.setTextSize(COMPLEX_UNIT_SP, 18F)
        binding.exercisesLayout.addView(txtView)
        if (i != exerciseNames.lastIndex) {
            val image = ImageView(requireContext())
            image.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                R.drawable.ic_arrow_down_long_solid))
            binding.exercisesLayout.addView(image)
        }
    }





}