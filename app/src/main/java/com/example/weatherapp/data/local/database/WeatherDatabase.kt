package com.example.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.model.weather.Weather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast


//@Database(entities = [Weather::class, WeatherForecast::class], version = 1)
//abstract class WeatherDatabase: RoomDatabase() {
//
//    abstract fun userDao(): WeatherDao
//
//    companion object {
//        const val DATABASE_NAME = "WeatherDatabase_db"
//    }
//
//}