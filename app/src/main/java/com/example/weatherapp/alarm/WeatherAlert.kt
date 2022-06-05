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
import javax.inject.Inject


class WeatherAlert : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("TAG", "onReceive: it happen")

        if (context != null) {
            notify(context, "weather today", "Check weather App to know what is the temperature")
        }
    }


}