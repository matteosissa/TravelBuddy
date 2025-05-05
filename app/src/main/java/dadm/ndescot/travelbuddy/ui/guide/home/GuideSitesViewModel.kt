package dadm.ndescot.travelbuddy.ui.guide.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.domain.model.Site
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideSitesViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private var _guideSites = MutableStateFlow<List<Site>>(emptyList())
    val guideSites = _guideSites.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun getGuideSitesByUserId() {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()
                _guideSites.value = guideRepository.getGuideSitesByUserId(userId!!)
                _uiState.value = UiState.Success("Guide sites successfully retrieved")
            } catch (e: Exception) {
                Log.e("SiteGuideViewModel", "Error getting guide sites", e)
                _uiState.value = UiState.Error("Error while trying to get guide sites")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }

}