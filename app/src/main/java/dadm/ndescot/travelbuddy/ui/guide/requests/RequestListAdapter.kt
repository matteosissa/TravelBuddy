package dadm.ndescot.travelbuddy.ui.guide.requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.databinding.TripRequestItemBinding
import dadm.ndescot.travelbuddy.domain.model.Trip
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter for displaying a list of trip requests.
 *
 * @param onClick Lambda function to handle click events on each trip request item.
 */
class RequestListAdapter(
    private val onClick: (Trip) -> Unit
) : ListAdapter<Trip, RequestListAdapter.RequestViewHolder>(RequestDiff) {

    /**
     * ViewHolder for trip request items.
     *
     * @param requestItemBinding Binding for the trip request item layout.
     * @param onClick Lambda function to handle click events on the item.
     */
    class RequestViewHolder(
        private val requestItemBinding: TripRequestItemBinding,
        private val onClick: (Trip) -> Unit
    ) : RecyclerView.ViewHolder(requestItemBinding.root) {

        fun bind(request: Trip) {
            requestItemBinding.tvTravellerName.text = request.username
            val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
            requestItemBinding.tvStartDate.text = dateFormat.format(request.date)
            requestItemBinding.tvDuration.text = requestItemBinding.root.context.resources
                .getQuantityString(
                    R.plurals.trip_duration_days,
                    request.durationDays,
                    request.durationDays
                )
            requestItemBinding.tvTravellerDescription.text = request.description

            requestItemBinding.btContactThem.setOnClickListener {
                onClick(request)
            }
        }

    }

    /**
     * Creates a new ViewHolder for trip request items.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new instance of RequestViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(
            TripRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    /**
     * Binds the trip request data to the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Returns the number of items in the list.
     *
     * @return The size of the current list.
     */
    override fun getItemCount(): Int {
        return currentList.size
    }

    /**
     * DiffUtil for calculating differences between old and new lists of trip requests.
     */
    object RequestDiff : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.id == newItem.id
        }
    }
}