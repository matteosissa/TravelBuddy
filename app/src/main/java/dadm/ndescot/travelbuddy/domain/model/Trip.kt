package dadm.ndescot.travelbuddy.domain.model

import java.util.Date

/**
 * Data class representing a trip.
 *
 * @property id Unique identifier for the trip.
 * @property username The username of the user who created the trip.
 * @property date The date of the trip.
 * @property locationCity The city where the trip takes place.
 * @property locationCountry The country where the trip takes place.
 * @property activities A list of activities planned for the trip.
 * @property durationDays The duration of the trip in days.
 * @property budget The budget allocated for the trip.
 * @property description A description of the trip.
 */
data class Trip(
    val id: Int,
    val username: String,
    val date: Date,
    val locationCity: String,
    val locationCountry: String,
    val activities: List<Activity>,
    val durationDays: Int,
    val budget: Int,
    val description: String
)