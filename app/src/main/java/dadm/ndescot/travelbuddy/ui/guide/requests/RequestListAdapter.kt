package dadm.ndescot.travelbuddy.ui.guide.requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.data.guide.Request
import dadm.ndescot.travelbuddy.databinding.TripRequestItemBinding

class RequestListAdapter : ListAdapter<Request, RequestListAdapter.RequestViewHolder>(RequestDiff) {


    class RequestViewHolder(private val requestItemBinding: TripRequestItemBinding) : RecyclerView.ViewHolder(requestItemBinding.root) {

        fun bind(request: Request) {
            requestItemBinding.tvTravellerName.text = request.travellerName
            requestItemBinding.tvStartDate.text = request.startDate
            requestItemBinding.tvDuration.text = request.duration
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


    object RequestDiff : DiffUtil.ItemCallback<Request>() {
        override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem.travellerName == newItem.travellerName &&
                    oldItem.startDate == newItem.startDate &&
                    oldItem.duration == newItem.duration &&
                    oldItem.description == newItem.description

        }

    }

}