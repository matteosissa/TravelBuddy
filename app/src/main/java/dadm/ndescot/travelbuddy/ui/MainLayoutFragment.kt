package dadm.ndescot.travelbuddy.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentMainLayoutBinding

class MainLayoutFragment : Fragment(R.layout.fragment_main_layout), MenuProvider {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var _binding: FragmentMainLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainLayoutBinding.bind(view)

        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.tripFragment, R.id.sitesGuideFragment)
        )

        setupActionBarWithNavController(
            requireActivity() as AppCompatActivity,
            navController,
            appBarConfiguration
        )
        binding.bottomNavigationView.setupWithNavController(navController)

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_about, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        /*return when (menuItem.itemId) {
            R.id.menu_about -> {
                navController.navigate(R.id.aboutDialogFragment)
                true
            }
            else -> false
        }*/
        return false
    }



}