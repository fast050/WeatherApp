package com.example.weatherapp.ui.utils

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*


fun dayOfTheWeek(dayIndex: Int = 0,startDay:Int=0): String {
   // var dt = Date()
    val calendar = Calendar.getInstance()
    //c.time = dt
    calendar.add(Calendar.DATE, startDay+ dayIndex)
    val date = calendar.time
    val format: Format = SimpleDateFormat("EEEE")
    return format.format(date)
}

