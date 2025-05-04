package dadm.ndescot.travelbuddy.data.guide.domain

import connectors.default.AllTripsByLocationQuery
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip

// Function to map the GraphQL response to the Trip domain model
fun AllTripsByLocationQuery.Data.TripsItem.toDomain(): Trip {

    return Trip(
        id = id,
        username = user.name.orEmpty(),
        date = date.toDate(),
        locationCity = locationCity.orEmpty(),
        locationCountry = locationCountry.orEmpty(),
        activities = activities?.mapNotNull { activity ->
            activity.let {
                Activity.fromString(activity)
            }
        } ?: listOf(),
        durationDays = durationDays ?: 0,
        budget = budget ?: 0,
        description = description.orEmpty()
    )

}