package dadm.ndescot.travelbuddy.data.userdata.remote

import javax.inject.Inject

class RemoteUserDataRepositoryImpl @Inject constructor(
    private val remoteUserDataDataSource: RemoteUserDataDataSource
): RemoteUserDataRepository {

    override suspend fun addNewUser(userName: String, phoneNumber: String): Int {
        return remoteUserDataDataSource.addNewUser(userName, phoneNumber)
    }

    override suspend fun getUserId(userName: String, phoneNumber: String): Int? {
        return remoteUserDataDataSource.getUserId(userName, phoneNumber)
    }

}