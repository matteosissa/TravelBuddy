package dadm.ndescot.travelbuddy.domain.model

enum class Activity {
    HIKING,
    SWIMMING,
    CYCLING,
    CAMPING,
    FISHING,
    SIGHTSEEING,
    SKIING,
    SNOWBOARDING,
    SURFING,
    ROCK_CLIMBING,
    KAYAKING,
    PADDLE_BOARDING,
    JET_SKIING,
    PARAGLIDING,
    SKYDIVING,
    ART_AND_CRAFTS,
    COOKING,
    PHOTOGRAPHY,
    MUSIC,
    DANCE;

    companion object {
        fun fromString(value: String): Activity? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }

    fun asString(): String = this.name


}