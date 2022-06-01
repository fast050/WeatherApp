package com.example.weatherapp.data.model.weatherforecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherForecast(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val current: Current,
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)