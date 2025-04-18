package dadm.ndescot.travelbuddy.data.trip

import connectors.default.AllTripsByUserQuery
import connectors.default.execute
import connectors.default.instance
import dadm.ndescot.travelbuddy.data.trip.model.toDomain
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip
import java.util.Date
import javax.inject.Inject

class TripDataSourceImpl @Inject constructor() : TripDataSource {

    val connector = connectors.default.DefaultConnector.instance

    override suspend fun getTripsByUserId(id: Int): List<Trip> {
        return connector.allTripsByUser.execute{ userId = id }.data.trips.map {
            el -> el.toDomain()
        }

    }
}