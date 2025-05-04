package dadm.ndescot.travelbuddy.data.trip

import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dadm.ndescot.travelbuddy.domain.model.Trip
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(private val dataSource: TripDataSource): TripRepository {
    override suspend fun getTripsByUserId(id: Int): List<Trip> {
        return dataSource.getTripsByUserId(id)
    }

    override suspend fun createTrip(trip: Trip, userId: Int) : Boolean {
        return dataSource.createTrip(trip, userId)
    }

    override suspend fun getTripAnswers(tripId: Int): List<GuideAnswer> {
        return dataSource.getTripAnswers(tripId)
    }

}