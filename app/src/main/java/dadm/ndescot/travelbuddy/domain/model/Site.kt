package dadm.ndescot.travelbuddy.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a travel site.
 *
 * @property siteName The name of the site.
 * @property countryName The name of the country where the site is located.
 */
@Parcelize
data class Site(
    var siteName: String,
    var countryName: String
) : Parcelable