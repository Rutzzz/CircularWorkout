package cz.muni.fi.circularworkout.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.fi.circularworkout.databinding.FragmentStatisticsBinding
import cz.muni.fi.circularworkout.repository.StatisticsRepository
import cz.muni.fi.circularworkout.repository.WorkoutHistoryRepository


class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding

    private val statisticsRepository: StatisticsRepository by lazy {
        StatisticsRepository(requireContext())
    }

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
        val statistics = statisticsRepository.getStatistics()
        binding.totalMinutes.text = (statistics.totalSeconds / 60).toString()
        binding.totalWorkouts.text = statistics.totalWorkouts.toString()
        return binding.root
    }

}