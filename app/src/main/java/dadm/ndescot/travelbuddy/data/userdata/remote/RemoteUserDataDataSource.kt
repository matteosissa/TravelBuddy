package dadm.ndescot.travelbuddy.data.userdata.remote

/**
 * Interface that defines the methods to be implemented by the RemoteUserDataDataSource.
 */
interface RemoteUserDataDataSource {

    /**
     * Adds a new user to the remote data source.
     * @param userName The name of the user to be added.
     * @param phoneNumber The phone number of the user to be added.
     * @return The ID of the newly added user, or null if the operation failed.
     */
    suspend fun addNewUser(userName: String, phoneNumber: String): Int?

    /**
     * Retrieves the ID of a user from the remote data source.
     * @param userName The name of the user whose ID is to be retrieved.
     * @param phoneNumber The phone number of the user whose ID is to be retrieved.
     * @return The ID of the user, or null if the operation failed.
     */
    suspend fun getUserId(userName: String, phoneNumber: String): Int?
}