package dadm.ndescot.travelbuddy.data.trip

import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip
import java.util.Date
import javax.inject.Inject

class TripDataSourceImpl @Inject constructor() : TripDataSource {
    override suspend fun getTrips(): List<Trip> {
        // Todo: Replace with a real data source (database)
        return listOf(
            Trip(
                id = 1,
                username = "Matteo",
                date = Date(123, 5, 15), // June 15, 2023
                locationCity = "Valencia",
                locationCountry = "Spain",
                activities = listOf(
                    Activity.SIGHTSEEING,
                    Activity.SWIMMING,
                    Activity.PHOTOGRAPHY
                ),
                durationDays = 7,
                budget = 1200,
                description = "Description of the trip"
            ),
            Trip(
                id = 2,
                username = "John Doe",
                date = Date(123, 7, 10), // August 10, 2023
                locationCity = "Paris",
                locationCountry = "France",
                activities = listOf(
                    Activity.SIGHTSEEING,
                    Activity.ART_AND_CRAFTS,
                    Activity.PHOTOGRAPHY,
                    Activity.COOKING
                ),
                durationDays = 8,
                budget = 1800,
                description = "Description of the trip"
            ),
            Trip(
                id = 3,
                username = "Jane Smith",
                date = Date(123, 11, 20), // December 20, 2023
                locationCity = "Honolulu",
                locationCountry = "USA",
                activities = listOf(
                    Activity.SWIMMING,
                    Activity.SURFING,
                    Activity.HIKING,
                    Activity.PADDLE_BOARDING
                ),
                durationDays = 10,
                budget = 3500,
                description = "Description of the trip"
            )
        )
    }
}