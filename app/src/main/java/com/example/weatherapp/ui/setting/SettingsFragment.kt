package com.example.weatherapp.ui.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.preference.SwitchPreferenceCompat
import com.example.weatherapp.R
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


    lateinit var alarmManager: AlarmManager

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


        val preference =
            preferenceManager.findPreference<SwitchPreferenceCompat>(getString(R.string.weather_notification_key)) as SwitchPreferenceCompat

        preference.setOnPreferenceChangeListener { _, newValue ->


            Toast.makeText(context,newValue.toString(),Toast.LENGTH_SHORT).show()
            when (newValue as Boolean) {

                true -> startAlarm()
                false -> cancelAlarm()
            }

            true
        }

    }


    private fun startAlarm() {
        // Quote in Morning at 06:00:00 AM
        val calendar = Calendar.getInstance()

        calendar[Calendar.HOUR_OF_DAY] = 7
        calendar[Calendar.MINUTE] = 46
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        val cur = Calendar.getInstance()

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1)
        }

        val myIntent = Intent(context, MainFragment::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager = context!!.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun cancelAlarm() {
        alarmManager = (context?.getSystemService(ALARM_SERVICE) as AlarmManager?)!!
        val intent = Intent(context, MainFragment::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)
        alarmManager.cancel(pendingIntent)

    }

    companion object{
        const val ALARM1_ID = 10000
    }
}


