package dadm.ndescot.travelbuddy.ui.guide.requests

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentExploreRequestsGuideBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RequestGuideFragment : Fragment(R.layout.fragment_explore_requests_guide){

    private var _binding : FragmentExploreRequestsGuideBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RequestGuideViewModel by viewModels()

    private val args: RequestGuideFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExploreRequestsGuideBinding.bind(view)

        // Fetch the data
        // Note, calling this method will update the list of requests, so the coroutine will also trigger to update the UI
        viewModel.getTripsByLocation(args.site)
        val customAdapter = RequestListAdapter {
            trip -> showAddAnswerDialog(trip.id)


        }
        binding.rvExploreRequestsGuide.adapter = customAdapter
        binding.rvExploreRequestsGuide.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.requests.collect {
                    requests -> customAdapter.submitList(requests)
                }
            }
        }

    }

    private fun showAddAnswerDialog(tripId: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_guide_reply, null)
        val editText = dialogView.findViewById<EditText>(R.id.ietGuideMessage)

        AlertDialog.Builder(requireContext())
            .setTitle("Add answer for the traveller")
            .setView(dialogView)
            .setPositiveButton("Send") { dialog, _ -> // use view model
                val message = editText.text.toString()
                if(message.isNotEmpty()) {
                    viewModel.addAnswerToTrip(message, tripId)
                } else  {
                    editText.error = "Message cannot be empty"
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}