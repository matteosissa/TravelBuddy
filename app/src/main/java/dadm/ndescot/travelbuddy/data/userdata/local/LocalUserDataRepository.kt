package dadm.ndescot.travelbuddy.data.userdata.local

import kotlinx.coroutines.flow.Flow

/**
 * Interface for the local user data repository.
 *
 * This interface defines methods to get and set user data such as user name, user ID, and phone number.
 */
interface LocalUserDataRepository {

    /**
     * Gets the user name.
     *
     * @return A [Flow] emitting the user name as a [String?].
     */
    fun getUserName(): Flow<String?>

    /**
     * Sets the user name.
     *
     * @param userName The user name to set.
     */
    suspend fun setUserName(userName: String)

    /**
     * Gets the user ID.
     *
     * @return A [Flow] emitting the user ID as an [Int?].
     */
    fun getUserId(): Flow<Int?>

    /**
     * Sets the user ID.
     *
     * @param userId The user ID to set.
     */
    suspend fun setUserId(userId: Int)

    /**
     * Deletes the user ID.
     */
    suspend fun deleteUserId()

    /**
     * Gets the phone number.
     *
     * @return A [Flow] emitting the phone number as a [String?].
     */
    fun getPhoneNumber(): Flow<String?>

    /**
     * Sets the phone number.
     *
     * @param phoneNumber The phone number to set.
     */
    suspend fun setPhoneNumber(phoneNumber: String)
}