package dadm.ndescot.travelbuddy.data.trip

import android.util.Log
import com.google.firebase.Timestamp
import connectors.default.execute
import connectors.default.instance
import dadm.ndescot.travelbuddy.data.trip.model.toDomain
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dadm.ndescot.travelbuddy.domain.model.Trip
import javax.inject.Inject

/**
 * Implementation of the TripDataSource interface.
 *
 * This class provides methods to interact with the trip data source, including retrieving trips
 * by user ID, creating new trips, and retrieving guide answers for a specific trip.
 */
class TripDataSourceImpl @Inject constructor() : TripDataSource {

    private val connector = connectors.default.DefaultConnector.instance

    /**
     * Retrieves a list of trips for a given user ID.
     *
     * @param id The ID of the user whose trips are to be retrieved.
     * @return A list of trips associated with the specified user ID.
     */
    override suspend fun getTripsByUserId(id: Int): List<Trip> {
        return connector.allTripsByUser.execute { userId = id }
                .data.trips.map { it.toDomain() }
    }

    /**
     * Creates a new trip for a given user ID.
     *
     * @param trip The trip to be created.
     * @param userId The ID of the user for whom the trip is to be created.
     * @return A boolean indicating whether the trip was successfully created.
     */
    override suspend fun createTrip(trip: Trip, userId: Int): Boolean {
        return try {
            val response = connector.addNewTrip.execute {
                this.userId = userId
                activities = trip.activities.map { it.asString() }
                budget = trip.budget
                date = Timestamp(trip.date)
                description = trip.description
                durationDays = trip.durationDays
                locationCity = trip.locationCity
                locationCountry = trip.locationCountry
            }
            true
        } catch (e: Exception) {
            Log.e("TripDataSource", "Error creating trip for user $userId", e)
            false
        }
    }

    /**
     * Retrieves a list of guide answers for a given trip ID.
     *
     * @param tripId The ID of the trip whose guide answers are to be retrieved.
     * @return A list of guide answers associated with the specified trip ID.
     */
    override suspend fun getTripAnswers(tripId: Int): List<GuideAnswer> {
        println("tripId: $tripId")
        val data = connector.allAnswersToTrip.execute { this.tripId = tripId }
                .data.tripAnswers.map { it.toDomain() }
        println(data)
        return data
    }

    override suspend fun deleteTrip(tripId: Int): Boolean {
        return try {
            connector.deleteTrip.execute { this.tripId = tripId }.data
            true
        } catch (e: Exception) {
            Log.e("TripDataSource", "Error deleting trip with id $tripId", e)
            false
        }
    }
}
