package dadm.ndescot.travelbuddy.data.guide

import android.util.Log
import com.google.firebase.Timestamp
import connectors.default.execute
import connectors.default.instance
import dadm.ndescot.travelbuddy.data.guide.domain.toDomain
import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.Site
import java.time.Instant
import javax.inject.Inject

/**
 * Implementation of the [GuideDataSource] interface.
 *
 * This class is responsible for fetching and manipulating data related to the guide feature.
 * It uses the default connector to interact with the data source.
 */
class GuideDataSourceImpl @Inject constructor() : GuideDataSource {

    private val connector = connectors.default.DefaultConnector.instance

    /**
     * Fetches the guide sites for a given user ID.
     *
     * @param id The ID of the user.
     * @return A list of [Site] objects representing the guide sites.
     */
    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return connector.allSitesAsGuide.execute {
            userId = id
        }.data.users.flatMap { user ->
                user.guideSites.map { site -> site.toDomain() }
            }
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
        return connector.allTripsByLocation.execute {
            this.siteName = siteName
            this.countryName = countryName
            this.userId = userId
        }.data.trips.map { it.toDomain() }

    }

    /**
     * Adds a new guide site for a given user ID.
     *
     * @param siteName The name of the site.
     * @param countryName The name of the country.
     * @param userId The ID of the user.
     */
    override suspend fun addGuideSite(siteName: String, countryName: String, userId: Int): Boolean {
        return try {
            val result = connector.addNewGuideSite.execute {
                this.siteName = siteName
                this.countryName = countryName
                this.userId = userId
            }
            Log.d(
                "GuideDataSource",
                "Guide site added: ${result.data.siteGuide_upsert.siteName}, ${result.data.siteGuide_upsert.countryName}"
            )
            true
        } catch (e: Exception) {
            Log.e("GuideDataSource", "Error adding guide site", e)
            false
        }
    }

    /**
     * Adds a new answer to a trip for a given user ID.
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
    ): Boolean {
        return try {
            connector.addNewGuideReply.execute {
                this.userId = userId
                this.tripId = tripId
                this.text = message
                this.time = Timestamp(instant)
            }
            true
        } catch (e: Exception) {
            Log.e("GuideDataSource", "Error adding answer to trip", e)
            false
        }
    }

    override suspend fun deleteSite(userId: Int, siteName: String, siteCountry: String): Boolean {
        return try {
            connector.deleteSite.execute {
                this.userId = userId
                this.siteName = siteName
                this.siteCountry = siteCountry
            }
            true
        } catch (e: Exception) {
            Log.e("GuideDataSource", "Error deleting site", e)
            false
        }
    }

}