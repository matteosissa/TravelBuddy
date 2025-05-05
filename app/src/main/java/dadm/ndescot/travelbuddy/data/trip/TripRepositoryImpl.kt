package dadm.ndescot.travelbuddy.data.trip

import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dadm.ndescot.travelbuddy.domain.model.Trip
import javax.inject.Inject

/**
 * Implementation of the TripRepository interface.
 *
 * This class provides methods to interact with the trip data source, including retrieving trips
 * by user ID, creating new trips, and retrieving guide answers for a specific trip.
 */
class TripRepositoryImpl @Inject constructor(private val dataSource: TripDataSource): TripRepository {
    /**
     * Retrieves a list of trips for a given user ID.
     *
     * @param id The ID of the user whose trips are to be retrieved.
     * @return A list of trips associated with the specified user ID.
     */
    override suspend fun getTripsByUserId(id: Int): List<Trip> {
        return dataSource.getTripsByUserId(id)
    }

    /**
     * Creates a new trip for a given user ID.
     *
     * @param trip The trip to be created.
     * @param userId The ID of the user for whom the trip is to be created.
     * @return A boolean indicating whether the trip was successfully created.
     */
    override suspend fun createTrip(trip: Trip, userId: Int) : Boolean {
        return dataSource.createTrip(trip, userId)
    }

    /**
     * Retrieves a list of guide answers for a given trip ID.
     *
     * @param tripId The ID of the trip whose guide answers are to be retrieved.
     * @return A list of guide answers associated with the specified trip ID.
     */
    override suspend fun getTripAnswers(tripId: Int): List<GuideAnswer> {
        return dataSource.getTripAnswers(tripId)
    }
}