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

/**
 * ViewModel for managing trips.
 *
 * @property tripRepository Repository for trip data.
 * @property localUserDataRepository Repository for local user data.
 */
@HiltViewModel
class TripViewModel @Inject constructor(
    private val tripRepository: TripRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    /**
     * Creates a new trip.
     *
     * @param city The city of the trip.
     * @param country The country of the trip.
     * @param date The date of the trip.
     * @param activities The list of activities for the trip.
     * @param durationDays The duration of the trip in days.
     * @param budget The budget for the trip.
     * @param description A description of the trip.
     */
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
                if (success) {
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

    /**
     * Get trips by user ID.
     */
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

    /**
     * Reset the UI state to idle.
     */
    fun resetState() {
        _uiState.value = UiState.Idle
    }
}