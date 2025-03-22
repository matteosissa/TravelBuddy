package dadm.ndescot.quotationshake.data.settings

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
    private val USERNAME = stringPreferencesKey("prefs_username")
    private val LANGUAGE = stringPreferencesKey("prefs_language")


    override fun getUserName(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[USERNAME].orEmpty()
        }
    }

    override suspend fun getUserNameSnapshot(): String {
        return dataStore.data.first()[USERNAME] ?: ""
    }

    override suspend fun setUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = userName
        }
    }

    override fun getLanguage(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[LANGUAGE].orEmpty()
        }
    }

    override suspend fun getLanguageSnapshot(): String {
        return dataStore.data.first()[LANGUAGE] ?: "en"
    }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }
}