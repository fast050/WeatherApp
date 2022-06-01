package com.example.weatherapp.data.model.weather

data class Main(
    val feels_like: Int,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)