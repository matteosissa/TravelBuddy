package dadm.ndescot.travelbuddy.data.trip.model

import connectors.default.AllTripsByUserQuery
import dadm.ndescot.travelbuddy.domain.model.Activity
import dadm.ndescot.travelbuddy.domain.model.Trip

/**
 * Converts a [AllTripsByUserQuery.Data.TripsItem] to a [Trip]
 */
fun AllTripsByUserQuery.Data.TripsItem.toDomain(): Trip {
    return Trip(
        id = id,
        username = user.name.orEmpty(),
        date = date.toDate(),
        locationCity = locationCity.orEmpty(),
        locationCountry = locationCountry.orEmpty(),
        activities = activities?.mapNotNull{
           activity -> activity.let{
               Activity.fromString(activity)
        }} ?: listOf(),
        durationDays = durationDays ?: 0,
        budget = budget ?: 0,
        description = description.orEmpty()
    )
}