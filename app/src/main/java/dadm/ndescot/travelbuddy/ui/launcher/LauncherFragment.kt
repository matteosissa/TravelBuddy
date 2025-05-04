package dadm.ndescot.travelbuddy.ui.launcher

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LauncherFragment : Fragment(R.layout.fragment_launcher) {

    private val viewModel: LauncherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Observe the value of the isFirstAccess to decide the first view
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isFirstAccess.collect { isFirstAccess ->
                val navController = findNavController()
                if (isFirstAccess) {
                    try {
                        navController.navigate(R.id.action_launcherFragment_to_welcomeFragment)
                    } catch (e: Exception) {
                        Log.e("LauncherFragment", "Error navigating to WelcomeFragment", e)
                        Toast.makeText(requireContext(), "There was an error while trying to enter the application", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    try {
                        findNavController().navigate(R.id.action_launcherFragment_to_mainLayoutFragment)
                    } catch (e: Exception) {
                        Log.e("LauncherFragment", "Error navigating to MainLayoutFragment", e)
                        Toast.makeText(requireContext(), "There was an error while trying to enter the application", Toast.LENGTH_SHORT).show()
                    }
                }

                cancel()
            }
        }
    }
}