package dadm.ndescot.travelbuddy.domain.model

import java.time.LocalDateTime

data class GuideAnswer (
    val userId: Int,
    val userName: String,
    val tripId: Int?,
    val message: String,
    val dateTime: LocalDateTime,
    val guidePhoneNumber: String
)
