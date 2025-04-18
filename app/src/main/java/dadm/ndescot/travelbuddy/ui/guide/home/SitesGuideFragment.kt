package dadm.ndescot.travelbuddy.ui.guide.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import dadm.ndescot.travelbuddy.databinding.FragmentSitesGuideBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SitesGuideFragment : Fragment(R.layout.fragment_sites_guide) {

    private var _binding : FragmentSitesGuideBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SiteGuideViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSitesGuideBinding.bind(view)

        val customAdapter = SiteListAdapter()
        binding.rvSitesGuide.adapter = customAdapter
        binding.rvSitesGuide.layoutManager = LinearLayoutManager(requireContext())


        // Listen to changes to the list of sites and update the adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.guideSites.collect {
                    sites -> customAdapter.submitList(sites)
                }
            }
        }
        
    }
}