package dadm.ndescot.travelbuddy.ui.guide.addsite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSiteViewModel @Inject constructor(
    private val guideRepository: GuideRepository,
    private val userDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val _successfulRequest = MutableStateFlow(false)
    val successfulRequest = _successfulRequest.asStateFlow()

    fun addSite(siteName: String, countryName: String) {

        viewModelScope.launch {
            val userId = userDataRepository.getUserId()
            println("Adding site for user ${userId.first()}")
            guideRepository.addGuideSite(siteName, countryName, userId.first()!!)
            _successfulRequest.value = true

        }

    }


}