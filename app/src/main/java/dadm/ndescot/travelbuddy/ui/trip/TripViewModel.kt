package dadm.ndescot.travelbuddy.ui.trip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.domain.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(private val tripRepository: TripRepository) : ViewModel() {
    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    init {
        viewModelScope.launch {
            _trips.value = tripRepository.getTrips()
        }
    }
}