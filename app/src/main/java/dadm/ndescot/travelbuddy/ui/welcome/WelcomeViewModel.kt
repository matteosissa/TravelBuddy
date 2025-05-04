package dadm.ndescot.travelbuddy.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val localUserDataRepository: LocalUserDataRepository,
    private val remoteUserDataRepository: RemoteUserDataRepository
): ViewModel() {

    private val _successfulRegistration = MutableStateFlow(false)
    val successfulRegistration = _successfulRegistration.asStateFlow()

    fun logInUser(userName: String, phoneNumber: String) {
        viewModelScope.launch {
            var userId : Int? = remoteUserDataRepository.getUserId(userName, phoneNumber)

            if(userId == null) {    // Create new user
                userId = remoteUserDataRepository.addNewUser(userName, phoneNumber)
            }

            localUserDataRepository.setUserName(userName)
            localUserDataRepository.setUserId(userId)
            localUserDataRepository.setPhoneNumber(phoneNumber)
            localUserDataRepository.getUserId().first()
            localUserDataRepository.getUserName().first()
            localUserDataRepository.getPhoneNumber().first()
            println("Registered the information of the user locally with phone number : $phoneNumber")
            println("Trying to fetch the user phone number from the datastore: ${localUserDataRepository.getPhoneNumber().first()}")
            _successfulRegistration.value = true
        }
    }

}