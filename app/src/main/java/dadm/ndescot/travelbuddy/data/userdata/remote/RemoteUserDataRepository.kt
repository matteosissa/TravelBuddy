package dadm.ndescot.travelbuddy.data.userdata.remote

/**
 * Interface for the remote data source of user data.
 * This interface defines the methods to be implemented by the remote data source.
 * It is used to abstract the implementation details and provide a clean API for the repository.
 */
interface RemoteUserDataRepository {

    /**
     * Adds a new user to the remote data source.
     *
     * @param userName The name of the user to be added.
     * @param phoneNumber The phone number of the user to be added.
     * @return The ID of the newly added user, or null if the operation failed.
     */
    suspend fun addNewUser(userName: String, phoneNumber: String): Int?

    /**
     * Retrieves the user ID for a given user name and phone number.
     *
     * @param userName The name of the user whose ID is to be retrieved.
     * @param phoneNumber The phone number of the user whose ID is to be retrieved.
     * @return The ID of the user, or null if the user was not found.
     */
    suspend fun getUserId(userName: String, phoneNumber: String): Int?
}