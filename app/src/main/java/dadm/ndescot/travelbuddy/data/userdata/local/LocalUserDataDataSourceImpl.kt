package dadm.ndescot.travelbuddy.data.userdata.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalUserDataDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalUserDataDataSource {

    private object UserDataKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_ID = intPreferencesKey("user_id")
        val PHONE_NUMBER = stringPreferencesKey("phone_number")
    }

    override fun getUserName(): Flow<String?> {
        return dataStore.data
            .map { data -> data[UserDataKeys.USER_NAME] }
            .catch { e ->
                Log.e("LocalUserDataDataSource", "Error reading user name", e)
                emit(null)
            }
    }

    override suspend fun setUserName(userName: String) {
        try {
            dataStore.edit { data ->
                data[UserDataKeys.USER_NAME] = userName
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error setting user name", e)
        }
    }

    override fun getUserId(): Flow<Int?> {
        return dataStore.data
            .map { data -> data[UserDataKeys.USER_ID] }
            .catch { e ->
                Log.e("LocalUserDataDataSource", "Error reading user ID", e)
                emit(null)
            }
    }

    override suspend fun setUserId(userId: Int) {
        try {
            dataStore.edit { data ->
                data[UserDataKeys.USER_ID] = userId
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error setting user ID", e)
        }
    }

    override suspend fun deleteUserId() {
        try {
            dataStore.edit { data ->
                data.remove(UserDataKeys.USER_ID)
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error deleting user ID", e)
        }
    }

    override fun getPhoneNumber(): Flow<String?> {
        return dataStore.data
            .map { data -> data[UserDataKeys.PHONE_NUMBER] }
            .catch { e ->
                Log.e("LocalUserDataDataSource", "Error reading phone number", e)
                emit(null)
            }
    }

    override suspend fun setPhoneNumber(phoneNumber: String) {
        try {
            dataStore.edit { data ->
                data[UserDataKeys.PHONE_NUMBER] = phoneNumber
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error setting phone number", e)
        }
    }
}
