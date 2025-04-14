package dadm.ndescot.travelbuddy.ui.trip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.Date

@HiltViewModel
class TripViewModel @Inject constructor(private val tripRepository: TripRepository) : ViewModel() {
    fun createTrip(city: String, country: String, date: Date, activities: List<Activity>, durationDays: Int, budget: Int, description: String) {
        val trip = Trip(
            id = 123456,
            username = "default id",
            locationCity = city,
            locationCountry = country,
            date = date,
            activities = activities,
            durationDays = durationDays,
            budget = budget,
            description = description,
        )
        viewModelScope.launch {
            // Todo add trip to the repository, once it is connected to firebase
            //tripRepository.createTrip(trip)
        }
    }

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    init {
        viewModelScope.launch {
            _trips.value = tripRepository.getTrips()
        }
    }
}