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

class GuideDataSourceImpl @Inject constructor() : GuideDataSource {

    private val connector = connectors.default.DefaultConnector.instance

    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return connector.allSitesAsGuide.execute {
            userId = id
        }.data.users.flatMap { user ->
                user.guideSites.map { site -> site.toDomain() }
            }
    }

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
}
