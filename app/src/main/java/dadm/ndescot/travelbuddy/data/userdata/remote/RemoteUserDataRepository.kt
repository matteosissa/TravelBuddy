package dadm.ndescot.travelbuddy.data.userdata.remote

interface RemoteUserDataRepository {

    suspend fun addNewUser(userName: String, phoneNumber: String): Int

    suspend fun getUserId(userName: String, phoneNumber: String): Int?

}