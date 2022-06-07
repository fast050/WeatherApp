package com.example.weatherapp.ui.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.weatherapp.R
import com.example.weatherapp.alarm.WeatherAlert
import com.example.weatherapp.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


//Fahrenheit use units=imperial
//Celsius use units=metric
enum class TemperatureUnits(val unit: String) {
    Fahrenheit("imperial"),
    Celsius("metric")
}

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {


    lateinit var alarmMgr: AlarmManager

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


        val preference =
            preferenceManager.findPreference<SwitchPreferenceCompat>(getString(R.string.weather_notification_key)) as SwitchPreferenceCompat

        preference.setOnPreferenceChangeListener { _, newValue ->


            Toast.makeText(context, newValue.toString(), Toast.LENGTH_SHORT).show()
            when (newValue as Boolean) {

                true -> startAlarm()
                false -> cancelAlarm()
            }

            true
        }

    }


    private fun startAlarm() {
        alarmMgr = requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WeatherAlert::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

       // Set the alarm to start at 6:00 a.m.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 6
        calendar[Calendar.MINUTE] = 0



        // setRepeating() lets you specify a precise custom interval--in this case,
        // 1 day.
        alarmMgr.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, alarmIntent
        )
    }

    private fun cancelAlarm() {
        alarmMgr = (requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager?)!!
        val intent = Intent(context, MainFragment::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)
        alarmMgr.cancel(pendingIntent)

    }

    companion object {
        const val ALARM1_ID = 10000
    }
}




