package dadm.ndescot.travelbuddy.ui.trip.tripanswers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.databinding.TripAnswerItemBinding
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer

class TripAnswersListAdapter(
    private val onButtonClick: (GuideAnswer) -> Unit
) : ListAdapter<GuideAnswer, TripAnswersListAdapter.ViewHolder>(TripAnswerDiff) {


    object TripAnswerDiff : DiffUtil.ItemCallback<GuideAnswer>() {
        override fun areItemsTheSame(oldItem: GuideAnswer, newItem: GuideAnswer): Boolean {
            return oldItem.userId == newItem.userId && oldItem.tripId == newItem.tripId
        }

        override fun areContentsTheSame(oldItem: GuideAnswer, newItem: GuideAnswer): Boolean {
            return oldItem.message == newItem.message
        }
    }

    class ViewHolder(private val binding: TripAnswerItemBinding, private val onButtonClick: (GuideAnswer) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guideAnswer: GuideAnswer) {
            binding.tvGuideName.text = guideAnswer.userName
            binding.tvGuideMessage.text = guideAnswer.message

            binding.btStartChatWithGuide.setOnClickListener {
                onButtonClick(guideAnswer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TripAnswerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onButtonClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}