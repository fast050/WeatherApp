package com.example.weatherapp.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.weatherapp.ui.main.MainFragment
import com.example.weatherapp.ui.utils.notify
import java.util.*

class WeatherAlert : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("TAG", "onReceive: it happen")

        if (context != null) {
            notify(context, "weather today")
        }

        if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {

            // Quote in Morning 6:00 Am
            val calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 6);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            val cur = Calendar.getInstance();

            if (cur.after(calendar)) {
                calendar.add(Calendar.DATE, 1);
            }

            val myIntent = Intent(context, MainFragment::class.java)

            val pendingIntent = PendingIntent.getBroadcast(
                context, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT
            );
            val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            );

        }
    }

    companion object{
        val ALARM1_ID = 10000;
    }
}