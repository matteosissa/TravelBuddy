package dadm.ndescot.travelbuddy.ui.mainlayout

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigationrail.NavigationRailView
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentMainLayoutBinding
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainLayoutFragment @Inject constructor() : Fragment(R.layout.fragment_main_layout),
    MenuProvider {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var _binding: FragmentMainLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainLayoutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainLayoutBinding.bind(view)

        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            navController = binding.navHostFragment.getFragment<NavHostFragment>().navController

            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.tripFragment, R.id.sitesGuideFragment)
            )

            setupActionBarWithNavController(
                requireActivity() as AppCompatActivity,
                navController,
                appBarConfiguration
            )
            when (val navView = binding.bottomNavigationView) {
                is BottomNavigationView -> navView.setupWithNavController(navController)
                is NavigationRailView -> navView.setupWithNavController(navController)
            }

            NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        lifecycleScope.launch {
            viewModel.uiState.collect { successfulLogout ->
                when(successfulLogout) {
                    is UiState.Success -> {

                        Toast.makeText(requireContext(), successfulLogout.data, Toast.LENGTH_SHORT).show()

                        val activityNavController = requireActivity()
                            .findNavController(R.id.mainHostContainer)
                        val navOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.entry_nav_graph, true) // clear the entire entry graph
                            .build()

                        activityNavController.navigate(R.id.welcomeFragment, null, navOptions)

                        viewModel.resetState()
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), successfulLogout.message, Toast.LENGTH_SHORT).show()
                        viewModel.resetState()
                    }
                    is UiState.Idle -> { }

                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomNavigationView) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.systemBars()
            )
            view.updatePadding(
                left = bars.left,
                top = 0,
                right = 0,
                bottom = bars.bottom
            )
            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.navHostFragment) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.systemBars()
            )
            view.updatePadding(
                left = 0,
                top = 0,
                right = bars.right,
                bottom = if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) bars.bottom else 0
            )
            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.appBarLayout) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.systemBars()
            )
            view.updatePadding(
                left = bars.left,
                top = 0,
                right = 0,
                bottom = 0
            )
            WindowInsetsCompat.CONSUMED
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_about, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_about -> {
                navController.navigate(R.id.aboutDialogFragment)
                true
            }

            R.id.menu_logout -> {
                viewModel.logout()
                true
            }

            else -> false
        }
    }

}