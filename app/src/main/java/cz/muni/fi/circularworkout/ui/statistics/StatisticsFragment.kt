package cz.muni.fi.circularworkout.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.fi.circularworkout.databinding.FragmentStatisticsBinding
import cz.muni.fi.circularworkout.repository.WorkoutHistoryRepository


class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding

    private val workoutHistoryRepository: WorkoutHistoryRepository by lazy {
        WorkoutHistoryRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(layoutInflater, container, false)


        val adapter = HistoryListAdapter(
            workoutHistoryRepository = workoutHistoryRepository
        )
        binding.workoutsHistoryView.layoutManager = LinearLayoutManager(requireContext())
        binding.workoutsHistoryView.adapter = adapter
        val history = adapter.history
//        val totalTime = history.map { workout -> workout.exerciseTime * workout.rounds * workout.exercises.size }.sum()
//        val hours = totalTime / 3600
//        val minutes = (totalTime % 3600) / 60
//        val seconds = totalTime % 60
//        binding.totalHours.text = "$hours:$minutes:$seconds"
        binding.totalHours.text = "TBD"
//        binding.totalHours.text = totalTime.toString()
        binding.totalWorkouts.text = history.size.toString()
        return binding.root
    }

}