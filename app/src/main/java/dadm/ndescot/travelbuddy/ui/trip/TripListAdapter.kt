package dadm.ndescot.travelbuddy.ui.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import dadm.ndescot.travelbuddy.databinding.TripItemBinding
import dadm.ndescot.travelbuddy.domain.model.Trip
import java.text.SimpleDateFormat
import java.util.Locale

class TripListAdapter(private val onItemClick: (String) -> Unit) : ListAdapter<Trip,
        TripListAdapter.ViewHolder>(TripDiff) {

    object TripDiff : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.date == newItem.date && oldItem.locationCity == newItem.locationCity
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: TripItemBinding, private val onItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(binding.tvCity.text.toString())
                }
            }
        }

        fun bind(trip: Trip) {
            binding.tvCity.text = trip.locationCity
            binding.tvCountry.text = trip.locationCountry

            val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
            binding.tvDate.text = dateFormat.format(trip.date)

            binding.tvDuration.text = "${trip.durationDays} days"
            binding.tvBudget.text = "$${trip.budget}"

            // Set up activity chips
            binding.chipGroupActivities.removeAllViews()
            trip.activities.forEach { activity ->
                val chip = Chip(binding.chipGroupActivities.context)
                chip.text = activity.name
                binding.chipGroupActivities.addView(chip)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TripItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}