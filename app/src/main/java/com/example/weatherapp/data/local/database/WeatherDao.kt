package com.example.weatherapp.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.favorite.FavoriteWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import kotlinx.coroutines.flow.Flow

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

    @Insert(onConflict = IGNORE)
    suspend fun insertFavorite(favoriteWeather: FavoriteWeather)

    @Delete
    suspend fun deleteFavorite(favoriteWeather: FavoriteWeather)


    //main
    @Query("select * from CurrentWeather")
    fun getCurrentWeather(): Flow<CurrentWeather?>

    @Query("select * from WeatherForecast")
    fun getWeatherForecast(): Flow<WeatherForecast?>


    //favorite
    @Query("select * from FavoriteWeather")
    fun getAllFavoriteWeather():LiveData<List<FavoriteWeather>>

    @Query("select * from FavoriteWeather where id=:id")
    fun getFavoriteWeatherById(id: Int):LiveData<FavoriteWeather>
}