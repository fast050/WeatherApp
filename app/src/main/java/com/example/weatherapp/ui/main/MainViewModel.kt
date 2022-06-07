package com.example.weatherapp.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.preference.SettingPreference
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.favorite.FavoriteWeather
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.setting.TemperatureUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {


    val observeCurrentWeather: LiveData<CurrentWeather>
        get() = _currentWeather
    private val _currentWeather = MutableLiveData<CurrentWeather>()


    val observeWeatherForecast: LiveData<List<Daily>>
        get() = _weatherForecast
    private val _weatherForecast = MutableLiveData<List<Daily>>()


    fun getCurrentWeatherAndForecast(
        city: String
        //       , units: String? = TemperatureUnits.Celsius.unit
    ) =
        viewModelScope.launch {


            weatherRepository.requestCurrentWeather(city)

            val weatherData = weatherRepository.observeCurrentWeather().first()

            _currentWeather.value = weatherData

            weatherData?.let {
                weatherRepository.requestWeatherForecast(lat = it.coord.lat,lon= it.coord.lon)

                val forecastData = weatherRepository.observeWeatherForecast().first()
                //filter the dailyList from first day item index = 0
                val filterDailyList = forecastData.daily.filterIndexed { index, _ ->
                    index != 0
                }

                _weatherForecast.value=filterDailyList
            }


        }

    fun setFavorite(
        isFavorite: Boolean,
        currentWeather: CurrentWeather?,
    ) = viewModelScope.launch {

        if (currentWeather == null)
            return@launch

        val favorite = FavoriteWeather(
            id = currentWeather.id,
            feelLike = currentWeather.weatherProperties.feels_like,
            description = currentWeather.weatherDescription[0].main,
            temperature = currentWeather.weatherProperties.temp,
            cityName = currentWeather.cityName,
            humidity = currentWeather.weatherProperties.humidity,
            windSpeed = currentWeather.wind.speed,
            pressure = currentWeather.weatherProperties.pressure
        )

        when (isFavorite) {
            true -> {
                weatherRepository.saveFavoriteWeather(favorite)
            }
            false -> {
                weatherRepository.deleteFavoriteWeather(favorite)
            }
        }

    }

}