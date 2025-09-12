package cl.uchile.postgrado.mobile.shoppinglist.viewmodel

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
    var theme: String by mutableStateOf("system")
    var language: String by mutableStateOf("es-cl")

    val THEME_KEY = stringPreferencesKey("theme")
    val LANGUAGE_KEY = stringPreferencesKey("language")

    val Context.dataStore by preferencesDataStore("UserSettings")

    fun getSettings(context: Context) {
        runBlocking {
            val dataStore = context.dataStore
            val preferences = dataStore.data.first()
            if (preferences[THEME_KEY] != null)
                theme = preferences[THEME_KEY].toString()
            if (preferences[LANGUAGE_KEY] != null)
                language = preferences[LANGUAGE_KEY].toString()
        }
    }

    fun saveSettings(context: Context) {
        runBlocking {
            val dataStore = context.dataStore
            dataStore.edit { preferences ->
                preferences[THEME_KEY] = theme
                preferences[LANGUAGE_KEY] = language
            }
        }
    }

    fun saveThemeSetting(context: Context) {
        runBlocking {
            val dataStore = context.dataStore
            dataStore.edit { preferences ->
                preferences[THEME_KEY] = theme
            }
        }
    }
}