package dadm.ndescot.travelbuddy.ui.guide.addsite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentNewSiteBinding
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment to add a new site to the guide.
 * It contains two EditText fields for the location and country, and a button to create the site.
 * When the button is clicked, it checks if the fields are filled and calls the ViewModel to add the site.
 * If successful, it shows a success message and navigates back to the previous fragment.
 */
@AndroidEntryPoint
class AddSiteFragment : Fragment(R.layout.fragment_new_site) {

    private var _binding : FragmentNewSiteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : AddSiteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewSiteBinding.bind(view)

        binding.btnCreateNewSite.setOnClickListener {
            val location = binding.etCreateNewSiteLocation.text.toString()
            val country = binding.etCreateNewSiteCountry.text.toString()

            if(location.isNotEmpty() && country.isNotEmpty()) {
                viewModel.addSite(location, country)

            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Success -> {
                        Toast.makeText(requireContext(), state.data, Toast.LENGTH_SHORT).show()
                        // Send a signal to the previous fragment to refresh data
                        val refresh = Bundle().apply { putBoolean("refresh", true) }
                        parentFragmentManager.setFragmentResult("refresh", refresh)
                        findNavController().popBackStack()

                        viewModel.resetState()
                    }

                    is UiState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        viewModel.resetState()
                    }

                    is UiState.Idle -> { // Do nothing }
                    }
                }
            }
        }

    }
}