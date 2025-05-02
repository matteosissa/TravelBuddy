package dadm.ndescot.travelbuddy.data.userdata.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataRepositoryImpl @Inject constructor(private val dataSource: LocalUserDataDataSource) :
    LocalUserDataRepository {

    override fun getUserName(): Flow<String> {
        return dataSource.getUserName()
    }

    override suspend fun setUserName(userName: String) {
        dataSource.setUserName(userName)
    }

    override fun getUserId(): Flow<Int?> {
        return dataSource.getUserId()
    }

    override suspend fun setUserId(userId: Int) {
        dataSource.setUserId(userId)
    }

    override suspend fun deleteUserId() {
        dataSource.deleteUserId()
    }



}