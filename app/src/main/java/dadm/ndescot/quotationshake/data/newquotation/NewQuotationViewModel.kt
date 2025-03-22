package dadm.ndescot.quotationshake.data.newquotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.quotationshake.data.settings.SettingsRepository
import dadm.ndescot.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val newQuotationRepository: NewQuotationRepository,
    private val settingsRepository: SettingsRepository
): ViewModel() {
     val userName = settingsRepository.getUserName().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
     )

    private val _quotation = MutableStateFlow<Quotation?>(null)
    val currentQuote = _quotation.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isAddFavoriteVisible = MutableStateFlow<Boolean>(false)
    val isAddFavoriteVisible = _isAddFavoriteVisible.asStateFlow()

    private val _isError = MutableStateFlow<Throwable?>(null)
    val error = _isError.asStateFlow()


    fun resetError() {
        _isError.update { null }
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