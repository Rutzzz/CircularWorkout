package cz.muni.fi.circularworkout.ui.workout.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutDetailBinding
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutPlayBinding

class WorkoutPlayFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutPlayBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}