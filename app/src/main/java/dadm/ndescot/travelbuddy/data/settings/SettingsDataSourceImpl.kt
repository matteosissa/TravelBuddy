package dadm.ndescot.travelbuddy.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(private val dataStore :DataStore<Preferences>): SettingsDataSource {
    private val username = stringPreferencesKey("prefs_username")
    private val language = stringPreferencesKey("prefs_language")


    override fun getUserName(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[username].orEmpty()
        }
    }

    override suspend fun getUserNameSnapshot(): String {
        return dataStore.data.first()[username] ?: ""
    }

    override suspend fun setUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[username] = userName
        }
    }

    override fun getLanguage(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[language].orEmpty()
        }
    }

    override suspend fun getLanguageSnapshot(): String {
        return dataStore.data.first()[language] ?: "en"
    }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[this.language] = language
        }
    }
}