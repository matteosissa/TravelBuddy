package dadm.ndescot.travelbuddy.domain.model

/**
 * Enum class representing various activities that can be done during a trip.
 * Each activity is represented as a constant.
 *
 * @property HIKING Hiking activity
 * @property SWIMMING Swimming activity
 * @property CYCLING Cycling activity
 * @property CAMPING Camping activity
 * @property FISHING Fishing activity
 * @property SIGHTSEEING Sightseeing activity
 * @property SKIING Skiing activity
 * @property SNOWBOARDING Snowboarding activity
 * @property SURFING Surfing activity
 * @property ROCK_CLIMBING Rock climbing activity
 * @property KAYAKING Kayaking activity
 * @property PADDLE_BOARDING Paddle boarding activity
 * @property JET_SKIING Jet skiing activity
 * @property PARAGLIDING Paragliding activity
 * @property SKYDIVING Skydiving activity
 * @property ART_AND_CRAFTS Art and crafts activity
 * @property COOKING Cooking activity
 * @property PHOTOGRAPHY Photography activity
 * @property MUSIC Music activity
 * @property DANCE Dance activity
 */
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

    /**
     * Converts the enum constant to a string representation.
     *
     * @return The name of the enum constant as a string.
     */
    fun asString(): String = this.name
}