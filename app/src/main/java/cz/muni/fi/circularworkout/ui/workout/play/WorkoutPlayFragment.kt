package cz.muni.fi.circularworkout.ui.workout.play

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.WorkoutInterval
import cz.muni.fi.circularworkout.data.WorkoutIntervalType
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutPlayBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository
import cz.muni.fi.circularworkout.util.workoutToIntervals

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

    private lateinit var zeroMediaPlayer: MediaPlayer
    private lateinit var oneMediaPlayer: MediaPlayer
    private lateinit var twoMediaPlayer: MediaPlayer
    private lateinit var threeMediaPlayer: MediaPlayer
    private lateinit var fiveMediaPlayer: MediaPlayer
    private lateinit var tenMediaPlayer: MediaPlayer
    private lateinit var completedMediaPlayer: MediaPlayer

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

        zeroMediaPlayer = readAudioFile(R.raw.zero)
        oneMediaPlayer = readAudioFile(R.raw.one)
        twoMediaPlayer = readAudioFile(R.raw.two)
        threeMediaPlayer = readAudioFile(R.raw.three)
        fiveMediaPlayer = readAudioFile(R.raw.five)
        tenMediaPlayer = readAudioFile(R.raw.ten)
        completedMediaPlayer = readAudioFile(R.raw.completed)

        startTimer()
        return binding.root
    }

    private fun readAudioFile(resourceId: Int): MediaPlayer {
        val afd: AssetFileDescriptor = resources.openRawResourceFd(resourceId)
        val mp = MediaPlayer()
        afd.use { a ->
            mp.apply {
                setDataSource(a.fileDescriptor, a.startOffset, a.length)
                isLooping = false
                prepare()
            }
        }
        return mp
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
                binding.timerNumber.animate().apply {
                    duration = 100
                    val scale = 1.15f
                    scaleX(scale)
                    scaleY(scale)
                }.withEndAction {
                    binding.timerNumber.animate().apply {
                        duration = 400
                        scaleX(1.0f)
                        scaleY(1.0f)
                    }.start()
                }.start()
                when(seconds.toInt()) {
                    0 -> zeroMediaPlayer.start()
                    1 -> oneMediaPlayer.start()
                    2 -> twoMediaPlayer.start()
                    3 -> threeMediaPlayer.start()
                    5 -> fiveMediaPlayer.start()
                    10 -> tenMediaPlayer.start()
                }
                val progress = timeLeftToProgress(seconds.toInt(), intervals[currentIntervalIndex].duration)
                binding.timerProgressBar.progress = progress
            }

            override fun onFinish() {
                binding.timerNumber.text = "0"
                currentIntervalIndex += 1
                if (currentIntervalIndex != intervals.lastIndex + 1) {
                    currentTimeLeft = intervals[currentIntervalIndex].duration.toLong() * 1000 + 1000
                    if (isAdded) startTimer()
                } else {
                    if (isAdded) finishExercise()
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
        changeBackgroundColor(intervalTypeToBackgroundColor(intervals[currentIntervalIndex].type))
    }

    private fun finishExercise() {
        changeBackgroundColor(R.color.blue_bg)
        completedMediaPlayer.start()
        binding.exerciseInfo.text = "Completed!"
        binding.playPauseButton.setImageResource(R.drawable.ic_xmark_solid)
        binding.playPauseButton.setOnClickListener { findNavController().navigateUp() }
    }

    private fun changeBackgroundColor(color: Int) {
        val x = ContextCompat.getColor(requireContext(), color)
        val csl = ContextCompat.getColorStateList(requireContext(), color)
        activity?.window?.statusBarColor = x
        binding.root.backgroundTintList = csl
        binding.workoutName.backgroundTintList = csl
        binding.workoutRound.backgroundTintList = csl
        binding.workoutExerciseNumber.backgroundTintList = csl
        binding.exerciseInfo.backgroundTintList = csl
        binding.timerNumber.backgroundTintList = csl
        binding.playPauseButton.backgroundTintList = csl
    }

    private fun intervalTypeToBackgroundColor(intervalType: WorkoutIntervalType) = when(intervalType) {
        WorkoutIntervalType.WORKOUT -> R.color.pink_500
        WorkoutIntervalType.REST -> R.color.green_500
        WorkoutIntervalType.PREPARATION -> R.color.blue_bg
    }

    private fun timeLeftToProgress(timeLeft: Int, duration: Int): Int =
        ((1f - timeLeft.toFloat() / duration.toFloat()) * 100f).toInt()


    override fun onDestroy() {
        super.onDestroy()
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_bg)
    }

}