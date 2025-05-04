package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import java.time.Instant
import java.time.LocalDateTime
import javax.inject.Inject

class GuideRepositoryImpl @Inject constructor(private val dataSource: GuideDataSource) :
    GuideRepository {

    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return dataSource.getGuideSitesByUserId(id)
    }

    override suspend fun getTripsByLocation(siteName: String, countryName: String, userId: Int): List<Trip> {
        return dataSource.getTripsByLocation(siteName, countryName, userId)
    }

    override suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) : Boolean {
        return dataSource.addGuideSite(siteName, countryName, userId)
    }

    override suspend fun addAnswerToTrip(userId: Int, tripId: Int, message: String, instant: Instant) : Boolean {
        return dataSource.addAnswerToTrip(userId, tripId, message, instant)
    }

}