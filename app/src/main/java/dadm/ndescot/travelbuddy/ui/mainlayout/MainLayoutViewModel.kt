package dadm.ndescot.travelbuddy.ui.mainlayout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the MainLayout screen.
 *
 * @param localUserDataRepository Repository for user data operations.
 */
@HiltViewModel
class MainLayoutViewModel @Inject constructor(
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    /**
     * Initializes the ViewModel by checking if the user is logged in.
     */
    fun logout() {
        viewModelScope.launch {
            try {
                localUserDataRepository.deleteUserId()
                _uiState.value = UiState.Success(R.string.logout_successful_toast)
            } catch (e: Exception) {
                Log.e("MainLayoutViewModel", "Error logging out", e)
                _uiState.value = UiState.Error(R.string.logout_failed_toast)

            }
        }
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise.
     */
    fun resetState() {
        _uiState.value = UiState.Idle
    }
}