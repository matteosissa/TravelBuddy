package dadm.ndescot.quotationshake.ui.favourites

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dadm.ndescot.quotationshake.R
import dadm.ndescot.quotationshake.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {
    private val viewModel: FavouritesViewModel by activityViewModels()
    private var _binding : FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter()
        binding.recyclerView.adapter = adapter

        requireActivity().addMenuProvider(this,
            viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.favouriteQuotations.collect { quotations ->
                        adapter.submitList(quotations)
                    }
                }

                launch {
                    viewModel.isDeleteAllMenuVisible.collect {
                        requireActivity().invalidateMenu()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_favourites_delete -> {
                findNavController().navigate(R.id.deleteAllDialogFragment)
                true
            }
            else -> false
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        val deleteMenuItem = menu.findItem(R.id.menu_favourites_delete)
        deleteMenuItem.isVisible = viewModel.isDeleteAllMenuVisible.value
    }

}