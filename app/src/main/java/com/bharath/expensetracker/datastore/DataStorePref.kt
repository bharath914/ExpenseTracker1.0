package com.bharath.expensetracker.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SettingsPref")

class DataStorePref(context: Context) {
    private object PreferencesKey {
        val colorKey = intPreferencesKey(name = "ColorKey")
        val calendarStyle = booleanPreferencesKey(name = "CalendarStyle")
        val amoledTheme = booleanPreferencesKey(name = "Amoled")
        val disableColorBlock = booleanPreferencesKey(name = "ColorBlock")
    }

    private val dataStore = context.dataStore


    suspend fun saveColor(color: Int) {
        dataStore.edit { pref ->
            pref[PreferencesKey.colorKey] = color

        }
    }

    suspend fun saveCalendarStyle(bool: Boolean) {
        dataStore.edit {
            it[PreferencesKey.calendarStyle] = bool
        }
    }

    suspend fun amoledTheme(bool: Boolean) {
        dataStore.edit {
            it[PreferencesKey.amoledTheme] = bool
        }
    }

    suspend fun colorBlock(bool: Boolean) {
        dataStore.edit {
            it[PreferencesKey.disableColorBlock] = bool
        }
    }

    fun readCalendarStyle(): Flow<Boolean> {
        return dataStore.data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }

        }.map {
            val style = it[PreferencesKey.calendarStyle] ?: false
            style
        }
    }

    fun getColorBlockStatus(): Flow<Boolean> {
        return dataStore.data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
            .map {
                val color = it[PreferencesKey.disableColorBlock] ?: false
                color
            }
    }


    fun getAmoledStatus(): Flow<Boolean> {
        return dataStore.data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            val amoled = it[PreferencesKey.amoledTheme] ?: false
            amoled
        }
    }


    fun readColor(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())

                } else {
                    throw exception
                }


            }.map {
                val colorkey = it[PreferencesKey.colorKey] ?: 0
                colorkey
            }
    }
}