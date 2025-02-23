package dadm.ndescot.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import dadm.ndescot.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update

class NewQuotationViewModel: ViewModel() {
    private val generatedName = getUserName()
    private val _userName = MutableStateFlow<String>(generatedName)

    private val _quotation = MutableStateFlow<Quotation?>(null)
    val currentQuote = _quotation.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isAddFavoriteVisible = MutableStateFlow<Boolean>(false)
    val isAddFavoriteVisible = _isAddFavoriteVisible.asStateFlow()

    val userName = _userName.asStateFlow()

    private fun getUserName() : String {

        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "Nicolas", "").random()

    }

    fun getNewQuotation() {
        _isLoading.update { true }

        val num = (0..99).random()
        _quotation.update {
            Quotation(
                id = "$num",
                quote = "Quotation text #$num",
                author = "Author #$num"
            )
        }

        _isLoading.update { false }
        _isAddFavoriteVisible.update { true }
    }

    fun addToFavorites() {
        _isAddFavoriteVisible.update { false }
    }
}