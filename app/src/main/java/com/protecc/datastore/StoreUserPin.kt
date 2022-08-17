package com.protecc.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class StoreUserPin(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserPin")
        val USER_PIN_KEY = stringPreferencesKey(("user_pin"))
    }

    val getPin: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PIN_KEY] ?: ""
        }

    suspend fun savePin(pin : String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PIN_KEY] = pin
        }
    }
}