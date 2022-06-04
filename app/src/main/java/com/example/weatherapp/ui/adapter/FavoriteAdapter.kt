package com.example.weatherapp.ui.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.databinding.FavoriteWeatherItemBinding
import com.example.weatherapp.databinding.ForecastWeatherItemBinding
import com.example.weatherapp.ui.utils.dayOfTheWeek


class FavoriteAdapter(private val weatherFavoriteList: List<CurrentWeather>, val listener : (Int)->(Unit) ):
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

        fun bind(item: CurrentWeather) {

            binding.apply {
                cityFavorite.text = item.cityName
                descriptionFavorite.text = item.weatherDescription[0].main
                temperatureFavorite.text = resource.getString(
                    R.string.temperature,
                    item.weatherProperties.temp.toInt().toString()
                )
                feelLikeFavorite.text = resource.getString(
                    R.string.feel_like,
                    item.weatherProperties.feels_like.toString()
                )


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