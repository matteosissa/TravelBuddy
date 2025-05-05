package dadm.ndescot.travelbuddy.ui.guide.addsite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for adding a new site to the guide.
 *
 * @param guideRepository Repository for managing guide data.
 * @param localUserDataRepository Repository for managing local user data.
 */
@HiltViewModel
class AddSiteViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    /**
     * Adds a new site to the guide.
     *
     * @param siteName The name of the site to be added.
     * @param countryName The name of the country where the site is located.
     */
    fun addSite(siteName: String, countryName: String) {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()
                val success = guideRepository.addGuideSite(siteName, countryName, userId!!)
                if (success) {
                    _uiState.value = UiState.Success("Site successfully added")
                } else {
                    _uiState.value = UiState.Error("Network error while trying to add the site")
                }
            } catch (e: Exception) {
                Log.e("AddSiteViewModel", "Error adding site: ${e.message}", e)
                _uiState.value = UiState.Error("Error while trying to add the site")
            }
        }
    }

    /**
     * Resets the UI state to idle.
     */
    fun resetState() {
        _uiState.value = UiState.Idle
    }
}