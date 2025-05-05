package dadm.ndescot.travelbuddy.ui.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataRepository
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Welcome screen.
 * It handles the logic for logging in a user and updating the UI state.
 *
 * @param localUserDataRepository Repository for local user data.
 * @param remoteUserDataRepository Repository for remote user data.
 */
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val localUserDataRepository: LocalUserDataRepository,
    private val remoteUserDataRepository: RemoteUserDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    /**
     * Logs in a user with the provided username and phone number.
     * If the user does not exist, a new user is created.
     *
     * @param userName The username of the user.
     * @param phoneNumber The phone number of the user.
     */
    fun logInUser(userName: String, phoneNumber: String) {
        viewModelScope.launch {
            try {
                var newUser = false
                var userId: Int? = remoteUserDataRepository.getUserId(userName, phoneNumber)

                if (userId == null) {    // Create new user
                    userId = remoteUserDataRepository.addNewUser(userName, phoneNumber)
                    newUser = true
                }

                localUserDataRepository.setUserName(userName)
                localUserDataRepository.setUserId(userId!!)
                localUserDataRepository.setPhoneNumber(phoneNumber)

                if (newUser) {
                    _uiState.value = UiState.Success(R.string.new_user_registered_successfully_toast)

                } else {
                    _uiState.value = UiState.Success(R.string.user_logged_in_successfully_toast)
                }
            } catch (e: Exception) {
                Log.e("WelcomeViewModel", "Error logging in user", e)
                _uiState.value = UiState.Error(R.string.error_logging_in_user_toast)
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