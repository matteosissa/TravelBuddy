package dadm.ndescot.travelbuddy.data.trip

import dadm.ndescot.travelbuddy.domain.model.Trip

interface TripRepository {
    suspend fun getTripsByUserId(id: Int): List<Trip>

    suspend fun createTrip(trip: Trip, userId: Int)

}