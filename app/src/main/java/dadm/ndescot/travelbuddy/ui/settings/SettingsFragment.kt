package dadm.ndescot.travelbuddy.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.data.settings.SettingsPreferenceDataStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var dataStore: SettingsPreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = dataStore
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}