package com.example.weatherapp.ui.utils

import com.example.weatherapp.ui.setting.TemperatureUnits
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*


fun dayOfTheWeek(dayIndex: Int = 0, startDay: Int = 0): String {
    // var dt = Date()
    val calendar = Calendar.getInstance()
    //c.time = dt
    calendar.add(Calendar.DATE, startDay + dayIndex)
    val date = calendar.time
    val format: Format = SimpleDateFormat("EEEE")
    return format.format(date)
}

fun temperatureUnits(temperature: Double, units: String): Int {
    return when (units) {
        TemperatureUnits.Celsius.unit -> {
            temperature.toInt()
        }
        TemperatureUnits.Fahrenheit.unit -> {
            val fahrenheit =  (temperature * 9.0/5.0) + 32.0
            fahrenheit.toInt()
        }
        else -> 0

    }
}