package cz.muni.fi.circularworkout.ui.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import cz.muni.fi.circularworkout.databinding.FragmentWorkoutListBinding

class WorkoutListFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutListBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Workouts"
        return binding.root
    }

}