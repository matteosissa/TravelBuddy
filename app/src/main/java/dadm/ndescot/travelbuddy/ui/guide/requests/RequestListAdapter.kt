package dadm.ndescot.travelbuddy.ui.guide.requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.databinding.TripRequestItemBinding
import dadm.ndescot.travelbuddy.domain.model.Trip
import java.text.SimpleDateFormat
import java.util.Locale

class RequestListAdapter(
    private val onClick: (Trip) -> Unit
) : ListAdapter<Trip, RequestListAdapter.RequestViewHolder>(RequestDiff) {


    class RequestViewHolder(private val requestItemBinding: TripRequestItemBinding, private val onClick: (Trip) -> Unit) : RecyclerView.ViewHolder(requestItemBinding.root) {

        fun bind(request: Trip) {
            requestItemBinding.tvTravellerName.text = request.username
            val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
            requestItemBinding.tvStartDate.text = dateFormat.format(request.date)
            requestItemBinding.tvDuration.text = "${request.durationDays} days"
            requestItemBinding.tvTravellerDescription.text = request.description

            requestItemBinding.btContactThem.setOnClickListener {
                onClick(request)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(
            TripRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    object RequestDiff : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.id == newItem.id
        }

    }

}