package dadm.ndescot.travelbuddy.ui.trip.tripanswers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripAnswersViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel(){

    private val _tripAnswers = MutableStateFlow(emptyList<GuideAnswer>())
    val tripAnswers = _tripAnswers.asStateFlow()

    fun loadTripAnswers(tripId: Int) {
        viewModelScope.launch {
            _tripAnswers.value = tripRepository.getTripAnswers(tripId)
        }
    }

}