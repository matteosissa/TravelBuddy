package dadm.ndescot.travelbuddy.data.trip.model

import connectors.default.AllAnswersToTripQuery
import dadm.ndescot.travelbuddy.domain.model.GuideAnswer
import java.time.LocalDateTime
import java.time.ZoneId

fun AllAnswersToTripQuery.Data.TripAnswersItem.toDomain() : GuideAnswer {
    return GuideAnswer(
        userId = this.user.id,
        userName = user.name ?: "Anonymous",
        tripId = null,
        message = this.text,
        dateTime = LocalDateTime.ofInstant(this.time.toInstant(), ZoneId.systemDefault()),
        guidePhoneNumber = this.user.phoneNumber ?: ""
    )
}