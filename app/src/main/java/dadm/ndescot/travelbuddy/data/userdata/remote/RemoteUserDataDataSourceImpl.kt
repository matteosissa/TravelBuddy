package dadm.ndescot.travelbuddy.data.userdata.remote

import android.util.Log
import connectors.default.DefaultConnector
import connectors.default.execute
import connectors.default.instance
import javax.inject.Inject

class RemoteUserDataDataSourceImpl @Inject constructor() : RemoteUserDataDataSource {

    private val connector = DefaultConnector.instance

    override suspend fun addNewUser(userName: String, phoneNumber: String): Int? {
        val response = connector.addNewUser.execute {
            username = userName
            this.phoneNumber = phoneNumber
        }
        return response.data.key.id
    }

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
