package dadm.ndescot.travelbuddy.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.data.ConnectivityChecker
import dadm.ndescot.travelbuddy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * MainActivity is the entry point of the application.
 * It checks for internet connectivity and shows a dialog if there is no connection.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityChecker: ConnectivityChecker

    private var noConnectionDialog: AlertDialog? = null
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            runOnUiThread {
                noConnectionDialog?.dismiss()
            }
        }

        override fun onLost(network: Network) {
            runOnUiThread {
                showNoConnectionDialog()
            }
        }
    }

    /**
     * onCreate is called when the activity is first created.
     * It sets up the layout and checks for internet connectivity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setSupportActionBar(binding.toolbar)

        checkInternetConnection()

        registerNetworkCallback()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainHostContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * checkInternetConnection checks if there is an internet connection available.
     * If not, it shows a dialog to the user.
     */
    private fun checkInternetConnection() {
        if (!connectivityChecker.isConnectionAvailable()) {
            showNoConnectionDialog()
        }
    }

    /**
     * showNoConnectionDialog shows a dialog to the user indicating that there is no internet connection.
     */
    private fun showNoConnectionDialog() {
        if (noConnectionDialog == null || !noConnectionDialog!!.isShowing) {
            val builder = AlertDialog.Builder(this).setTitle(getString(R.string.no_internet_connection_dialog))
                .setMessage(getString(R.string.please_check_your_internet_connection_and_try_again_dialog))
                .setCancelable(false) // Make it non-dismissable

            noConnectionDialog = builder.create()
            noConnectionDialog?.show()
        }
    }

    /**
     * registerNetworkCallback registers a network callback to listen for changes in network connectivity.
     */
    private fun registerNetworkCallback() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    /**
     * onDestroy is called when the activity is destroyed.
     * It unregisters the network callback to avoid memory leaks.
     */
    override fun onDestroy() {
        super.onDestroy()
        try {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            // Ignore
        }
    }
}