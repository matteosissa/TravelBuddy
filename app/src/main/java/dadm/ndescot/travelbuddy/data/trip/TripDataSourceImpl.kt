package dadm.ndescot.travelbuddy.data.trip

import com.google.firebase.Timestamp
import connectors.default.execute
import connectors.default.instance
import dadm.ndescot.travelbuddy.data.trip.model.toDomain
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import dadm.ndescot.travelbuddy.domain.model.Trip
import javax.inject.Inject

class TripDataSourceImpl @Inject constructor() : TripDataSource {

    val connector = connectors.default.DefaultConnector.instance

    override suspend fun getTripsByUserId(id: Int): List<Trip> {
        return connector.allTripsByUser.execute{ userId = id }.data.trips.map {
            el -> el.toDomain()
        }

    }

    override suspend fun createTrip(trip: Trip, userId: Int) {

        connector.addNewTrip.execute {
            this.userId = userId
            activities = trip.activities.map {it.asString()}
            budget = trip.budget
            date = Timestamp(trip.date)
            description = trip.description
            durationDays = trip.durationDays
            locationCity = trip.locationCity
            locationCountry = trip.locationCountry
        }

    }

    override suspend fun getTripAnswers(tripId: Int): List<GuideAnswer> {
        return connector.allAnswersToTrip.execute {
            this.tripId = tripId
        }.data.tripAnswers.map {
            el -> el.toDomain()
        }
    }

}