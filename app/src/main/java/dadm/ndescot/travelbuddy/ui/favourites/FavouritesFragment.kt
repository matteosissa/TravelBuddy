package dadm.ndescot.travelbuddy.ui.favourites

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {
    private val viewModel: FavouritesViewModel by activityViewModels()
    private var _binding : FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter{ authorName ->
            if (authorName == "Anonymous") {
                Snackbar.make(
                    binding.root,
                    getString(R.string.anonymous_author_error),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                try {
                    val searchUrl = "https://en.wikipedia.org/wiki/Special:Search?search=$authorName"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.unable_to_handle_action),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }


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

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteQuotationAtPosition(position)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
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