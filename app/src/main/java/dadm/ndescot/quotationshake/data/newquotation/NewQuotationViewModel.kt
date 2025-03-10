package dadm.ndescot.quotationshake.data.newquotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val newQuotationRepository: NewQuotationRepository
): ViewModel() {
    private val generatedName = getUserName()
    private val _userName = MutableStateFlow<String>(generatedName)

    private val _quotation = MutableStateFlow<Quotation?>(null)
    val currentQuote = _quotation.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isAddFavoriteVisible = MutableStateFlow<Boolean>(false)
    val isAddFavoriteVisible = _isAddFavoriteVisible.asStateFlow()

    private val _isError = MutableStateFlow<Throwable?>(null)
    val error = _isError.asStateFlow()

    val userName = _userName.asStateFlow()

    fun resetError() {
        _isError.update { null }
    }

    private fun getUserName() : String {

        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "Nicolas", "").random()

    }

    fun getNewQuotation() {
        _isLoading.update { true }
        _isAddFavoriteVisible.update { false }

        viewModelScope.launch {
            newQuotationRepository.getNewQuotation().fold(
                onSuccess = { quotation ->
                    _quotation.update { quotation }
                    _isAddFavoriteVisible.update { true }
                    _isError.update { null }
                },
                onFailure = { throwable ->
                    _isError.update { throwable }
                }
            )

            _isLoading.update { false }
        }
    }

    fun addToFavorites() {
        _isAddFavoriteVisible.update { false }
    }
}