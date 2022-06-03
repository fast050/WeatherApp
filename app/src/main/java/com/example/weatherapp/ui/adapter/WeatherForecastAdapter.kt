package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.databinding.ForecastWeatherItemBinding


class WeatherForecastAdapter(val forecastList : List<Daily>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =ForecastWeatherItemBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            holder.bind(forecastList[position])
        }
    }


    inner class CourseViewHolder(private val binding: ForecastWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Daily) {

            binding.apply {
               binding.descriptionForecast.text =item.weather[0].main
               binding.tempForecast.text=item.temp.toString()
            }
        }
    }


    override fun getItemCount(): Int {
       return forecastList.size
    }


}