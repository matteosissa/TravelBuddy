package dadm.ndescot.travelbuddy.data.guide

import com.google.firebase.Timestamp
import connectors.default.execute
import connectors.default.instance
import dadm.ndescot.travelbuddy.data.guide.domain.toDomain
import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class GuideDataSourceImpl @Inject constructor() : GuideDataSource {

    private val connector = connectors.default.DefaultConnector.instance

    override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
        return connector.allSitesAsGuide.execute{ userId = id }.data.users.map {
            el -> el.guideSites.map {
                site -> site.toDomain()
        }
        }.flatten()     // API returns a list of lists (one list per user), but the parameter is the userID which is unique
    }

    override suspend fun getTripsByLocation(siteName: String, countryName: String): List<Trip> {
        return connector.allTripsByLocation.execute{ this.siteName = siteName; this.countryName = countryName }.data.trips.map {
            el -> el.toDomain()
        }
    }

    override suspend fun addGuideSite(siteName: String, countryName: String, userId: Int) {
        val result = connector.addNewGuideSite.execute{ this.siteName = siteName; this.countryName = countryName; this.userId = userId }
        println("addGuideSite")
        println(result.data.siteGuide_upsert.siteName)
        println(result.data.siteGuide_upsert.countryName)
        println(result.data.siteGuideList_upsert.siteSiteName)

    }

    override suspend fun addAnswerToTrip(userId: Int, tripId: Int, message: String, dateTime: LocalDateTime) {
        connector.addNewGuideReply.execute{
            this.userId = userId
            this.tripId = tripId
            this.text = message
            this.time = Timestamp(dateTime.toInstant(ZoneOffset.UTC))
        }
    }


}