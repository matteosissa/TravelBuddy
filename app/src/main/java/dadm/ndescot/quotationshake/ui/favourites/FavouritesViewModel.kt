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
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(favouritesRepository: FavouritesRepository): ViewModel() {

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
    //    _favouriteQuotations.update { emptyList() }
    }

    fun deleteQuotationAtPosition(position: Int) {
    //    val copy = _favouriteQuotations.value.toMutableList()
    //    copy.removeAt(position)
    //    _favouriteQuotations.update { copy }
    }
}