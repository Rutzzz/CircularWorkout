package cz.muni.fi.circularworkout.ui.workout.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val pauseAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.pause,
            android.R.layout.simple_spinner_item
        )
        val roundsAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.rounds,
            android.R.layout.simple_spinner_item
        )
        binding.pause.optionsGroupTextView.setText("Pause")
        binding.pause.exerciseSpinner.adapter = pauseAdapter
        binding.rounds.optionsGroupTextView.setText("Rounds")
        binding.rounds.exerciseSpinner.adapter = roundsAdapter
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ExerciseListAdapter()
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.START or ItemTouchHelper.END) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition
                    adapter.swapItems(from, to)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val pos = viewHolder.adapterPosition
                    adapter.removeItem(pos)
                }

                override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                    super.onSelectedChanged(viewHolder, actionState)
                    if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                        viewHolder?.itemView?.alpha = 0.5f
                    }

                }

                override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                    super.clearView(recyclerView, viewHolder)
                    viewHolder.itemView.alpha = 1.0f
                }

            }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)

        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemTouchHelper.attachToRecyclerView(binding.exerciseRecyclerView)
        binding.exerciseRecyclerView.adapter = adapter

        binding.exerciseAddButton.setOnClickListener {
            val dialog = AddExerciseDialogFragment( onItemPicked = {
                adapter.addItem(it)
            })
            activity?.supportFragmentManager?.let {
                dialog.show(it, "test")
            }
        }
    }
}