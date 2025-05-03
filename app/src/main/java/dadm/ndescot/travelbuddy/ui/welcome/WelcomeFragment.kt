package dadm.ndescot.travelbuddy.ui.welcome

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWelcomeBinding.bind(view)

        binding.btnUserName.setOnClickListener {
            val userName = binding.etUserName.text.toString().trim()
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            if(userName.isNotEmpty() && phoneNumber.isNotEmpty()) {
                // Add new user to the DB and locally
                viewModel.addNewUser(userName, phoneNumber)

            } else {
                Toast.makeText(requireContext(), "Please enter a valid username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        lifecycleScope.launch {
            viewModel.successfulRegistration.collect { successfulRegistration ->
                if (successfulRegistration) {   // Only when the registration has been successful navigate to the app
                    val navController = findNavController()
                    navController.navigate(R.id.action_welcomeFragment_to_mainLayoutFragment)
                }
            }
        }
    }



}