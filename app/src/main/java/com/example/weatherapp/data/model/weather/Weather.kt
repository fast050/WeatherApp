package com.example.weatherapp.data.model.weather

import androidx.room.Entity
import androidx.room.PrimaryKey


//the main class
@Entity
data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Double,
    val coord: Coord,
    val dt: Double,
    @PrimaryKey
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)