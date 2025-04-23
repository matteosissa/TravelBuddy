package dadm.ndescot.travelbuddy.data.userdata.local

import kotlinx.coroutines.flow.Flow

interface LocalUserDataDataSource {

    fun getUserName(): Flow<String>
    suspend fun setUserName(userName: String)

    fun getUserId(): Flow<Int>
    suspend fun setUserId(userId: Int)

}