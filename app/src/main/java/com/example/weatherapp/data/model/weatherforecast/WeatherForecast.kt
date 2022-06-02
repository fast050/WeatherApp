package com.example.weatherapp.data.model.weatherforecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherForecast(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
)

data class Daily(
    val clouds: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pressure: Int,
    val temp: Temp,
    val weather: List<WeatherX>,
    val wind_speed: Double
)

data class FeelsLike(
    val day: Double,
)

data class WeatherX(
    val main: String
)

data class Temp(
    val day: Double,
    val max: Double,
    val min: Double,
)



