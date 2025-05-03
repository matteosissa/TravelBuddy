package dadm.ndescot.travelbuddy.domain.model.guide

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Parcelize to make data serializable to pass it between fragments
@Parcelize
data class Site (
    var siteName: String,
    var countryName: String
) : Parcelable
