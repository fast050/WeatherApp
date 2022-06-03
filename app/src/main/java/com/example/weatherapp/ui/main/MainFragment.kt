package com.example.weatherapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.adapter.WeatherForecastAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.*


@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding :FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var adapter:WeatherForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.main_menu, menu)

        val searchViewItem = menu.findItem(R.id.app_bar_search)

        val searchView = searchViewItem.actionView as SearchView

        searchView.queryHint="Search city..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty())
                        viewModel.getCurrentWeatherAndForecast(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
        )


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
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun FragmentMainBinding.setCurrentWeather()
    {
        viewModel.observeCurrentWeather.observe(viewLifecycleOwner){
            FeelLikeMain.text = it.weatherProperties.feels_like.toString()
            val day = LocalDate.now().dayOfWeek.name
            currentDayMain.text=day
            temperatureMain.text= it.weatherProperties.temp.toString()
            descriptionMain.text=it.weatherDescription[0].main

            // i could use ext function but i run out of time
            windMain.text=it.wind.speed.toString()+"km/h"
            humidityMain.text=it.weatherProperties.humidity.toString()+"%"
            prussureMain.text=it.weatherProperties.pressure.toString()+""
        }

    }

    fun FragmentMainBinding.setRecyclerView()
    {
        viewModel.observeWeatherForecast.observe(viewLifecycleOwner){
            adapter= WeatherForecastAdapter(it)
            forecastRecyclerViewMain.adapter=adapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}