package dadm.ndescot.travelbuddy.ui.guide.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ndescot.travelbuddy.domain.model.Site
import dadm.ndescot.travelbuddy.databinding.SiteItemBinding

class SiteListAdapter (
    private val onItemClick: (Site) -> Unit
): ListAdapter<Site, SiteListAdapter.SiteViewHolder>(SiteDiff) {


    /**
     * ViewHolder class responsible to inject data into a single item of the view
     */
    class SiteViewHolder(private val siteItemBinding: SiteItemBinding, private val onItemClick: (Site) -> Unit) : RecyclerView.ViewHolder(siteItemBinding.root) {

        fun bind(site: Site) {
            siteItemBinding.tvSiteName.text = site.siteName
            siteItemBinding.tvCountryName.text = site.countryName

            siteItemBinding.root.setOnClickListener {
                onItemClick(site)
            }
        }

    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        return SiteViewHolder(
            SiteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    /**
     * Singleton to define differences between items in the list
     */
    object SiteDiff : DiffUtil.ItemCallback<Site>() {
        override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean {
            return oldItem.siteName == newItem.siteName && oldItem.countryName == newItem.countryName

        }
    }

}