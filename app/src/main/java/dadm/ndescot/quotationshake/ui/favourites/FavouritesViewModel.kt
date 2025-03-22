package dadm.ndescot.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.quotationshake.data.favourites.FavouritesRepository
import dadm.ndescot.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val favouritesRepository: FavouritesRepository): ViewModel() {

    val favouriteQuotations : StateFlow<List<Quotation>> = favouritesRepository.getAllQuotations().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )

    val isDeleteAllMenuVisible = favouriteQuotations.map { list ->
        list.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = true
    )

    fun deleteAllQuotations() {
        viewModelScope.launch { favouritesRepository.deleteAllQuotations() }
    }

    fun deleteQuotationAtPosition(position: Int) {
        val quotationToDelete = favouriteQuotations.value.getOrNull(position)
        quotationToDelete?.let { quotation ->
            viewModelScope.launch {
                favouritesRepository.deleteQuotation(quotation)
            }
        }
    }
}