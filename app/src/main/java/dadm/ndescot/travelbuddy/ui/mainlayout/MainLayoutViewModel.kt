package dadm.ndescot.travelbuddy.ui.mainlayout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
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

    private val _successfulLogout = MutableStateFlow(false)
    val successfulLogout: StateFlow<Boolean> = _successfulLogout.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            localUserDataRepository.deleteUserId()
            _successfulLogout.value = true
        }
    }

}