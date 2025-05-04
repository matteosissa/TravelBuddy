package dadm.ndescot.travelbuddy.ui.guide.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.domain.model.Site
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

    fun getGuideSitesByUserId() {
        viewModelScope.launch {
            _guideSites.value = guideRepository.getGuideSitesByUserId(userId.first()!!)
        }
    }

    init {
        getGuideSitesByUserId()
    }

}