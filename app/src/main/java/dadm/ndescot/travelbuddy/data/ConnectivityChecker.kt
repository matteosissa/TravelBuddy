package dadm.ndescot.travelbuddy.data

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import javax.inject.Inject

/**
 * Class to check if the device has an internet connection.
 *
 * @param connectivityManager The [ConnectivityManager] instance to check network capabilities.
 */
class ConnectivityChecker @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    /**
     * Checks if the device has an active internet connection.
     *
     * @return true if the device is connected to the internet, false otherwise.
     */
    fun isConnectionAvailable(): Boolean {

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return (capabilities != null && (capabilities.hasTransport(TRANSPORT_CELLULAR) || capabilities.hasTransport(
            TRANSPORT_WIFI
        )))
    }
}