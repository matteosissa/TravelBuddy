package dadm.ndescot.travelbuddy.domain.model

import java.time.LocalDateTime

data class GuideReply (
    val userId: Int,
    val tripId: Int,
    val message: String,
    val dateTime: LocalDateTime
)
