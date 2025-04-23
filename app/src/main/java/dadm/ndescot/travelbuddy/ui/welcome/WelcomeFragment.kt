package dadm.ndescot.travelbuddy.ui.welcome

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

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
            if(userName.isNotEmpty()) {
                // Add new user to the DB and locally
                viewModel.addNewUser(userName)
                // Navigate to the home page (trips fragment)
                val navController = findNavController()
                navController.navigate(R.id.action_welcomeFragment_to_mainLayoutFragment)
            } else {
                Toast.makeText(requireContext(), "Please enter a valid username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }



}