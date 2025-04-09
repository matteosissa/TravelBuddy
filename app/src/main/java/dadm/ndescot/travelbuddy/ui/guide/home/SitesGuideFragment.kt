package dadm.ndescot.travelbuddy.ui.guide.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import dadm.ndescot.travelbuddy.databinding.FragmentSitesGuideBinding

class SitesGuideFragment : Fragment(R.layout.fragment_sites_guide) {

    private var _binding : FragmentSitesGuideBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSitesGuideBinding.bind(view)

        val dataset = listOf(
            Site(
                "Paris",
                "France"
            ),
            Site(
                "London",
                "United Kingdom"
            ),
            Site(
                "New York",
                "United States")
        )
        val customAdapter = SiteListAdapter()
        binding.rvSitesGuide.adapter = customAdapter
        binding.rvSitesGuide.layoutManager = LinearLayoutManager(requireContext())
        customAdapter.submitList(dataset)
        
    }
}