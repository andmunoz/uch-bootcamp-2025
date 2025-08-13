package cl.uchile.postgrado.mobile.shoppinglist.model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserSettingsViewModel: ViewModel() {
    var theme: String? by mutableStateOf("")
    fun setTheme(theme: String) {
        this.theme = theme
    }

    var language: String? by mutableStateOf("")

    fun setLanguage(language: String) {
        this.language = language
    }

    val THEME_KEY = stringPreferencesKey("theme")
    val LANGUAGE_KEY = stringPreferencesKey("language")

    val Context.dataStore by preferencesDataStore("UserSettings")

    fun getSettings(context: Context) {
        runBlocking {
            val dataStore = context.dataStore
            val preferences = dataStore.data.first()
            theme = preferences[THEME_KEY]
            language = preferences[LANGUAGE_KEY]
        }
    }

    fun saveSettings(context: Context) {
        runBlocking {
            val dataStore = context.dataStore
            dataStore.edit { preferences ->
                preferences[THEME_KEY] = theme.toString()
                preferences[LANGUAGE_KEY] = language.toString()
            }
        }
    }

}