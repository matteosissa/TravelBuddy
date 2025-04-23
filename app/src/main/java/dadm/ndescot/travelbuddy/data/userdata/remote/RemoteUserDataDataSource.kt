package dadm.ndescot.travelbuddy.data.userdata.remote

interface RemoteUserDataDataSource {

    suspend fun addNewUser(userName: String): Int

}