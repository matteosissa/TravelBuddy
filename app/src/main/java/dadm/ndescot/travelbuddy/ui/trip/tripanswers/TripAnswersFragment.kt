package dadm.ndescot.travelbuddy.ui.trip.tripanswers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentTripAnswersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TripAnswersFragment : Fragment(R.layout.fragment_trip_answers) {
    private var _binding : FragmentTripAnswersBinding? = null
    val binding get() = _binding!!
    private val viewModel: TripAnswersViewModel by viewModels()

    private val args: TripAnswersFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTripAnswersBinding.bind(view)

        // Fetch the answers through the viewmodel
        viewModel.loadTripAnswers(args.tripId)

        val adapter = TripAnswersListAdapter {
            // Setup for contact through whattsapp
        }
        binding.rvGuideAnswers.adapter = adapter
        binding.rvGuideAnswers.layoutManager = LinearLayoutManager(requireContext())

        // Observe changes to the list and update the list
        lifecycleScope.launch {
            viewModel.tripAnswers.collect { tripAnswers ->
                adapter.submitList(tripAnswers)
            }
        }

    }


}