package com.example.weatherapp.repository

import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import retrofit2.Response

interface WeatherRepository {

   suspend fun getCurrentWeatherApi(city:String) :Response<CurrentWeather>

   suspend fun getWeatherForecastApi(lat:Double,log:Double) :Response<WeatherForecast>

}