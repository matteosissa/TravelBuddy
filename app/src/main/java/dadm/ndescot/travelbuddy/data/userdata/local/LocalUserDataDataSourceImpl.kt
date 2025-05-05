package dadm.ndescot.travelbuddy.data.userdata.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * LocalUserDataDataSourceImpl is an implementation of the LocalUserDataDataSource interface.
 * It uses DataStore to persist user data locally.
 *
 * @param dataStore The DataStore instance used for local data storage.
 */
class LocalUserDataDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalUserDataDataSource {

    private object UserDataKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_ID = intPreferencesKey("user_id")
        val PHONE_NUMBER = stringPreferencesKey("phone_number")
    }

    /**
     * Retrieves the user name from DataStore.
     *
     * @return A Flow emitting the user name, or null if not set.
     */
    override fun getUserName(): Flow<String?> {
        return dataStore.data
            .map { data -> data[UserDataKeys.USER_NAME] }
            .catch { e ->
                Log.e("LocalUserDataDataSource", "Error reading user name", e)
                emit(null)
            }
    }

    /**
     * Sets the user name in DataStore.
     *
     * @param userName The user name to set.
     */
    override suspend fun setUserName(userName: String) {
        try {
            dataStore.edit { data ->
                data[UserDataKeys.USER_NAME] = userName
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error setting user name", e)
        }
    }

    /**
     * Retrieves the user ID from DataStore.
     *
     * @return A Flow emitting the user ID, or null if not set.
     */
    override fun getUserId(): Flow<Int?> {
        return dataStore.data
            .map { data -> data[UserDataKeys.USER_ID] }
            .catch { e ->
                Log.e("LocalUserDataDataSource", "Error reading user ID", e)
                emit(null)
            }
    }

    /**
     * Sets the user ID in DataStore.
     *
     * @param userId The user ID to set.
     */
    override suspend fun setUserId(userId: Int) {
        try {
            dataStore.edit { data ->
                data[UserDataKeys.USER_ID] = userId
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error setting user ID", e)
        }
    }

    /**
     * Deletes the user ID from DataStore.
     */
    override suspend fun deleteUserId() {
        try {
            dataStore.edit { data ->
                data.remove(UserDataKeys.USER_ID)
            }
        } catch (e: Exception) {
            Log.e("LocalUserDataDataSource", "Error deleting user ID", e)
        }
    }

    /**
     * Retrieves the phone number from DataStore.
     *
     * @return A Flow emitting the phone number, or null if not set.
     */
    override fun getPhoneNumber(): Flow<String?> {
        return dataStore.data
            .map { data -> data[UserDataKeys.PHONE_NUMBER] }
            .catch { e ->
                Log.e("LocalUserDataDataSource", "Error reading phone number", e)
                emit(null)
            }
    }

    /**
     * Sets the phone number in DataStore.
     *
     * @param phoneNumber The phone number to set.
     */
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