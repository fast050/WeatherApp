package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.favorite.FavoriteWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.ui.setting.TemperatureUnits
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.util.concurrent.TimeUnit

interface WeatherRepository {

//
   fun observeCurrentWeather(): Flow<CurrentWeather>

   suspend fun requestCurrentWeather(cityName:String)

   suspend fun requestWeatherForecast(lat: Double, lon: Double)

   fun observeWeatherForecast():Flow<WeatherForecast>


   /*
   /   favorite fragment
    */

   suspend fun saveFavoriteWeather(favoriteWeather: FavoriteWeather)

   suspend fun deleteFavoriteWeather(favoriteWeather: FavoriteWeather)

   fun observeAllFavoriteWeather() :LiveData<List<FavoriteWeather>>

   fun observeFavoriteWeatherById(Id:Int) : LiveData<FavoriteWeather>

}