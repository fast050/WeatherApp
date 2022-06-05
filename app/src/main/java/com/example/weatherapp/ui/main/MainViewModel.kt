package com.example.weatherapp.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.preference.SettingPreference
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.setting.TemperatureUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {


    init {
        getCurrentWeatherAndForecast("dubai")
    }

    val observeCurrentWeather: LiveData<CurrentWeather>
        get() = _currentWeather
    private val _currentWeather = MutableLiveData<CurrentWeather>()

    val observeWeatherForecast: LiveData<List<Daily>>
        get() = _weatherForecast
    private val _weatherForecast = MutableLiveData<List<Daily>>()


    fun getCurrentWeatherAndForecast(city: String, units: String? = TemperatureUnits.Celsius.unit) =
        viewModelScope.launch {
            val responseCurrentWeather =
                units?.let { weatherRepository.getCurrentWeatherApi(city, it).body() } ?: return@launch

            _currentWeather.value = responseCurrentWeather

            val responseWeatherForecast = weatherRepository.getWeatherForecastApi(
                lat = responseCurrentWeather.coord.lat,
                log = responseCurrentWeather.coord.lon,
                units = units
            ).body() ?: return@launch

            val dailyList = responseWeatherForecast.daily

            //filter the dailyList from first day item index = 0
            val filterDailyList = dailyList.filterIndexed { index, _ ->
                index != 0
            }

            _weatherForecast.value = filterDailyList
        }

    fun setFavorite(
        isFavorite: Boolean,
        currentWeather: CurrentWeather?,
    ) = viewModelScope.launch {

        if (currentWeather == null)
            return@launch

        when (isFavorite) {
            true -> {
                weatherRepository.saveCurrentWeather(currentWeather)
            }
            false -> {
                weatherRepository.deleteCurrentWeather(currentWeather)
            }
        }

    }

}