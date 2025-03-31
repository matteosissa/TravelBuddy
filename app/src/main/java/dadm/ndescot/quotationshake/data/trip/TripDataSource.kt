package dadm.ndescot.quotationshake.data.trip

import dadm.ndescot.quotationshake.domain.model.Trip

interface TripDataSource {
    suspend fun getTrips(): List<Trip>
}