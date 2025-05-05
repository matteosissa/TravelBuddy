package dadm.ndescot.travelbuddy.data.userdata.remote

import android.util.Log
import connectors.default.DefaultConnector
import connectors.default.execute
import connectors.default.instance
import javax.inject.Inject

/**
 * Implementation of the RemoteUserDataDataSource interface.
 *
 * This class is responsible for interacting with the remote data source to perform user-related operations.
 *
 * @property connector The connector instance used to communicate with the remote data source.
 */
class RemoteUserDataDataSourceImpl @Inject constructor() : RemoteUserDataDataSource {

    private val connector = DefaultConnector.instance

    /**
     * Adds a new user to the remote data source.
     *
     * @param userName The name of the user to be added.
     * @param phoneNumber The phone number of the user to be added.
     * @return The ID of the newly added user, or null if the operation failed.
     */
    override suspend fun addNewUser(userName: String, phoneNumber: String): Int {
        val response = connector.addNewUser.execute {
            username = userName
            this.phoneNumber = phoneNumber
        }
        return response.data.key.id
    }

    /**
     * Retrieves the user ID for a given username and phone number from the remote data source.
     *
     * @param userName The name of the user whose ID is to be retrieved.
     * @param phoneNumber The phone number of the user whose ID is to be retrieved.
     * @return The ID of the user, or null if the operation failed.
     */
    override suspend fun getUserId(userName: String, phoneNumber: String): Int? {
        val response = connector.getUserId.execute {
            username = userName
            this.phoneNumber = phoneNumber
        }
        val userId = response.data.users.firstOrNull()?.count
        Log.d("RemoteUserDataDataSource", "The user id is $userId")
        return userId
    }
}
