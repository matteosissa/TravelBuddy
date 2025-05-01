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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            viewModel.successfulRequest.collect {
                if(it) {
                    // Send a signal to the previous fragment to refresh data
                    val refresh = Bundle().apply { putBoolean("refresh", true) }
                    parentFragmentManager.setFragmentResult("refresh", refresh)
                    findNavController().popBackStack()
                }
            }
        }

    }
}