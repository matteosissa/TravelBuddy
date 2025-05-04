package dadm.ndescot.travelbuddy.ui.guide.requests

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.slider.Slider
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

        val slider = view.findViewById<Slider>(R.id.sliderBudget)
        slider.valueFrom = 0f
        slider.valueTo = 10000f
        slider.value = 5000f  // Set to a safe initial value
        slider.stepSize = 100f

        slider.addOnChangeListener { _, value, _ ->
            view.findViewById<TextView>(R.id.tvBudgetValue).text = value.toInt().toString()
        }


        _binding = FragmentExploreRequestsGuideBinding.bind(view)

        // Fetch the data
        // Note, calling this method will update the list of requests, so the coroutine will also trigger to update the UI
        viewModel.getTripsByLocation(args.site)
        val customAdapter = RequestListAdapter {
                trip -> showAddAnswerDialog(trip.id)
        }
        binding.rvExploreRequestsGuide.adapter = customAdapter
        binding.rvExploreRequestsGuide.layoutManager = LinearLayoutManager(requireContext())

        setupFilterSection()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.requests.collect {
                        requests -> customAdapter.submitList(requests)
                }
            }
        }

    }

    private fun setupFilterSection() {
        binding.filterContent.visibility = View.VISIBLE

        binding.btnToggleFilters.setOnClickListener {
            if (binding.filterContent.visibility == View.VISIBLE) {
                binding.filterContent.visibility = View.GONE
                binding.btnToggleFilters.setImageResource(android.R.drawable.arrow_down_float)
            } else {
                binding.filterContent.visibility = View.VISIBLE
                binding.btnToggleFilters.setImageResource(android.R.drawable.arrow_up_float)
            }
        }

        binding.sliderBudget.addOnChangeListener { slider, value, fromUser ->
            val formattedValue = "€${value.toInt()}"
            binding.tvBudgetValue.text = formattedValue
        }

        binding.btnApplyFilters.setOnClickListener {
            val maxBudget = binding.sliderBudget.value.toInt()
            viewModel.filterTripsByBudget(maxBudget)
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