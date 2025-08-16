package cl.uchile.postgrado.mobile.shoppinglist.model

import android.content.Context
import androidx.core.content.edit

class UserSettingsSharePreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        "UserSettings",
        Context.MODE_PRIVATE
    )

    fun getUserSetting(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun setUserSetting(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }
}