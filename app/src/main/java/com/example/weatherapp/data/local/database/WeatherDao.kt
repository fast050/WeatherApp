package com.example.weatherapp.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.data.model.weather.Weather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast

interface WeatherDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertUser(Weather: Weather)

    @Insert(onConflict = IGNORE)
    suspend fun insertUser(Weather: WeatherForecast)

    @Update
    suspend fun updateUser(Weather: Weather)

    @Update
    suspend fun updateUser(Weather: WeatherForecast)

    @Delete
    suspend fun removeUser(Weather: Weather)

    @Delete
    suspend fun removeUser(Weather: WeatherForecast)


    @Query("select * from Weather")
    fun getCurrentWeather(userId:Int): LiveData<List<Weather>>

    @Query("select * from WeatherForecast")
    fun getWeatherForecast(userId:Int): LiveData<List<WeatherForecast>>
}