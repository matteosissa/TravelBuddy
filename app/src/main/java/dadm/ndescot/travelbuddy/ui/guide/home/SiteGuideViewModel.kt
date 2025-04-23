package dadm.ndescot.travelbuddy.ui.guide.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SiteGuideViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val userId = localUserDataRepository.getUserId()

    private var _guideSites = MutableStateFlow<List<Site>>(emptyList())
    val guideSites = _guideSites.asStateFlow()

    private suspend fun getGuideSitesByUserId(id: Int) {
        _guideSites.value = guideRepository.getGuideSitesByUserId(id)
    }

    init {
        viewModelScope.launch {
            getGuideSitesByUserId(userId.first())
        }
    }

}