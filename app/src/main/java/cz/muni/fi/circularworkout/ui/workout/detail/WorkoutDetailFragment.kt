package cz.muni.fi.circularworkout.ui.workout.detail

import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.WorkoutDetail
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutDetailBinding


class WorkoutDetailFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutDetailBinding

    private fun getMockedInstance(name: String) = WorkoutDetail(
        name = name,
        exerciseNames = listOf("Pull up", "Squat", "Dead lift", "Chin up"),
        exerciseTime = 30,
        restTime = 20,
        rounds = 3
    )

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
        val workout = getMockedInstance(WorkoutDetailFragmentArgs.fromBundle(requireArguments()).name)
        binding.toolbar.title = workout.name
        buildExerciseLayout(workout.exerciseNames)
        binding.roundsTextView.text = workout.rounds.toString()
        binding.exerciseTimeTextView.text = workout.exerciseTime.toString()
        binding.restTimeTextView.text = workout.restTime.toString()
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