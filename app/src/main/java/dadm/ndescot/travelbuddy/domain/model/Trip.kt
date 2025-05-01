package dadm.ndescot.travelbuddy.domain.model

import java.util.Date

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
