package dadm.ndescot.travelbuddy.ui.trip.tripanswers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.databinding.TripAnswerItemBinding
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer

/**
 * Adapter for displaying a list of trip answers.
 *
 * @param onButtonClick Callback function to handle button clicks for each answer.
 */
class TripAnswersListAdapter(
    private val onButtonClick: (GuideAnswer) -> Unit
) : ListAdapter<GuideAnswer, TripAnswersListAdapter.ViewHolder>(TripAnswerDiff) {

    /**
     * DiffUtil.ItemCallback for comparing trip answers.
     */
    object TripAnswerDiff : DiffUtil.ItemCallback<GuideAnswer>() {
        override fun areItemsTheSame(oldItem: GuideAnswer, newItem: GuideAnswer): Boolean {
            return oldItem.userId == newItem.userId && oldItem.tripId == newItem.tripId
        }

        override fun areContentsTheSame(oldItem: GuideAnswer, newItem: GuideAnswer): Boolean {
            return oldItem.message == newItem.message
        }
    }

    /**
     * ViewHolder for trip answer items.
     *
     * @param binding The binding for the trip answer item layout.
     * @param onButtonClick Callback function to handle button clicks for each answer.
     */
    class ViewHolder(
        private val binding: TripAnswerItemBinding, private val onButtonClick: (GuideAnswer) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guideAnswer: GuideAnswer) {
            binding.tvGuideName.text = guideAnswer.userName
            binding.tvGuideMessage.text = guideAnswer.message

            binding.btStartChatWithGuide.setOnClickListener {
                onButtonClick(guideAnswer)
            }
        }
    }

    /**
     * Creates a new ViewHolder for trip answer items.
     *
     * @param parent The parent view group.
     * @param viewType The view type of the new ViewHolder.
     * @return A new ViewHolder for trip answer items.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TripAnswerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onButtonClick
        )
    }

    /**
     * Binds the trip answer data to the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}