package com.example.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.databinding.ForecastWeatherItemBinding
import com.example.weatherapp.ui.utils.dayOfTheWeek


class WeatherForecastAdapter(private val forecastList: List<Daily>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ForecastWeatherItemBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            holder.bind(forecastList[position])
        }
    }


    inner class CourseViewHolder(private val binding: ForecastWeatherItemBinding,private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        val resource = context.resources

        fun bind(item: Daily) {

            binding.apply {
                descriptionForecast.text = item.weather[0].main
                tempForecast.text = resource.getString(R.string.temperature , item.temp.day.toInt().toString())
                dayForecast.text = dayOfTheWeek(adapterPosition,1)
            }
        }
    }


    override fun getItemCount(): Int {
        return forecastList.size
    }


}