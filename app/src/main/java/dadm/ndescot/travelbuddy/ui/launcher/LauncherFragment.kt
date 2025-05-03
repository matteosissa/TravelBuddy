package dadm.ndescot.travelbuddy.ui.launcher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dagger.hilt.android.AndroidEntryPoint
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
                    navController.navigate(R.id.action_launcherFragment_to_welcomeFragment)
                } else {
                    findNavController().navigate(R.id.action_launcherFragment_to_mainLayoutFragment)
                }
            }
        }
    }
}