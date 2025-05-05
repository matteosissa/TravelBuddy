package dadm.ndescot.travelbuddy.ui.guide.requests

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.time.LocalDateTime
import javax.inject.Inject

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


    fun getTripsByLocation(site: Site) {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()
                _requests.value =
                    guideRepository.getTripsByLocation(site.siteName, site.countryName, userId!!)
                _trips.value = _requests.value.toList() // Create new instance
            } catch (e: Exception) {
                Log.e("RequestGuideViewModel", "Error getting trips by location", e)
                _uiState.value = UiState.Error("Error getting trips by location")
            }
        }
    }

    fun filterTripsByBudget(maxBudget: Int) {
        viewModelScope.launch {
            try {
                _requests.value = _trips.value.filter { trip -> trip.budget <= maxBudget }
                _uiState.value = UiState.Success("Trips filtered by budget")
            } catch (e: Exception) {
                Log.e("RequestGuideViewModel", "Error filtering trips by budget", e)
                _uiState.value = UiState.Error("Error filtering trips by budget")
            }
        }
    }

    fun addAnswerToTrip(message: String, tripId: Int) {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()!!
                val successful = guideRepository.addAnswerToTrip(userId, tripId, message, Instant.now())
                if(successful) {
                    _uiState.value = UiState.Success("Answer to trip added successfully")
                } else {
                    _uiState.value = UiState.Error("Error adding answer to trip")
                }
            } catch (e: Exception) {
                Log.e("RequestGuideViewModel", "Error adding answer to trip", e)
                _uiState.value = UiState.Error("Error adding answer to trip")
            }

        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }

}