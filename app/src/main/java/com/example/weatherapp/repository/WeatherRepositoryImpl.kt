package com.example.weatherapp.repository

import com.example.weatherapp.data.local.database.WeatherDao
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherApiService
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

class WeatherRepositoryImpl @Inject constructor(
  private val weatherApi: WeatherApiService,
  private val weatherDao: WeatherDao
) :WeatherRepository
{
  override suspend fun getCurrentWeatherApi(city:String): Response<CurrentWeather> {
    return weatherApi.getCurrentWeather(city)
  }

  override suspend fun getWeatherForecastApi(lat:Double,log:Double): Response<WeatherForecast> {
    return weatherApi.getWeatherForecast(lat = lat , log = log )
  }
}