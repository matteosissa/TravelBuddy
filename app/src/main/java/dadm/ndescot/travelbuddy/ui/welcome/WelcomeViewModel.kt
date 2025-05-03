package dadm.ndescot.travelbuddy.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val localUserDataRepository: LocalUserDataRepository,
    private val remoteUserDataRepository: RemoteUserDataRepository
): ViewModel() {

    private val _successfulRegistration = MutableStateFlow(false)
    val successfulRegistration = _successfulRegistration.asStateFlow()

    fun addNewUser(userName: String, phoneNumber: String) {
        viewModelScope.launch {
            val userId = remoteUserDataRepository.addNewUser(userName, phoneNumber)
            localUserDataRepository.setUserName(userName)
            localUserDataRepository.setUserId(userId)
            localUserDataRepository.setPhoneNumber(phoneNumber)
            _successfulRegistration.value = true
        }
    }

}