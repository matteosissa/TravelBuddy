package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import java.time.Instant

/**
 * Interface for the data source of the guide feature.
 *
 * This interface defines the methods that the guide repository will use to interact with the data source.
 */
interface GuideDataSource {
    /**
     * Fetches the guide sites for a given user ID.
     *
     * @param id The ID of the user.
     * @return A list of [Site] objects representing the guide sites.
     */
    suspend fun getGuideSitesByUserId(id: Int): List<Site>

    /**
     * Fetches the trips for a given location and user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     * @return A list of [Trip] objects representing the trips.
     */
    suspend fun getTripsByLocation(siteName: String, countryName: String, userId: Int): List<Trip>

    /**
     * Adds a new guide site for a given user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     */
    suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) : Boolean

    /**
     * Adds an answer to a trip for a given user ID.
     *
     * @param userId The ID of the user.
     * @param tripId The ID of the trip.
     * @param message The message to be added.
     * @param instant The instant when the message was created.
     */
    suspend fun addAnswerToTrip(userId: Int, tripId: Int, message: String, instant: Instant) : Boolean

    suspend fun deleteSite(userId: Int, siteName: String, siteCountry: String) : Boolean

}