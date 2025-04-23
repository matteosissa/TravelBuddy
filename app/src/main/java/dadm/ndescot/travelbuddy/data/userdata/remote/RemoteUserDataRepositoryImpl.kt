package dadm.ndescot.travelbuddy.data.userdata.remote

import javax.inject.Inject

class RemoteUserDataRepositoryImpl @Inject constructor(
    private val remoteUserDataDataSource: RemoteUserDataDataSource
): RemoteUserDataRepository {

    override suspend fun addNewUser(userName: String): Int {
        return remoteUserDataDataSource.addNewUser(userName)
    }

}