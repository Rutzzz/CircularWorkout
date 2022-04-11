package cz.muni.fi.circularworkout.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cz.muni.fi.circularworkout.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        binding.menuItemStartWorkout.setOnClickListener {
            findNavController()
                .navigate(MenuFragmentDirections.actionMenuToWorkoutSetup())
        }
        return binding.root
    }

}