package dadm.ndescot.travelbuddy.ui.trip

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentTripsBinding
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment that displays a list of trips.
 * It uses the TripViewModel to fetch and display the trips.
 * It also handles navigation to the CreateTripFragment and TripAnswersFragment.
 */
@AndroidEntryPoint
class TripFragment : Fragment(R.layout.fragment_trips) {
    private var _binding: FragmentTripsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TripViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTripsBinding.bind(view)

        val adapter = TripListAdapter { id ->
            val action = TripFragmentDirections.actionTripFragmentToTripAnswersFragment(id)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter

        // Fetch trips
        viewModel.getTripsByUserId()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.trips.collect { trips ->
                        adapter.submitList(trips)
                    }
                }

                launch {
                    viewModel.uiState.collect { state ->
                        when (state) {
                            is UiState.Success -> {
                                Toast.makeText(requireContext(), state.data, Toast.LENGTH_SHORT)
                                    .show()
                                viewModel.resetState()
                            }

                            is UiState.Error -> {
                                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                    .show()
                                viewModel.resetState()
                            }

                            is UiState.Idle -> {
                                // Do nothing
                            }
                        }
                    }
                }

            }
        }

        binding.fabAddTrip.setOnClickListener {
            findNavController().navigate(R.id.action_tripFragment_to_createTripFragment)
        }

        // Manage the refresh of data in case of adding a new site
        parentFragmentManager.setFragmentResultListener(
            "refresh", viewLifecycleOwner
        ) { key, bundle ->
            val refresh = bundle.getBoolean("refresh")
            if (refresh) {
                println("Refreshing data")
                viewModel.getTripsByUserId()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}