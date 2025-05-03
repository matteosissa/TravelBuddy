package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import java.time.LocalDateTime
import javax.inject.Inject

class GuideRepositoryImpl @Inject constructor(private val dataSource: GuideDataSource) :
    GuideRepository {

    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return dataSource.getGuideSitesByUserId(id)
    }

    override suspend fun getTripsByLocation(siteName: String, countryName: String): List<Trip> {
        return dataSource.getTripsByLocation(siteName, countryName)
    }

    override suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) {
        dataSource.addGuideSite(siteName, countryName, userId)
    }

    override suspend fun addAnswerToTrip(
        userId: Int,
        tripId: Int,
        message: String,
        dateTime: LocalDateTime
    ) {
        dataSource.addAnswerToTrip(userId, tripId, message, dateTime)
    }

}