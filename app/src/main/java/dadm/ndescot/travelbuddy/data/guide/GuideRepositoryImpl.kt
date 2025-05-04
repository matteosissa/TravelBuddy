package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import java.time.Instant
import javax.inject.Inject

/**
 * Implementation of the [GuideRepository] interface.
 *
 * This class is responsible for fetching and manipulating data related to the guide feature.
 * It uses the [GuideDataSource] to interact with the data source.
 */
class GuideRepositoryImpl @Inject constructor(private val dataSource: GuideDataSource) :
    GuideRepository {

    /**
     * Fetches the guide sites for a given user ID.
     *
     * @param id The ID of the user.
     * @return A list of [Site] objects representing the guide sites.
     */
    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return dataSource.getGuideSitesByUserId(id)
    }

    /**
     * Fetches the trips for a given location and user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     * @return A list of [Trip] objects representing the trips.
     */
    override suspend fun getTripsByLocation(
        siteName: String,
        countryName: String,
        userId: Int
    ): List<Trip> {
        return dataSource.getTripsByLocation(siteName, countryName, userId)
    }

    /**
     * Adds a new guide site for a given user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     */
    override suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) {
        dataSource.addGuideSite(siteName, countryName, userId)
    }

    /**
     * Adds an answer to a trip for a given user ID, trip ID, message, and timestamp.
     *
     * @param userId The ID of the user.
     * @param tripId The ID of the trip.
     * @param message The message to be added.
     * @param instant The timestamp of the message.
     */
    override suspend fun addAnswerToTrip(
        userId: Int,
        tripId: Int,
        message: String,
        instant: Instant
    ) {
        dataSource.addAnswerToTrip(userId, tripId, message, instant)
    }

}