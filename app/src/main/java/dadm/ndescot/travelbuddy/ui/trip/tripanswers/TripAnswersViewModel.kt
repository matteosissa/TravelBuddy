package dadm.ndescot.travelbuddy.ui.trip.tripanswers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the TripAnswers screen.
 *
 * @param tripRepository The repository to fetch trip answers from.
 */
@HiltViewModel
class TripAnswersViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel() {

    private val _tripAnswers = MutableStateFlow(emptyList<GuideAnswer>())
    val tripAnswers = _tripAnswers.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    /**
     * Load the trip answers for a given trip ID.
     *
     * @param tripId The ID of the trip to load answers for.
     */
    fun loadTripAnswers(tripId: Int) {
        viewModelScope.launch {
            try {
                _tripAnswers.value = tripRepository.getTripAnswers(tripId)
            } catch (e: Exception) {
                Log.e("TripAnswersViewModel", "Error loading trip answers", e)
                _uiState.value = UiState.Error("Error loading trip answers")
            }
        }
    }
    /** reset the UI state to idle */
    fun resetState() {
        _uiState.value = UiState.Idle
    }
}