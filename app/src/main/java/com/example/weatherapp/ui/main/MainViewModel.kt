package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {


    val observeCurrentWeather: LiveData<CurrentWeather>
        get() = _currentWeather
    private val _currentWeather = MutableLiveData<CurrentWeather>()

    val observeWeatherForecast: LiveData<List<Daily>>
        get() = _weatherForecast
    private val _weatherForecast = MutableLiveData<List<Daily>>()


    fun getCurrentWeatherAndForecast(city: String) = viewModelScope.launch {
        val responseCurrentWeather =
            weatherRepository.getCurrentWeatherApi(city).body() ?: return@launch

        _currentWeather.value = responseCurrentWeather

        val responseWeatherForecast = weatherRepository.getWeatherForecastApi(
            lat = responseCurrentWeather.coord.lat,
            log = responseCurrentWeather.coord.lon
        ).body() ?: return@launch

        _weatherForecast.value = responseWeatherForecast.daily

    }
}