package dadm.ndescot.travelbuddy.domain.model

import java.time.LocalDateTime

/**
 * Data class representing a guide answer.
 *
 * @property userId The ID of the user who answered.
 * @property userName The name of the user who answered.
 * @property tripId The ID of the trip associated with the answer, or null if not applicable.
 * @property message The content of the answer.
 * @property dateTime The date and time when the answer was created.
 * @property guidePhoneNumber The phone number of the guide associated with the answer.
 */
data class GuideAnswer(
    val userId: Int,
    val userName: String,
    val tripId: Int?,
    val message: String,
    val dateTime: LocalDateTime,
    val guidePhoneNumber: String
)
