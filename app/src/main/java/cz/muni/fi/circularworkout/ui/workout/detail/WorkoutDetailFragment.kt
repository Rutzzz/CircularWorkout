package cz.muni.fi.circularworkout.ui.workout.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.exercise1TextView.text = workout.exerciseNames[0]
        binding.exercise2TextView.text = workout.exerciseNames[1]
        binding.exercise3TextView.text = workout.exerciseNames[2]
        binding.exercise4TextView.text = workout.exerciseNames[3]
        binding.roundsTextView.text = workout.rounds.toString()
        binding.exerciseTimeTextView.text = workout.exerciseTime.toString()
        binding.restTimeTextView.text = workout.restTime.toString()
    }

}