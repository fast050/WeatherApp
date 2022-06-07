package com.example.weatherapp.ui.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.weatherforecast.Daily
import com.example.weatherapp.databinding.ForecastWeatherItemBinding
import com.example.weatherapp.ui.setting.TemperatureUnits
import com.example.weatherapp.ui.utils.dayOfTheWeek
import com.example.weatherapp.ui.utils.temperatureUnits


class WeatherForecastAdapter(private val forecastList: List<Daily>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ForecastWeatherItemBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            holder.bind(forecastList[position])
        }
    }


    inner class CourseViewHolder(
        private val binding: ForecastWeatherItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        val resource = context.resources

        private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val units = sharedPreferences.getString(
            resource.getString(R.string.temperature_unit_key),
            TemperatureUnits.Celsius.unit
        ) ?:TemperatureUnits.Celsius.unit

        fun bind(item: Daily) {

            binding.apply {
                descriptionForecast.text = item.weather[0].main
                tempForecast.text =
                    resource.getString(R.string.temperature, temperatureUnits(item.temp.day,units).toString())
                dayForecast.text = dayOfTheWeek(adapterPosition, 1)
            }
        }
    }


    override fun getItemCount(): Int {
        return forecastList.size
    }


}