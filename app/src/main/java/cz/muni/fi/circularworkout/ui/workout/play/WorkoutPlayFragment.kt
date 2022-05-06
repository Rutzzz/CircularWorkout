package cz.muni.fi.circularworkout.ui.workout.play

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutPlayBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository
import java.lang.IllegalStateException

class WorkoutPlayFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutPlayBinding

    private val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository(requireContext())
    }

    private val workout by lazy {
        workoutRepository.getDetailByName(WorkoutPlayFragmentArgs.fromBundle(requireArguments()).name)
            ?: throw IllegalStateException("Workout with that name doesn't exist")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutPlayBinding.inflate(layoutInflater, container, false)
        binding.workoutName.text = workout.name
        binding.exerciseInfo.text = workout.exerciseNames[0]
        val timer = object: CountDownTimer(10000, 1000) {
            override fun onTick(m: Long) {
                val seconds = m / 1000 + 1
                binding.timerNumber.text = seconds.toString()
            }

            override fun onFinish() {
                binding.timerNumber.text = "DONE !"
            }

        }
        timer.start()
        return binding.root
    }

}