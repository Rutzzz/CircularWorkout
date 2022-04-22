package cz.muni.fi.circularworkout.ui.workout.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutSetupBinding


class WorkoutSetupFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutSetupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutSetupBinding.inflate(layoutInflater, container, false)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_solid)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.title = "New workout"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ExerciseListAdapter()
        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.exerciseRecyclerView.adapter = adapter
    }
}