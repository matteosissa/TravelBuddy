package dadm.ndescot.travelbuddy.ui.welcome

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentWelcomeBinding
import dadm.ndescot.travelbuddy.utils.UiState
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
            if (userName.isNotEmpty() && phoneNumber.isNotEmpty()) {
                // Add new user to the DB and locally
                viewModel.logInUser(userName, phoneNumber)

            } else {
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid username",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is UiState.Success -> {
                            Toast.makeText(requireContext(), state.data, Toast.LENGTH_SHORT).show()
                            val navController = findNavController()
                            navController.navigate(R.id.action_welcomeFragment_to_mainLayoutFragment)
                            viewModel.resetState()
                        }

                        is UiState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                            viewModel.resetState()
                        }

                        is UiState.Idle -> {
                            // Do nothing
                        }
                    }
                }
            }
        }
    }
}