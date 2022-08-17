package com.protecc.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class StoreLoggedIn(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserLogin")
        val USER_LOGIN_KEY = stringPreferencesKey(("user_login"))
    }

    val getUser: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_LOGIN_KEY] ?: ""
        }

    suspend fun isUser(login : String) {
        context.dataStore.edit { preferences ->
            preferences[USER_LOGIN_KEY] = login
        }
    }
}