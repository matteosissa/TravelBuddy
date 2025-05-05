package dadm.ndescot.travelbuddy.data.userdata.local

import kotlinx.coroutines.flow.Flow

/**
 * Interface for local data source to manage user data.
 */
interface LocalUserDataDataSource {

    fun getUserName(): Flow<String?>
    /**
     * Get the user name as a Flow.
     */

    /**
     * Set the user name.
     */
    suspend fun setUserName(userName: String)

    /**
     * Get the user ID as a Flow.
     */
    fun getUserId(): Flow<Int?>

    /**
     * Set the user ID.
     */
    suspend fun setUserId(userId: Int)

    /**
     * Delete the user ID.
     */
    suspend fun deleteUserId()

    fun getPhoneNumber(): Flow<String?>
    /**
     * Get the phone number as a Flow.
     */

    /**
     * Set the phone number.
     */
    suspend fun setPhoneNumber(phoneNumber: String)
}