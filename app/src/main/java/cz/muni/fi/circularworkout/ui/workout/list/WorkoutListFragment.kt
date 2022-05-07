package cz.muni.fi.circularworkout.ui.workout.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutListBinding
import cz.muni.fi.circularworkout.repository.WorkoutRepository

class WorkoutListFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutListBinding

    private val workoutRepository: WorkoutRepository by lazy {
        WorkoutRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutListBinding.inflate(layoutInflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(WorkoutListFragmentDirections.actionListFragmentToSetupFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WorkoutListAdapter(
            workoutRepository = workoutRepository,
            onStartClick =  {
                findNavController()
                    .navigate(WorkoutListFragmentDirections.actionListFragmentToPlayFragment(it))
            },
            onItemClick = {
                findNavController()
                    .navigate(WorkoutListFragmentDirections.actionListFragmentToDetailFragment(it.name))
            }
        )
        binding.workoutsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.workoutsRecyclerView.adapter = adapter
    }

}