package com.example.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast


@Database(entities = [CurrentWeather::class,WeatherForecast::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun userDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "WeatherDatabase_db"
    }

}