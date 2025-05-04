package dadm.ndescot.travelbuddy

import android.app.Application
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TravelBuddyApplication: Application() {
    @Inject
    lateinit var localUserDataRepository: LocalUserDataRepository

    override fun onCreate() {
        super.onCreate()
    }
}