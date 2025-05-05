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

/**
 * Adapter for displaying a list of trips in a RecyclerView.
 *
 * @param onItemClick Lambda function to handle item click events.
 */
class TripListAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<Trip, TripListAdapter.ViewHolder>(TripDiff) {

    /**
     * DiffUtil.ItemCallback for comparing Trip items in the list.
     */
    object TripDiff : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.date == newItem.date && oldItem.locationCity == newItem.locationCity
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * ViewHolder for the TripListAdapter.
     *
     * @param binding The binding for the trip item layout.
     * @param onItemClick Lambda function to handle item click events.
     */
    class ViewHolder(private val binding: TripItemBinding, private val onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

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

            binding.root.setOnClickListener {
                onItemClick(trip.id)
            }
        }
    }

    /**
     * Creates a new ViewHolder for the trip item layout.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TripItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClick
        )

    }

    /**
     * Binds the trip data to the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}