package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import java.time.Instant
import java.time.LocalDateTime

interface GuideRepository {

    suspend fun getGuideSitesByUserId(id: Int) : List<Site>

    suspend fun getTripsByLocation(siteName: String, countryName: String, userId: Int) : List<Trip>

    suspend fun addGuideSite(siteName: String, countryName: String, userId: Int)

    suspend fun addAnswerToTrip(userId: Int, tripId: Int, message: String, instant: Instant)

}