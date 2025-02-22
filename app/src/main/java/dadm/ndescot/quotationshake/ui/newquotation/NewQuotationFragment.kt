package dadm.ndescot.quotationshake.ui.newquotation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.ndescot.quotationshake.R
import dadm.ndescot.quotationshake.databinding.FragmentNewQuotationBinding
import kotlinx.coroutines.launch

class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewQuotationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(this,
            viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentNewQuotationBinding.bind(view)

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getNewQuotation()
        }

        binding.fabAddToFavorites.setOnClickListener {
            viewModel.addToFavorites()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userName.collect { userName ->
                        binding.tvGreetings.text = getString(
                            R.string.greetings,
                            userName.ifEmpty { getString(R.string.anonymous) }
                        )
                    }
                }

                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.swipeToRefresh.isRefreshing = isLoading
                    }
                }

                launch {
                    viewModel.currentQuote.collect { quotation ->
                        binding.tvGreetings.isVisible = quotation == null

                        quotation?.let {
                            binding.tvQuoteText.text = it.text
                            binding.tvQuoteAuthor.text =
                                if (it.author.isEmpty()) "Anonymous" else it.author
                        }
                    }
                }

                launch {
                    viewModel.isAddFavoriteVisible.collect { isVisible ->
                        binding.fabAddToFavorites.isVisible = isVisible
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
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.refresh_button -> {
                viewModel.getNewQuotation()
                true
            }
            else -> false
        }
    }
}
