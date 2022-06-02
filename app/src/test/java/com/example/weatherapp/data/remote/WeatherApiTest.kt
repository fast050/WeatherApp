package com.example.weatherapp.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class WeatherApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val server = MockWebServer()
    private lateinit var service: WeatherApiService
    private lateinit var mockedResponse: String

    @Before
    fun init() {

        server.start(8000)

        var BASE_URL = server.url("/").toString()

        val okHttpClient = OkHttpClient
            .Builder()
            .build()
         service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(WeatherApiService::class.java)

    }

    @Test
    fun weatherForecastApiCallSuccess() {
        mockedResponse = MockResponseFileReader("WeatherApi/success_weather_forecast.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { service.getWeatherForecast(30.00,30.00) }


        assertEquals("the api request not successful",response.code() , 200 )
    }

    @Test
    fun weatherForecastApiCallFailed() {
        mockedResponse = MockResponseFileReader("WeatherApi/success_weather_forecast.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody(mockedResponse)
        )

        val response = runBlocking { service.getWeatherForecast(30.00,30.00) }

        assertEquals("the api request not successful",response.code() , 404 )
    }

    @Test
    fun currentWeatherApiCallSuccess() {
        mockedResponse = MockResponseFileReader("WeatherApi/success_current_weather.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { service.getWeatherForecast(30.00,30.00) }

        assertEquals("the api request not successful",response.code() , 200 )
    }

    @Test
    fun currentWeatherApiCallFailed() {
        mockedResponse = MockResponseFileReader("WeatherApi/success_current_weather.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { service.getWeatherForecast(30.00,30.00) }


        assertEquals("the api request not successful",response.code() , 200 )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
