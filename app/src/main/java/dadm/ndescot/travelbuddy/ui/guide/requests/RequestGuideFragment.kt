package dadm.ndescot.travelbuddy.ui.guide.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentExploreRequestsGuideBinding
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip
import java.util.Date

class RequestGuideFragment : Fragment(R.layout.fragment_explore_requests_guide){

    private var _binding : FragmentExploreRequestsGuideBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExploreRequestsGuideBinding.bind(view)

        val dataset = listOf(
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Valencia",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                1300,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Valencia",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                1300,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Valencia",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                1300,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
            Trip(
                0,
                "Matteo Sissa",
                Date(123, 5, 15), // June 15, 2023),
                "Valencia",
                "Spain",
                listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING),
                13,
                1300,
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
            ),
        )

        val customAdapter = RequestListAdapter()
        binding.rvExploreRequestsGuide.adapter = customAdapter
        binding.rvExploreRequestsGuide.layoutManager = LinearLayoutManager(requireContext())
        customAdapter.submitList(dataset)

    }
}