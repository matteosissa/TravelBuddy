package dadm.ndescot.quotationshake.data.trip

import dadm.ndescot.quotationshake.domain.model.Trip
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(private val dataSource: TripDataSource): TripRepository {
    override suspend fun getTrips(): List<Trip> {
        return dataSource.getTrips()
    }
}