package dadm.ndescot.travelbuddy.data.userdata.remote

interface RemoteUserDataRepository {

    suspend fun addNewUser(userName: String, phoneNumber: String): Int

}