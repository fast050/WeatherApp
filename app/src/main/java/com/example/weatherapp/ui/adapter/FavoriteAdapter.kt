package com.example.weatherapp.ui.adapter

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.favorite.FavoriteWeather
import com.example.weatherapp.databinding.FavoriteWeatherItemBinding
import com.example.weatherapp.databinding.ForecastWeatherItemBinding
import com.example.weatherapp.ui.setting.TemperatureUnits
import com.example.weatherapp.ui.utils.dayOfTheWeek
import com.example.weatherapp.ui.utils.temperatureUnits


class FavoriteAdapter(
    private val weatherFavoriteList: List<FavoriteWeather>,
    val listener: (Int) -> (Unit)
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoriteWeatherItemBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            holder.bind(weatherFavoriteList[position])
        }
    }


    inner class CourseViewHolder(
        private val binding: FavoriteWeatherItemBinding,
        context: Context
    ) : RecyclerView.ViewHolder(binding.root) {


        private val resource = context.resources
        private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        private val units = sharedPreferences.getString(
            resource.getString(R.string.temperature_unit_key),
            TemperatureUnits.Celsius.unit
        ) ?: TemperatureUnits.Celsius.unit

        fun bind(item: FavoriteWeather) {

            binding.apply {
                cityFavorite.text = item.cityName
                descriptionFavorite.text = item.description
                temperatureFavorite.text = resource.getString(
                    R.string.temperature,
                    temperatureUnits(item.temperature, units).toString()
                )
                feelLikeFavorite.text = resource.getString(R.string.feel_like, temperatureUnits(item.feelLike, units).toString())


                binding.root.setOnClickListener {
                    listener(item.id)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return weatherFavoriteList.size
    }


}