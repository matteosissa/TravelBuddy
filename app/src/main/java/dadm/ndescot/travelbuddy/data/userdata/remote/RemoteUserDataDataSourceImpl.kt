package dadm.ndescot.travelbuddy.data.userdata.remote

import connectors.default.DefaultConnector
import connectors.default.execute
import connectors.default.instance
import javax.inject.Inject

class RemoteUserDataDataSourceImpl @Inject constructor() : RemoteUserDataDataSource {

    private val connector = DefaultConnector.instance

    override suspend fun addNewUser(userName: String, phoneNumber: String): Int {
        return connector.addNewUser.execute {
            username = userName
            this.phoneNumber = phoneNumber
        }.data.key.id
    }

    override suspend fun getUserId(userName: String, phoneNumber: String): Int? {
        val userId = connector.getUserId.execute {
            username = userName
            this.phoneNumber = phoneNumber
        }.data.users.firstOrNull()?.count
        println("The user id is $userId")
        return userId
    }

}