package com.example.weatherapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.data.model.currentweather.CurrentWeather
import com.example.weatherapp.data.model.weatherforecast.WeatherForecast
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.adapter.WeatherForecastAdapter
import com.example.weatherapp.ui.utils.dayOfTheWeek
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WeatherForecastAdapter

    private var currentWeatherInfo :CurrentWeather? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.main_menu, menu)

        val searchViewItem = menu.findItem(R.id.app_bar_search)

        val searchView = searchViewItem.actionView as SearchView

        searchView.queryHint = getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { city ->
                    if (city.isNotEmpty())
                        viewModel.getCurrentWeatherAndForecast(query)
                        binding.checkBox.isChecked=false
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // i could use local manager to get current time but i run out of time
        viewModel.getCurrentWeatherAndForecast("Dubai")


        binding.apply {
            setCurrentWeather()
            setRecyclerView()

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                    viewModel.setFavorite(isChecked,currentWeatherInfo)
            }

        }
    }


    private fun FragmentMainBinding.setCurrentWeather() {
        viewModel.observeCurrentWeather.observe(viewLifecycleOwner) {

            feelLikeMain.text = getString(R.string.feel_like, it.weatherProperties.feels_like.toInt().toString())
            cityMain.text = it.cityName
            currentDayMain.text = dayOfTheWeek()
            temperatureMain.text = getString(R.string.temperature, it.weatherProperties.temp.toInt().toString())
            descriptionMain.text = it.weatherDescription[0].main
            // i could use ext function but i run out of time
            windMain.text = getString(R.string.wind, it.wind.speed.toString())
            humidityMain.text = getString(R.string.humidity, it.weatherProperties.humidity.toString())
            prussureMain.text = getString(R.string.pressure,it.weatherProperties.pressure.toString())
            currentWeatherInfo=it
        }

    }

    private fun FragmentMainBinding.setRecyclerView() {
        viewModel.observeWeatherForecast.observe(viewLifecycleOwner) {
            adapter = WeatherForecastAdapter(it)
            forecastRecyclerViewMain.adapter = adapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}