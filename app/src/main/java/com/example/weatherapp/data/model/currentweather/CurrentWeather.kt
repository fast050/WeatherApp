package com.example.weatherapp.data.model.currentweather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


//the main class
@Entity
data class CurrentWeather(
    @PrimaryKey
    val id: Int,
    val coord: Coord,
    @SerializedName("main")
    val weatherProperties: Main,
    @SerializedName("name")
    val cityName: String,
    val timezone: Int,
    @SerializedName( "weather")
    val weatherDescription: List<WeatherX>,
    val wind: Wind
)

data class WeatherX(
    val main: String
)

data class Wind(
    val speed: Double
)
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Coord(
    val lat: Double,
    val lon: Double
)
