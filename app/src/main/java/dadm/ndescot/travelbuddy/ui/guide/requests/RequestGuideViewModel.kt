package dadm.ndescot.travelbuddy.ui.guide.requests

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

/**
 * ViewModel for the RequestGuideFragment.
 *
 * @param guideRepository Repository for guide-related data.
 * @param localUserDataRepository Repository for local user data.
 */
@HiltViewModel
class RequestGuideViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private var _trips = MutableStateFlow<List<Trip>>(emptyList())

    private var _requests = MutableStateFlow<List<Trip>>(emptyList())
    val requests = _requests.asStateFlow()

    private var _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    /**
     * Fetches the trips from the repository and updates the UI state.
     */
    fun getTripsByLocation(site: Site) {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()
                _requests.value =
                    guideRepository.getTripsByLocation(site.siteName, site.countryName, userId!!)
                _trips.value = _requests.value.toList() // Create new instance
            } catch (e: Exception) {
                Log.e("RequestGuideViewModel", "Error getting trips by location", e)
                _uiState.value = UiState.Error(R.string.error_getting_trips_by_location_toast)
            }
        }
    }

    /**
     * Fetches the trips from the repository and updates the UI state.
     */
    fun filterTripsByBudget(maxBudget: Int) {
        viewModelScope.launch {
            try {
                _requests.value = _trips.value.filter { trip -> trip.budget <= maxBudget }
                _uiState.value = UiState.Success(R.string.trips_filtered_by_budget_toast)
            } catch (e: Exception) {
                Log.e("RequestGuideViewModel", "Error filtering trips by budget", e)
                _uiState.value = UiState.Error(R.string.error_filtering_trips_by_budget_toast)
            }
        }
    }

    /**
     * Fetches the trips from the repository and updates the UI state.
     */
    fun addAnswerToTrip(message: String, tripId: Int) {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()!!
                val successful =
                    guideRepository.addAnswerToTrip(userId, tripId, message, Instant.now())
                if (successful) {
                    _uiState.value = UiState.Success(R.string.answer_to_trip_added_successfully_toast)
                } else {
                    _uiState.value = UiState.Error(R.string.error_adding_answer_to_trip_toast)
                }
            } catch (e: Exception) {
                Log.e("RequestGuideViewModel", "Error adding answer to trip", e)
                _uiState.value = UiState.Error(R.string.error_adding_answer_to_trip_toast)
            }

        }
    }

    /**
     * Fetches the trips from the repository and updates the UI state.
     */
    fun resetState() {
        _uiState.value = UiState.Idle
    }
}