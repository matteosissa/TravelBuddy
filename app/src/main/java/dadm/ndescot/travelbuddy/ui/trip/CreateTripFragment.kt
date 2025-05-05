package dadm.ndescot.travelbuddy.ui.trip

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.FragmentCreateTripBinding
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Fragment to create a new trip.
 * It allows the user to select activities, date, and enter trip details.
 */
@AndroidEntryPoint
class CreateTripFragment : Fragment(R.layout.fragment_create_trip) {
    private var _binding: FragmentCreateTripBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TripViewModel by viewModels()
    private val selectedActivities = mutableSetOf<Activity>()
    private var selectedDate: Date? = null
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateTripBinding.bind(view)

        setupActivityChips()
        setupDatePicker()
        setupSaveButton()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is UiState.Success -> {
                            Toast.makeText(requireContext(), state.data, Toast.LENGTH_SHORT).show()
                            // Previous fragment has to refresh the data
                            val refresh = Bundle().apply { putBoolean("refresh", true) }
                            parentFragmentManager.setFragmentResult("refresh", refresh)

                            findNavController().popBackStack()
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

    private fun setupActivityChips() {
        Activity.values().forEach { activity ->
            val chip = Chip(requireContext()).apply {
                text =
                    activity.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() }
                isCheckable = true
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        selectedActivities.add(activity)
                    } else {
                        selectedActivities.remove(activity)
                    }
                }
            }
            binding.chipGroupActivities.addView(chip)
        }
    }

    private fun setupDatePicker() {
        binding.btnSelectDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                selectedDate = calendar.time
                binding.btnSelectDate.text = dateFormat.format(selectedDate!!)
            }, year, month, day).show()
        }
    }

    private fun setupSaveButton() {
        binding.btnCreateTrip.setOnClickListener {
            if (validateFields()) {
                val city = binding.etCity.text.toString()
                val country = binding.etCountry.text.toString()
                val duration = binding.etDuration.text.toString().toInt()
                val budget = binding.etBudget.text.toString().toInt()
                val description = binding.etDescription.text.toString()

                viewModel.createTrip(
                    city = city,
                    country = country,
                    date = selectedDate!!,
                    activities = selectedActivities.toList(),
                    durationDays = duration,
                    budget = budget,
                    description = description
                )

            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.etCity.text.isNullOrBlank()) {
            binding.tilCity.error = getString(R.string.field_required)
            isValid = false
        } else {
            binding.tilCity.error = null
        }

        if (binding.etCountry.text.isNullOrBlank()) {
            binding.tilCountry.error = getString(R.string.field_required)
            isValid = false
        } else {
            binding.tilCountry.error = null
        }

        if (selectedDate == null) {
            binding.btnSelectDate.error = getString(R.string.field_required)
            isValid = false
        }

        if (binding.etDuration.text.isNullOrBlank()) {
            binding.tilDuration.error = getString(R.string.field_required)
            isValid = false
        } else {
            binding.tilDuration.error = null
        }

        if (binding.etBudget.text.isNullOrBlank()) {
            binding.tilBudget.error = getString(R.string.field_required)
            isValid = false
        } else {
            binding.tilBudget.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}