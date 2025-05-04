package dadm.ndescot.travelbuddy.data.userdata.local

import kotlinx.coroutines.flow.Flow

/**
 * Interface for local data source to manage user data.
 */
interface LocalUserDataDataSource {

    /**
     * Get the user name as a Flow.
     */
    fun getUserName(): Flow<String>

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

    /**
     * Get the phone number as a Flow.
     */
    fun getPhoneNumber(): Flow<String>

    /**
     * Set the phone number.
     */
    suspend fun setPhoneNumber(phoneNumber: String)
}