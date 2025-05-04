package dadm.ndescot.travelbuddy.data.guide

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

    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return connector.allSitesAsGuide.execute { userId = id }.data.users.map { el ->
            el.guideSites.map { site ->
                site.toDomain()
            }
        }
            .flatten()
    }

    override suspend fun getTripsByLocation(
        siteName: String,
        countryName: String,
        userId: Int
    ): List<Trip> {
        return connector.allTripsByLocation.execute {
            this.siteName = siteName; this.countryName = countryName; this.userId = userId
        }.data.trips.map { el ->
            el.toDomain()
        }
    }

    override suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) {
        val result = connector.addNewGuideSite.execute {
            this.siteName = siteName; this.countryName = countryName; this.userId = userId
        }
        println("addGuideSite")
        println(result.data.siteGuide_upsert.siteName)
        println(result.data.siteGuide_upsert.countryName)
        println(result.data.siteGuideList_upsert.siteSiteName)

    }

    override suspend fun addAnswerToTrip(
        userId: Int,
        tripId: Int,
        message: String,
        instant: Instant
    ) {
        connector.addNewGuideReply.execute {
            this.userId = userId
            this.tripId = tripId
            this.text = message
            this.time = Timestamp(instant)
        }
    }
}