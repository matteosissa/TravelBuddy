package dadm.ndescot.quotationshake.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.quotationshake.databinding.QuotationItemBinding
import dadm.ndescot.quotationshake.domain.model.Quotation

class QuotationListAdapter : ListAdapter<Quotation,
        QuotationListAdapter.ViewHolder>(QuotationDiff) {

    object QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: QuotationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quotation: Quotation) {
            binding.tvQuoteItemText.text = quotation.quote
            binding.tvQuoteItemAuthor.text = quotation.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuotationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}