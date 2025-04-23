package dadm.ndescot.travelbuddy.ui.launcher

import androidx.lifecycle.ViewModel
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(private val localUserDataRepository: LocalUserDataRepository) : ViewModel() {

    val isFirstAccess: Flow<Boolean> = localUserDataRepository.getUserId().map {
        id -> id == -1
    }

}