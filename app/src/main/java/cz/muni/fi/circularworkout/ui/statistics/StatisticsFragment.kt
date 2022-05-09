package cz.muni.fi.circularworkout.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.muni.fi.circularworkout.databinding.FragmentStatisticsBinding
import cz.muni.fi.circularworkout.repository.WorkoutHistoryRepository
import cz.muni.fi.circularworkout.repository.WorkoutRepository

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

        val workoutHistory = workoutHistoryRepository.getAll()
        var total = 0
        for (historyItem in workoutHistory){
            total += historyItem.duration.minute;
        }


        return binding.root
    }

}