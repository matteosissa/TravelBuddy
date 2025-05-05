package dadm.ndescot.travelbuddy.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Used to display information about the application.
 */
@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAboutBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}