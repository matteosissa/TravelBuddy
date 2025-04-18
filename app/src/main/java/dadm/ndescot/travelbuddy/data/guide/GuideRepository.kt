package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.guide.Site

interface GuideRepository {

    suspend fun getGuideSitesByUserId(id: Int) : List<Site>

    suspend fun getTripsByLocation(siteName: String, countryName: String) : List<Trip>

}