package com.example.weatherapp.data.local.database

import androidx.room.TypeConverter
import com.example.weatherapp.data.model.currentweather.Coord
import com.example.weatherapp.data.model.currentweather.Main
import com.example.weatherapp.data.model.currentweather.WeatherX
import com.example.weatherapp.data.model.currentweather.Wind
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {


    @TypeConverter
    fun fromStringToCoord(coordString: String): Coord {
        val type = object : TypeToken<Coord>() {}.type
        return Gson().fromJson(coordString, type)
    }

    @TypeConverter
    fun fromCoordtoString(coord: Coord): String {
        val type = object : TypeToken<Coord>() {}.type
        return Gson().toJson(coord, type)
    }



    @TypeConverter
    fun fromStringToMain(mainString:String):Main
    {
        val type = object :TypeToken<Main>(){}.type
        return Gson().fromJson(mainString,type)
    }

    @TypeConverter
    fun fromMainToString(main:Main):String
    {
        val type = object :TypeToken<Main>(){}.type
        return Gson().toJson(main,type)
    }



    @TypeConverter
    fun fromStringToWind(windString:String): Wind
    {
        val type = object :TypeToken<Wind>(){}.type
        return Gson().fromJson(windString,type)
    }

    @TypeConverter
    fun fromWindToString(wind:Wind):String
    {
        val type = object :TypeToken<Wind>(){}.type
        return Gson().toJson(wind,type)
    }



    @TypeConverter // note this annotation
    fun fromWeatherXListToString(weatherXList: List<WeatherX>): String {

        val type = object : TypeToken<List<WeatherX>>() {}.type
        return Gson().toJson(weatherXList, type)
    }

    @TypeConverter // note this annotation
    fun fromStringToWeatherXList(weatherXListString:String): List<WeatherX> {


        val type = object : TypeToken<List<WeatherX>>() {}.type
        return Gson().fromJson(weatherXListString, type)
    }



    @TypeConverter
    fun fromStringToDailyList(dailyListString:String): List<Daily>
    {
        val type = object :TypeToken<List<Daily>>() {}.type
        return Gson().fromJson(dailyListString,type)
    }


    @TypeConverter
    fun fromDailyListToString(dailyList:List<Daily>): String
    {
        val type = object :TypeToken<List<Daily>>() {}.type
        return Gson().toJson(dailyList,type)
    }

//    @TypeConverter
//    fun fromStringToFeelsLike(feelsLikeString:String): FeelsLike
//    {
//        val type = object :TypeToken<FeelsLike>() {}.type
//        return Gson().fromJson(feelsLikeString,type)
//    }
//
//
//    @TypeConverter
//    fun fromFeelsLikeToString(feelsLike: FeelsLike): String
//    {
//        val type = object :TypeToken<FeelsLike>() {}.type
//        return Gson().toJson(feelsLike,type)
//    }


}