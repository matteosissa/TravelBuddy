package dadm.ndescot.travelbuddy.data.settings

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsPreferenceDataStore @Inject constructor(private val repository: SettingsRepository) : PreferenceDataStore(){
    private val keyUserName = "prefs_username"
    private val keyLanguage = "prefs_language"

    override fun putString(key: String, value: String?) {
        if (value == null) return
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                keyUserName -> repository.setUserName(value)
                keyLanguage -> repository.setLanguage(value)
            }
        }
    }

    override fun getString(key: String, defValue: String?): String? {
        return runBlocking(Dispatchers.IO) {
            when (key) {
                keyUserName -> repository.getUserNameSnapshot()
                keyLanguage -> repository.getLanguageSnapshot()
                else -> defValue
            }
        }
    }
}