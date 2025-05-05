package dadm.ndescot.travelbuddy.data.trip

import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dadm.ndescot.travelbuddy.domain.model.Trip

/**
 * Interface for the Trip repository.
 *
 * This interface defines the methods for retrieving and creating trips.
 */
interface TripRepository {
    /**
     * Retrieves a list of trips for a given user ID.
     *
     * @param id The ID of the user whose trips are to be retrieved.
     * @return A list of trips associated with the specified user ID.
     */
    suspend fun getTripsByUserId(id: Int): List<Trip>

    /**
     * Creates a new trip for a given user ID.
     *
     * @param trip The trip to be created.
     * @param userId The ID of the user for whom the trip is to be created.
     * @return A boolean indicating whether the trip was successfully created.
     */
    suspend fun createTrip(trip: Trip, userId: Int) : Boolean

    /**
     * Retrieves a list of guide answers for a given trip ID.
     *
     * @param tripId The ID of the trip whose guide answers are to be retrieved.
     * @return A list of guide answers associated with the specified trip ID.
     */
    suspend fun getTripAnswers(tripId: Int): List<GuideAnswer>

    /**
     * Deletes a trip with the specified trip ID.
     *
     * @param tripId The ID of the trip to be deleted.
     */
    suspend fun deleteTrip(tripId: Int): Boolean
}