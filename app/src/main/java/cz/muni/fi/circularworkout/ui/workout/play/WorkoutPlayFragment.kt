package cz.muni.fi.circularworkout.ui.workout.play

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.WorkoutInterval
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutPlayBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository
import cz.muni.fi.circularworkout.util.getTotalDuration
import cz.muni.fi.circularworkout.util.workoutToIntervals
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

    private val intervals: List<WorkoutInterval> by lazy { workoutToIntervals(workout) }
    private var currentIntervalIndex: Int = 0

    private lateinit var timer: CountDownTimer
    private var currentTimeLeft: Long = 0
    private var timerRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutPlayBinding.inflate(layoutInflater, container, false)
        binding.workoutName.text = workout.name
        binding.playPauseButton.setOnClickListener {
            if (timerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }
        currentTimeLeft = intervals[0].duration.toLong() * 1000
        startTimer()
        return binding.root
    }

    private fun pauseTimer() {
        timer.cancel()
        timerRunning = false
        binding.playPauseButton.setImageResource(R.drawable.ic_play_solid)
    }

    private fun startTimer() {
        timer = object: CountDownTimer( currentTimeLeft,1000) {
            override fun onTick(m: Long) {
                currentTimeLeft = m
                val seconds = m / 1000
                binding.timerNumber.text = seconds.toString()
            }

            override fun onFinish() {
                binding.timerNumber.text = "0"
                currentIntervalIndex += 1
                if (currentIntervalIndex != intervals.lastIndex) {
                    currentTimeLeft = intervals[currentIntervalIndex].duration.toLong() * 1000 + 1000
                    startTimer()
                } else {
                    finishExercise()
                }
            }
        }.start()
        timerRunning = true
        binding.exerciseInfo.text = intervals[currentIntervalIndex].info
        binding.workoutRound.text =
            "Round ${intervals[currentIntervalIndex].round}/${workout.rounds}"
        binding.workoutExerciseNumber.text =
            "Exercise ${intervals[currentIntervalIndex].exerciseOrder}/${workout.exerciseNames.size}"
        binding.playPauseButton.setImageResource(R.drawable.ic_pause_solid)
    }

    private fun finishExercise() {
        binding.exerciseInfo.text = "Completed!"
        binding.playPauseButton.setImageResource(R.drawable.ic_xmark_solid)
        binding.playPauseButton.setOnClickListener { findNavController().navigateUp() }
    }

}