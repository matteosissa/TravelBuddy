package dadm.ndescot.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import dadm.ndescot.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class FavouritesViewModel: ViewModel() {
    private val _favouriteQuotations = MutableStateFlow<List<Quotation>>(getFavouriteQuotations())

    val favouriteQuotations : StateFlow<List<Quotation>> = _favouriteQuotations.asStateFlow()

    private fun getFavouriteQuotations(): List<Quotation> {
        return List(20) { index ->
            Quotation(
                id = UUID.randomUUID().toString(),
                quote = "Random quote number $index",
                author = "Author $index"
            )
        }
    }
}