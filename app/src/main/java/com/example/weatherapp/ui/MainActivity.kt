package com.example.weatherapp.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.alarm.WeatherAlert
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        val intent = Intent(this, WeatherAlert::class.java)
//        val alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//
//// Set the alarm to start at 8:30 a.m.
//
//// Set the alarm to start at 8:30 a.m.
//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar[Calendar.HOUR_OF_DAY] = 8
//        calendar[Calendar.MINUTE] = 30
//
//// setRepeating() lets you specify a precise custom interval--in this case,
//        // 20 minutes.
//
//// setRepeating() lets you specify a precise custom interval--in this case,
//// 20 minutes.
//        alarmMgr.setRepeating(
//            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, (
//                    1000 * 60 * 20).toLong(), alarmIntent
//        )
        binding.apply {


            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.mainFragment, R.id.favoriteFragment
                )
            )

            val navHostFragment =
                supportFragmentManager.findFragmentById(navHostFragmentMain.id) as NavHostFragment
            navController = navHostFragment.navController

            setupActionBarWithNavController(navController, appBarConfiguration)

            bottomNavigationViewMain.setupWithNavController(navController)

        }


    }


    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}