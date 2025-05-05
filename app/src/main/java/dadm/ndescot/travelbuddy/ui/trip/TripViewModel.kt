package dadm.ndescot.travelbuddy.ui.trip

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.Date

@HiltViewModel
class TripViewModel @Inject constructor(
    private val tripRepository: TripRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    fun createTrip(
        city: String,
        country: String,
        date: Date,
        activities: List<Activity>,
        durationDays: Int,
        budget: Int,
        description: String
    ) {
        val trip = Trip(
            id = 0,
            username = "",
            locationCity = city,
            locationCountry = country,
            date = date,
            activities = activities,
            durationDays = durationDays,
            budget = budget,
            description = description
        )
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId()
                val success = tripRepository.createTrip(trip, userId.first()!!)
                if(success) {
                    _uiState.value = UiState.Success("Trip created successfully")
                } else {
                    _uiState.value = UiState.Error("Error creating trip")
                }
            } catch (e: Exception) {
                Log.e("TripViewModel", "Error creating trip", e)
                _uiState.value = UiState.Error("Error creating trip")
            }
        }
    }

    fun getTripsByUserId() {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId()
                _trips.value = tripRepository.getTripsByUserId(userId.first()!!)
                // Do not add success here
            } catch (e: Exception) {
                Log.e("TripViewModel", "Error fetching trips", e)
                _uiState.value = UiState.Error("Error fetching trips")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}