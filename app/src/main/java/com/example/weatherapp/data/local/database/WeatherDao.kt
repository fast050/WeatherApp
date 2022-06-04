package com.example.weatherapp.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast

@Dao
interface WeatherDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertCurrentWeather(currentWeather: CurrentWeather)

    @Insert(onConflict = IGNORE)
    suspend fun insertWeatherForecast(weatherForecast: WeatherForecast)

    @Delete
    suspend fun deleteCurrentWeather(currentWeather: CurrentWeather)

    @Delete
    suspend fun deleteWeatherForecast(weatherForecast: WeatherForecast)


    @Query("select * from CurrentWeather")
    fun getCurrentWeather(): LiveData<List<CurrentWeather>>

    @Query("select * from WeatherForecast")
    fun getWeatherForecast(): LiveData<List<WeatherForecast>>

    @Query("select * from CurrentWeather where id =:id")
    fun getCurrentWeatherById(id:Int):LiveData<CurrentWeather>
}