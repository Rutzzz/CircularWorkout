package cz.muni.fi.circularworkout.ui.exercises

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import cz.muni.fi.circularworkout.R
import cz.muni.fi.circularworkout.data.Exercise
import cz.muni.fi.circularworkout.databinding.FragmentExercisesBinding
import cz.muni.fi.circularworkout.util.getExercises


class ExercisesFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentExercisesBinding

    private val exercises by lazy {
        getExercises(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExercisesBinding.inflate(layoutInflater, container, false)
        val drawerToggle = ActionBarDrawerToggle(
            activity, binding.drawer, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        val act = activity as AppCompatActivity
        act.apply {
            this.setSupportActionBar(binding.toolbar)
            this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.navigationView.setNavigationItemSelectedListener(this)
        buildMenu(binding.navigationView.menu)
        selectExercise(exercises[0])
        return binding.root
    }

    private fun buildMenu(menu: Menu) {
        exercises.forEachIndexed { i, e ->
            menu.add(Menu.NONE, i, Menu.NONE, e.name )
                .setIcon(R.drawable.ic_book_solid)
        }
    }

    private fun selectExercise(exercise: Exercise) {
        binding.toolbar.title = exercise.name
        binding.exercisePrimaryValue.text = exercise.primary
        binding.exerciseSecondaryValue.text = exercise.secondary
        binding.exerciseDescription.text = exercise.description
        binding.drawer.close()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val exercise = exercises[item.itemId]
        selectExercise(exercise)
        return true
    }

}