package com.example.weatherapp.data.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteWeather(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val feelLike: Double,
    val description: String,
    val temperature: Double,
    val cityName: String,
    val windSpeed: Double,
    val humidity :Int,
    val pressure: Int,
)
