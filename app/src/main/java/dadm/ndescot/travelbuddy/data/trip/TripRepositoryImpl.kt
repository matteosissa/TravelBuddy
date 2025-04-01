package dadm.ndescot.travelbuddy.data.trip

import dadm.ndescot.travelbuddy.domain.model.Trip
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(private val dataSource: TripDataSource): TripRepository {
    override suspend fun getTrips(): List<Trip> {
        return dataSource.getTrips()
    }
}