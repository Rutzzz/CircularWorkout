package cz.muni.fi.circularworkout.ui.workout.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutDetailBinding

class WorkoutDetailFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutDetailBinding

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

        binding.toolbar.title = WorkoutDetailFragmentArgs.fromBundle(requireArguments()).name

    }

}