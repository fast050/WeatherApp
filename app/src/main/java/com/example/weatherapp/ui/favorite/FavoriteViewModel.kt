package com.example.weatherapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    val observeFavoriteList: LiveData<List<CurrentWeather>> =
        weatherRepository.observeLocalCurrentWeather()

}