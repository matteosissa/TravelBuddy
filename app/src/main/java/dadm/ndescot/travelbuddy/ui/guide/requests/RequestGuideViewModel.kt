package dadm.ndescot.travelbuddy.ui.guide.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestGuideViewModel @Inject constructor(private val guideRepository: GuideRepository) : ViewModel() {

    private var _requests = MutableStateFlow<List<Trip>>(emptyList())
    val requests = _requests.asStateFlow()

    suspend fun getTripsByLocation(siteName: String, countryName: String) {
        _requests.value = guideRepository.getTripsByLocation(siteName, countryName)
    }

    init {
        viewModelScope.launch {
            getTripsByLocation("Valencia", "Spain")
            _requests.value.forEach { trip ->
                println(trip)
            }
        }
    }

}