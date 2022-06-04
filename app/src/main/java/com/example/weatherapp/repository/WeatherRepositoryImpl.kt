package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
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
) : WeatherRepository {
    override suspend fun getCurrentWeatherApi(city: String): Response<CurrentWeather> {
        return weatherApi.getCurrentWeather(city)
    }

    override suspend fun getWeatherForecastApi(
        lat: Double,
        log: Double
    ): Response<WeatherForecast> {
        return weatherApi.getWeatherForecast(lat = lat, log = log)
    }

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) {
        weatherDao.insertCurrentWeather(currentWeather)
    }

    override suspend fun saveWeatherForecast(weatherForecast: WeatherForecast) {
        weatherDao.insertWeatherForecast(weatherForecast)
    }

    override suspend fun deleteCurrentWeather(currentWeather: CurrentWeather) {
        weatherDao.deleteCurrentWeather(currentWeather)
    }

    override suspend fun deleteWeatherForecast(weatherForecast: WeatherForecast) {
        weatherDao.deleteWeatherForecast(weatherForecast)
    }

    override fun observeLocalCurrentWeather(): LiveData<List<CurrentWeather>> {
        return weatherDao.getCurrentWeather()
    }

    override fun observeLocalWeatherForecast(): LiveData<List<WeatherForecast>> {
        return weatherDao.getWeatherForecast()
    }

    override fun getLocalCurrentWeather(id :Int): LiveData<CurrentWeather> {
        return weatherDao.getCurrentWeatherById(id = id)
    }
}