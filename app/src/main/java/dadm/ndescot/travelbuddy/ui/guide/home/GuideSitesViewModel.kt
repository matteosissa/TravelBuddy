package dadm.ndescot.travelbuddy.ui.guide.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.R
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

/**
 * ViewModel class responsible for the logic of the Guide Sites screen
 *
 * @param guideRepository Repository to get the guide sites
 * @param localUserDataRepository Repository to get the user data
 */
@HiltViewModel
class GuideSitesViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private var _guideSites = MutableStateFlow<List<Site>>(emptyList())
    val guideSites = _guideSites.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    /**
     * Function to get the guide sites from the repository
     */
    fun getGuideSitesByUserId() {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()
                _guideSites.value = guideRepository.getGuideSitesByUserId(userId!!)
            } catch (e: Exception) {
                Log.e("SiteGuideViewModel", "Error getting guide sites", e)
                _uiState.value = UiState.Error(R.string.error_while_trying_to_get_guide_sites_toast)
            }
        }
    }
    
    fun deleteSite(site: Site) {
        viewModelScope.launch {
            try {
                val userId = localUserDataRepository.getUserId().first()
                val result = guideRepository.deleteSite(userId!!, site.siteName, site.countryName)
                if (result) {
                    _uiState.value =
                        UiState.Success(R.string.your_site_has_been_deleted_successfully_toast)
                    _guideSites.value = _guideSites.value.filter { it != site }
                } else {
                    Log.e("SiteGuideViewModel", "Error deleting guide site")
                    _uiState.value =
                        UiState.Error(R.string.there_was_a_problem_deleting_the_site_toast)
                }
            } catch (e: Exception) {
                Log.e("SiteGuideViewModel", "Error deleting guide site", e)
                _uiState.value = UiState.Error(R.string.there_was_a_problem_deleting_the_site_toast)
            }
        }
    }


    /**
     * Function to get the guide sites from the repository
     */
    fun resetState() {
        _uiState.value = UiState.Idle
    }
}