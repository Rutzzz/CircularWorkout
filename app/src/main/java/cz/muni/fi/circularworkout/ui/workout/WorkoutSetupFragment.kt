package cz.muni.fi.circularworkout.ui.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import cz.muni.fi.circularworkout.R


class WorkoutSetupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.title = "Workout setup"
        }
        return inflater.inflate(R.layout.fragment_workout_setup, container, false)
    }

}