package dadm.ndescot.quotationshake.domain.model

import java.util.Date

data class Trip(val id: Int,
                val date: Date,
                val locationCity: String,
                val locationCountry: String,
                val activities: List<Activity>,
                val durationDays: Int,
                val budget: Int)
