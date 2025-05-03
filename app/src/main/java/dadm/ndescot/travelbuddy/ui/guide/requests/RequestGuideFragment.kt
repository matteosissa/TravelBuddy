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

    private val customAdapter = RequestListAdapter()
    private var allTrips = listOf<Trip>()
    private var maxBudget = 10000

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
        setupRecyclerView()
        loadTrips()
        setupFilterListeners()
        setupFilterToggle()
    }

    private fun setupFilterListeners() {
        binding.sliderBudget.apply {
            value = maxBudget.toFloat()
            addOnChangeListener { _, value, _ ->
                maxBudget = value.toInt()
                binding.tvBudgetValue.text = "€$maxBudget"
            }
        }

        // Apply filters button
        binding.btnApplyFilters.setOnClickListener {
            applyFilters()
        }
    }

    private fun applyFilters() {
        val cityFilter = binding.etCityFilter.text.toString().trim().lowercase()

        val filteredTrips = allTrips.filter { trip ->
            val matchesCity = cityFilter.isEmpty() ||
                    trip.locationCity.lowercase().contains(cityFilter)
            val matchesBudget = trip.budget <= maxBudget

            matchesCity && matchesBudget
        }

        customAdapter.submitList(filteredTrips)
    }

    private fun loadTrips() {
        //TODO("Use real database")
        allTrips = listOf(
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Bilbao",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                5000,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Valencia",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                1300,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Valencia",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                1300,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Santander",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                8000,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            )
        )

        customAdapter.submitList(allTrips)
    }

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
    private fun setupRecyclerView() {
        binding.rvExploreRequestsGuide.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupFilterToggle() {
        var filtersExpanded = true

        binding.btnToggleFilters.setOnClickListener {
            filtersExpanded = !filtersExpanded

            if (filtersExpanded) {
                binding.filterContent.visibility = View.VISIBLE
                binding.btnToggleFilters.setImageResource(android.R.drawable.arrow_up_float)
            } else {
                binding.filterContent.visibility = View.GONE
                binding.btnToggleFilters.setImageResource(android.R.drawable.arrow_down_float)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}