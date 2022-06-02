package com.example.weatherapp.repository

import com.example.weatherapp.data.local.database.WeatherDao
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherApiService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
  private val weatherApi: WeatherApiService,
  private val weatherDao: WeatherDao
) :WeatherRepository
{
}