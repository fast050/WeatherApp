package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import retrofit2.Response

interface WeatherRepository {

   suspend fun getCurrentWeatherApi(city:String) :Response<CurrentWeather>

   suspend fun getWeatherForecastApi(lat:Double,log:Double) :Response<WeatherForecast>

   suspend fun saveCurrentWeather(currentWeather: CurrentWeather)

   suspend fun saveWeatherForecast(weatherForecast: WeatherForecast)

   suspend fun deleteCurrentWeather(currentWeather: CurrentWeather)

   suspend fun deleteWeatherForecast(weatherForecast: WeatherForecast)

   fun observeLocalCurrentWeather():LiveData<List<CurrentWeather>>

   fun observeLocalWeatherForecast():LiveData<List<WeatherForecast>>

   fun getLocalCurrentWeather(id :Int) :LiveData<CurrentWeather>

}