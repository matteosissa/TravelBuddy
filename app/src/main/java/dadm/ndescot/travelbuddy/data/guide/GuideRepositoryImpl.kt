package dadm.ndescot.travelbuddy.data.guide

import dadm.ndescot.travelbuddy.domain.model.Trip
import dadm.ndescot.travelbuddy.domain.model.guide.Site
import javax.inject.Inject

class GuideRepositoryImpl @Inject constructor(private val dataSource: GuideDataSource)
    : GuideRepository {

        override suspend fun getGuideSitesByUserId(id: Int): List<Site> {
            return dataSource.getGuideSitesByUserId(id)
        }

        override suspend fun getTripsByLocation(siteName: String, countryName: String): List<Trip> {
            return dataSource.getTripsByLocation(siteName, countryName)
        }

}