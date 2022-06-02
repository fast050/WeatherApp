package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.weather.Weather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {


    @GET("weather")                       //lat={lat}&lon={lon}&appid={API key}
    suspend fun getCurrentWeather(
        @Query("q") city: String? = "dubai",
        @Query("appid") apiKey:String=WeatherApi.ApiKey
    ): Response<Weather>

    @GET("onecall")                       //lat=33.44&lon=-94.04&appid=638873d67ed5e6b048959ade3c44ce47&exclude=hourly,minutely
    suspend fun getWeatherForecast(
        @Query("lat") lat:Double,
        @Query("lon") log: Double,
        @Query("appid") apiKey:String=WeatherApi.ApiKey,
        @Query("exclude") exclude:String="hourly,minutely"
    ) : Response<WeatherForecast>



}

object WeatherApi {
    const val BaseURL = "https://api.openweathermap.org/data/2.5/"
    const val ApiKey = "638873d67ed5e6b048959ade3c44ce47"
}