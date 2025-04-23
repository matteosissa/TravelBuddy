package dadm.ndescot.travelbuddy.data.userdata.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataDataSourceImpl  @Inject constructor(private val dataStore : DataStore<Preferences>) :
    LocalUserDataDataSource {

    private object UserDataKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_ID = intPreferencesKey("user_id")
    }

    override fun getUserName(): Flow<String> {
        return dataStore.data.map {
            data -> data[UserDataKeys.USER_NAME] ?: ""
        }
    }

    override suspend fun setUserName(userName: String) {
        dataStore.edit {
            data -> data[UserDataKeys.USER_NAME] = userName
        }
    }

    override fun getUserId(): Flow<Int> {
        return dataStore.data.map {
            data -> data[UserDataKeys.USER_ID] ?: -1        // Returns -1 in case does not find the userId
        }
    }

    override suspend fun setUserId(userId: Int) {
        dataStore.edit {
            data -> data[UserDataKeys.USER_ID] = userId
        }
    }
}