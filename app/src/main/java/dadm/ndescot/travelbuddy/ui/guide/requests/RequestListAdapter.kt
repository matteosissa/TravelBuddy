package dadm.ndescot.travelbuddy.ui.guide.requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.databinding.TripRequestItemBinding
import dadm.ndescot.travelbuddy.domain.model.Trip

class RequestListAdapter : ListAdapter<Trip, RequestListAdapter.RequestViewHolder>(RequestDiff) {


    class RequestViewHolder(private val requestItemBinding: TripRequestItemBinding) : RecyclerView.ViewHolder(requestItemBinding.root) {

        fun bind(request: Trip) {
            requestItemBinding.tvTravellerName.text = request.username
            requestItemBinding.tvStartDate.text = "${request.date.day}/${request.date.month}/${request.date.year}"
            requestItemBinding.tvDuration.text = "${request.durationDays} days"
            requestItemBinding.tvTravellerDescription.text = request.description
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(
            TripRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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