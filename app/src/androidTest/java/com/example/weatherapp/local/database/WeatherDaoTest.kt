package com.example.weatherapp.local.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.weatherapp.data.local.database.WeatherDao
import com.example.weatherapp.data.local.database.WeatherDatabase
import com.example.weatherapp.data.model.currentweather.Coord
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.currentweather.Main
import com.example.weatherapp.data.model.currentweather.Wind
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.data.model.weatherforecast.FeelsLike
import com.example.weatherapp.data.model.weatherforecast.Temp
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.local.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@SmallTest
@RunWith(AndroidJUnit4::class)
class WeatherDaoTest
{

    private lateinit var database: WeatherDatabase
    private lateinit var dao: WeatherDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp(){

         database = Room
             .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext()
                       ,WeatherDatabase::class.java).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertCurrentWeather()= runBlocking{

        val currentWeather = CurrentWeather(id=292223, coord= Coord(lat=25.2582, lon=55.3047),
            weatherProperties= Main(feels_like=310.72, humidity=49, pressure=1004, temp=306.92, temp_max=308.31,
                temp_min=306.29), cityName="Dubai", timezone=14400, weatherDescription= listOf(), wind= Wind(speed=2.57))

        dao.insertCurrentWeather(currentWeather)

        val currentWeatherItem = dao.getCurrentWeather().getOrAwaitValue()

        assertThat(currentWeatherItem).isEqualTo(currentWeather)

    }

    @Test
    fun deleteCurrentWeather()= runBlocking{

        val currentWeather = CurrentWeather(id=292223, coord= Coord(lat=25.2582, lon=55.3047),
            weatherProperties= Main(feels_like=310.72, humidity=49, pressure=1004, temp=306.92, temp_max=308.31,
                temp_min=306.29), cityName="Dubai", timezone=14400, weatherDescription= listOf(), wind= Wind(speed=2.57))

        dao.insertCurrentWeather(currentWeather)
        dao.deleteCurrentWeather(currentWeather)

        val currentWeatherItem = dao.getCurrentWeather().getOrAwaitValue()

        assertThat(currentWeatherItem).isNull()
    }

    @Test
    fun insertWeatherForecast()= runBlocking{

        val weatherForecast = WeatherForecast(id=22,daily= listOf(),lat=300.00,lon=256.65)

        dao.insertWeatherForecast(weatherForecast)

        val weatherForecastItem = dao.getWeatherForecast().getOrAwaitValue()

        assertThat(weatherForecast).isEqualTo(weatherForecastItem)

    }

    @Test
    fun deleteWeatherForecast()= runBlocking{

        val weatherForecast = WeatherForecast(id=22,daily= listOf(),lat=300.00,lon=256.65)

        dao.insertWeatherForecast(weatherForecast)
        dao.deleteWeatherForecast(weatherForecast)

        val weatherForecastItem = dao.getWeatherForecast().getOrAwaitValue()

        assertThat(weatherForecastItem).isNull()
    }


}