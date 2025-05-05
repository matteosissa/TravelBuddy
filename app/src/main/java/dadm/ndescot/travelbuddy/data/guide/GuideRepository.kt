package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import java.time.Instant
import java.time.LocalDateTime

/**
 * Interface for the repository of the guide feature.
 *
 * This interface defines the methods that the guide repository will use to interact with the data source.
 */
interface GuideRepository {

    /**
     * Fetches the guide sites for a given user ID.
     *
     * @param id The ID of the user.
     * @return A list of [Site] objects representing the guide sites.
     */
    suspend fun getGuideSitesByUserId(id: Int): List<Site>

    suspend fun getTripsByLocation(siteName: String, countryName: String, userId: Int) : List<Trip>

    /**
     * Fetches the trips for a given location and user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     * @return A list of [Trip] objects representing the trips.
     */
    suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) : Boolean

    /**
     * Adds a new guide site for a given user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     */
    suspend fun addAnswerToTrip(userId: Int, tripId: Int, message: String, instant: Instant) : Boolean
}