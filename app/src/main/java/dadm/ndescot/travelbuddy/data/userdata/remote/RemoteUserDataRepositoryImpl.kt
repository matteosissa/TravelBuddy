package dadm.ndescot.travelbuddy.data.userdata.remote

import javax.inject.Inject

/**
 * Implementation of the RemoteUserDataRepository interface.
 * This class is responsible for handling user data operations
 * by interacting with the RemoteUserDataDataSource.
 *
 * @param remoteUserDataDataSource The data source for remote user data operations.
 */
class RemoteUserDataRepositoryImpl @Inject constructor(
    private val remoteUserDataDataSource: RemoteUserDataDataSource
) : RemoteUserDataRepository {

    /**
     * Adds a new user to the remote data source.
     *
     * @param userName The name of the user.
     * @param phoneNumber The phone number of the user.
     * @return The ID of the newly added user, or null if the operation failed.
     */
    override suspend fun addNewUser(userName: String, phoneNumber: String): Int? {
        return remoteUserDataDataSource.addNewUser(userName, phoneNumber)
    }

    /**
     * Retrieves the user ID based on the provided user name and phone number.
     *
     * @param userName The name of the user.
     * @param phoneNumber The phone number of the user.
     * @return The ID of the user, or null if not found.
     */
    override suspend fun getUserId(userName: String, phoneNumber: String): Int? {
        return remoteUserDataDataSource.getUserId(userName, phoneNumber)
    }
}