package dadm.ndescot.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.UUID

class FavouritesViewModel: ViewModel() {
    private val _favouriteQuotations = MutableStateFlow<List<Quotation>>(getFavouriteQuotations())

    val favouriteQuotations : StateFlow<List<Quotation>> = _favouriteQuotations.asStateFlow()

    val isDeleteAllMenuVisible = favouriteQuotations.map { list ->
        list.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = true
    )

    private fun getFavouriteQuotations(): List<Quotation> {
        return List(20) { index ->
            Quotation(
                id = UUID.randomUUID().toString(),
                quote = "Random quote number $index",
                author = "Author $index"
            )
        }
    }

    fun deleteAllQuotations() {
        _favouriteQuotations.update { emptyList() }
    }
}