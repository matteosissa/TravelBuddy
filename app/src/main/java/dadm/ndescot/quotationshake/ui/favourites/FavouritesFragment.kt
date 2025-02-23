package dadm.ndescot.quotationshake.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.ndescot.quotationshake.R
import dadm.ndescot.quotationshake.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private val viewModel: FavouritesViewModel by viewModels()
    private var _binding : FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter()
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouriteQuotations.collect { quotations ->
                    adapter.submitList(quotations)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}