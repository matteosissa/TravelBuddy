package dadm.ndescot.travelbuddy.ui.guide.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentSitesGuideBinding
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment to show the list of sites that the user has added to the app
 */
@AndroidEntryPoint
class GuideSitesFragment : Fragment(R.layout.fragment_sites_guide) {

    private var _binding : FragmentSitesGuideBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GuideSitesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSitesGuideBinding.bind(view)

        val customAdapter = SiteListAdapter { site ->
            val action = GuideSitesFragmentDirections.actionGuideSitesFragmentToRequestGuideFragment(site)
            findNavController().navigate(action)
        }
        binding.rvSitesGuide.adapter = customAdapter
        binding.rvSitesGuide.layoutManager = LinearLayoutManager(requireContext())

        // Fetch the sites from the source
        viewModel.getGuideSitesByUserId()

        // Listen to changes to the list of sites and update the adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.guideSites.collect {
                    sites -> customAdapter.submitList(sites)
                }
            }
        }

        // listen to changes in the state of the view model
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when(state) {
                        is UiState.Success -> {
                            viewModel.resetState()
                        }
                        is UiState.Error -> {
                            Toast.makeText(requireContext(), getString(state.message), Toast.LENGTH_SHORT).show()
                            viewModel.resetState()
                        }
                        is UiState.Idle -> {
                            // Do nothing
                        }
                    }
                }
            }
        }

        binding.fabAddSite.setOnClickListener {
            findNavController().navigate(R.id.action_guideSitesFragment_to_addSiteFragment)
        }

        // Manage the refresh of data in case of adding a new site
        parentFragmentManager.setFragmentResultListener("refresh", viewLifecycleOwner) {
            key, bundle ->
            val refresh = bundle.getBoolean("refresh")
            if(refresh) {
                println("Refreshing data")
                viewModel.getGuideSitesByUserId()
            }
        }
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}