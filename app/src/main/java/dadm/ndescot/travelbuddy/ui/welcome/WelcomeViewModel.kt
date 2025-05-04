package dadm.ndescot.travelbuddy.ui.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataRepository
import dadm.ndescot.travelbuddy.utils.UiState
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

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun logInUser(userName: String, phoneNumber: String) {
        viewModelScope.launch {
            try {
                var newUser = false
                var userId : Int? = remoteUserDataRepository.getUserId(userName, phoneNumber)

                if(userId == null) {    // Create new user
                    userId = remoteUserDataRepository.addNewUser(userName, phoneNumber)
                    newUser = true
                }

                localUserDataRepository.setUserName(userName)
                localUserDataRepository.setUserId(userId!!)
                localUserDataRepository.setPhoneNumber(phoneNumber)

                if(newUser) {
                    _uiState.value = UiState.Success("New user registered successfully")

                } else {
                    _uiState.value = UiState.Success("User logged in successfully")
                }
            } catch (e: Exception) {
                Log.e("WelcomeViewModel", "Error logging in user", e)
                _uiState.value = UiState.Error("Error logging in user")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}