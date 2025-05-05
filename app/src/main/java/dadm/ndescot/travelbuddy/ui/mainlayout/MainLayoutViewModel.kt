package dadm.ndescot.travelbuddy.ui.mainlayout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainLayoutViewModel @Inject constructor(
    private val localUserDataRepository: LocalUserDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            try {
                localUserDataRepository.deleteUserId()
                _uiState.value = UiState.Success("Logout successful")
            } catch (e: Exception) {
                Log.e("MainLayoutViewModel", "Error logging out", e)
                _uiState.value = UiState.Error("Logout failed")

            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }

}