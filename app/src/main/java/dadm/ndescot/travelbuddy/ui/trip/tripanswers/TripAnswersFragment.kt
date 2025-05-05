package dadm.ndescot.travelbuddy.ui.trip.tripanswers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentTripAnswersBinding
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment that shows the answers from the guides to a specific trip.
 * It uses a RecyclerView to display the answers and allows the user to start a chat with the guide.
 */
@AndroidEntryPoint
class TripAnswersFragment : Fragment(R.layout.fragment_trip_answers) {
    private var _binding: FragmentTripAnswersBinding? = null
    val binding get() = _binding!!
    private val viewModel: TripAnswersViewModel by viewModels()

    private val args: TripAnswersFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTripAnswersBinding.bind(view)

        // Fetch the answers through the viewmodel
        viewModel.loadTripAnswers(args.tripId)

        val adapter = TripAnswersListAdapter { guideAnswer ->
            val phoneNumber = guideAnswer.guidePhoneNumber
            val cleanedNumber = phoneNumber.replace(Regex("[^\\d+]"), "")
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$cleanedNumber")
            }
            startActivity(intent)
        }

        binding.rvGuideAnswers.adapter = adapter
        binding.rvGuideAnswers.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
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

                launch {
                    viewModel.tripAnswers.collect { tripAnswers ->
                        adapter.submitList(tripAnswers)
                    }
                }
            }
        }
    }
}