package com.example.weatherapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteDetailViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

   fun currentWeatherDetail(id:Int) :LiveData<CurrentWeather> {
       return repository.getLocalCurrentWeather(id)
   }

}