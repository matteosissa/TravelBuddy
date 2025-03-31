package dadm.ndescot.quotationshake.data.trip

import dadm.ndescot.quotationshake.domain.model.Trip

interface TripRepository {
    suspend fun getTrips(): List<Trip>

}