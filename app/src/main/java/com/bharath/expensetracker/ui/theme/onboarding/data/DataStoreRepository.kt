package com.bharath.expensetracker.ui.theme.onboarding.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name="on_boarding_pref")

class DataStoreRepository(context:Context) {

    private val dataStore=context.dataStore
    private object PreferncesKey{
        val onBoardingKey= booleanPreferencesKey(name = "on_boarding_completed")
    }
    suspend fun saveOnBoardingState(completed:Boolean){
        dataStore.edit {
            it[PreferncesKey.onBoardingKey]=completed
        }
    }
    fun readOnBoardingState():kotlinx.coroutines.flow.Flow<Boolean>{
        return dataStore.data
            .catch {exception->
                if (exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }

            }.map {preferences->
                val onBoardingState=preferences[PreferncesKey.onBoardingKey]?:false
                onBoardingState

            }
    }

}