package dadm.ndescot.quotationshake.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataSource: SettingsDataSource): SettingsRepository {
    override fun getUserName(): Flow<String> {
        return dataSource.getUserName()
    }

    override suspend fun getUserNameSnapshot(): String {
        return dataSource.getUserNameSnapshot()
    }

    override suspend fun setUserName(userName: String) {
        dataSource.setUserName(userName)
    }
}