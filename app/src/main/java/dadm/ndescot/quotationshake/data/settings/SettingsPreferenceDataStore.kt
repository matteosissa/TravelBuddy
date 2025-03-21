package dadm.ndescot.quotationshake.data.settings

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsPreferenceDataStore @Inject constructor(private val repository: SettingsRepository) : PreferenceDataStore(){
    private val KEY_USER_NAME = "prefs_username"
    private val KEY_LANGUAGE = "prefs_language"

    override fun putString(key: String, value: String?) {
        if (value == null) return
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                KEY_USER_NAME -> repository.setUserName(value)
                // KEY_LANGUAGE -> repository.setUserLanguage(value)
            }
        }
    }

    override fun getString(key: String, defValue: String?): String? {
        return runBlocking(Dispatchers.IO) {
            when (key) {
                KEY_USER_NAME -> repository.getUserNameSnapshot()
                // KEY_LANGUAGE -> repository.getUserLanguageSnapshot()
                else -> defValue
            }
        }
    }
}