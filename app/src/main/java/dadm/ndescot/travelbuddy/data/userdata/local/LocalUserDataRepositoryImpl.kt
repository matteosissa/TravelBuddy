package dadm.ndescot.travelbuddy.data.userdata.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of the LocalUserDataRepository interface.
 *
 * @param dataSource The data source for local user data.
 */
class LocalUserDataRepositoryImpl @Inject constructor(private val dataSource: LocalUserDataDataSource) :
    LocalUserDataRepository {

    /**
     * Retrieves the user name from the local data source.
     *
     * @return A Flow emitting the user name as a String.
     */
    override fun getUserName(): Flow<String?> {
        return dataSource.getUserName()
    }

    /**
     * Sets the user name in the local data source.
     *
     * @param userName The user name to set.
     */
    override suspend fun setUserName(userName: String) {
        dataSource.setUserName(userName)
    }

    /**
     * Deletes the user name from the local data source.
     */
    override fun getUserId(): Flow<Int?> {
        return dataSource.getUserId()
    }

    /**
     * Sets the user ID in the local data source.
     *
     * @param userId The user ID to set.
     */
    override suspend fun setUserId(userId: Int) {
        dataSource.setUserId(userId)
    }

    /**
     * Deletes the user ID from the local data source.
     */
    override suspend fun deleteUserId() {
        dataSource.deleteUserId()
    }

    /**
     * Retrieves the user email from the local data source.
     *
     * @return A Flow emitting the user email as a String.
     */
    override fun getPhoneNumber(): Flow<String?> {
        return dataSource.getPhoneNumber()
    }

    /**
     * Sets the user email in the local data source.
     *
     * @param phoneNumber The user email to set.
     */
    override suspend fun setPhoneNumber(phoneNumber: String) {
        dataSource.setPhoneNumber(phoneNumber)
    }
}