package com.example.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.example.weatherapp.data.local.database.WeatherDao
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.favorite.FavoriteWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.data.remote.WeatherApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApiService,
    private val weatherDao: WeatherDao,
    private val scope: CoroutineScope
) : WeatherRepository {


    private val currentWeather: Flow<CurrentWeather> get() = _currentWeather.asFlow()
    private var _currentWeather = MutableLiveData<CurrentWeather>()

    private val weatherForecast: Flow<WeatherForecast> get() = _weatherForecast.asFlow()
    private var _weatherForecast = MutableLiveData<WeatherForecast>()


    //offline cache handle
    override suspend fun requestCurrentWeather(
        cityName: String
        //, units: String
    ) {


        //check the database if null no data we save data , if not null then we dont save data we just return the date in the database
        val localCurrentWeather = scope.async {
            weatherDao.getCurrentWeather().first()
        }.await()


        //if local data is null we make a api call and save it in the database and then return data in the database
        if (localCurrentWeather == null) {
            Log.d("TAG", " item: item is null")
            Log.d("TAG", " flowCurrentWeather: flow is null with $localCurrentWeather")


            try {
                val weatherApiCall = weatherApi.getCurrentWeather(cityName)

                if (weatherApiCall.isSuccessful) {
                    weatherApiCall.body()?.let {

                        weatherDao.insertCurrentWeather(it)
                    }
                }
            } catch (e: Exception) {
                //do nothing
            }

        }
        //if local data is not null we return the data at the database
        else {
            Log.d("TAG", " item: item is  not null with name ${localCurrentWeather.cityName}")
            Log.d("TAG", " flowCurrentWeather: flow is not null with $localCurrentWeather")

            try {
                val weatherApiCall = weatherApi.getCurrentWeather(cityName)

                if (weatherApiCall.isSuccessful) {
                    weatherApiCall.body()?.let {

                        if (it.id != localCurrentWeather.id) {
                            weatherDao.deleteCurrentWeather(localCurrentWeather)
                            weatherDao.insertCurrentWeather(it)
                        }
                    }
                }

            } catch (e: Exception) {
                //do nothing
            }


        }

        // database as source of our data
        _currentWeather.value = weatherDao.getCurrentWeather().first()
    }

    override suspend fun requestWeatherForecast(lat: Double, lon: Double) {

        val localForecast = scope.async {
            weatherDao.getWeatherForecast().first()
        }.await()




        if (localForecast == null) {

            try {

                //get other api
                val forecastApiCall = weatherApi.getWeatherForecast(
                    lat = lat, lon = lon
                )

                if (forecastApiCall.isSuccessful) {

                    forecastApiCall.body()?.let { weatherForecast ->
                        weatherDao.insertWeatherForecast(weatherForecast)
                    }

                }
            } catch (e: Exception) {
              //do nothing
            }
        } else {

            try {

                //get other api
                val forecastApiCall = weatherApi.getWeatherForecast(
                    lat = lat, lon = lon
                )

                if (forecastApiCall.isSuccessful) {

                    forecastApiCall.body()?.let { weatherForecast ->

                       if ( localForecast.id != weatherForecast.id){
                           weatherDao.deleteWeatherForecast(localForecast)
                           weatherDao.insertWeatherForecast(weatherForecast)
                       }
                    }

                }
            } catch (e: Exception) {
                //do nothing
            }

        }


        _weatherForecast.value = weatherDao.getWeatherForecast().first()

    }


    override fun observeCurrentWeather(): Flow<CurrentWeather> {
        return currentWeather
    }

    override fun observeWeatherForecast(): Flow<WeatherForecast> {
        return weatherForecast
    }


    override suspend fun saveFavoriteWeather(favoriteWeather: FavoriteWeather) {
        weatherDao.insertFavorite(favoriteWeather)
    }

    override suspend fun deleteFavoriteWeather(favoriteWeather: FavoriteWeather) {
        weatherDao.deleteFavorite(favoriteWeather)
    }

    override fun observeAllFavoriteWeather(): LiveData<List<FavoriteWeather>> {
        return weatherDao.getAllFavoriteWeather()
    }

    override fun observeFavoriteWeatherById(id: Int): LiveData<FavoriteWeather> {
        return weatherDao.getFavoriteWeatherById(id)
    }
}