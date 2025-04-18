package dadm.ndescot.travelbuddy.ui.guide.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExploreRequestsGuideBinding.bind(view)

        val customAdapter = RequestListAdapter()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}