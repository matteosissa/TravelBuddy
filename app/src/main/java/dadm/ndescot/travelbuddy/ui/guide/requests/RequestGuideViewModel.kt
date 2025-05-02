package dadm.ndescot.travelbuddy.ui.guide.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class RequestGuideViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private var _requests = MutableStateFlow<List<Trip>>(emptyList())
    val requests = _requests.asStateFlow()


    fun getTripsByLocation(site: Site) {
        viewModelScope.launch {
            _requests.value = guideRepository.getTripsByLocation(site.siteName, site.countryName)
        }
    }

    fun addAnswerToTrip(message: String, tripId: Int) {
        viewModelScope.launch {
            val userId = localUserDataRepository.getUserId().first()!!
            guideRepository.addAnswerToTrip(userId, tripId, message, LocalDateTime.now())
        }
    }

}