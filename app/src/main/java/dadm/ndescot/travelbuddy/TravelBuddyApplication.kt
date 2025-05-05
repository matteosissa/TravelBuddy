package dadm.ndescot.travelbuddy

import android.app.Application
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * This is the main application class for the TravelBuddy app.
 * It initializes the Hilt dependency injection framework and provides access to the LocalUserDataRepository.
 */
@HiltAndroidApp
class TravelBuddyApplication: Application() {
    @Inject
    lateinit var localUserDataRepository: LocalUserDataRepository

    override fun onCreate() {
        super.onCreate()
    }
}