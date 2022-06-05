package com.example.weatherapp.data.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.weatherapp.R
import com.example.weatherapp.ui.setting.TemperatureUnits

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingPreference @Inject constructor(@ApplicationContext context : Context){
    val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val preference = PreferenceManager.getDefaultSharedPreferences(context)

    val resources = context.resources

    fun getStoredTag(): String {
        return prefs.getString(resources.getString(R.string.temperature_unit_key), TemperatureUnits.Celsius.unit)?:""
    }
    fun setStoredTag(query: String) {
        prefs.edit().putString(SETTING_KEY, query).apply()
    }

    companion object{
       private const val SETTING_KEY = "SettingPreference_KEY"
    }
}

