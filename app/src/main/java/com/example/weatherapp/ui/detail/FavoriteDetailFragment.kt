package com.example.weatherapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentFavoriteBinding
import com.example.weatherapp.databinding.FragmentFavoriteDetailBinding
import com.example.weatherapp.ui.setting.TemperatureUnits
import com.example.weatherapp.ui.utils.temperatureUnits
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteDetailFragment : Fragment() {


    private var _binding: FragmentFavoriteDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteDetailViewModel by viewModels()
    private val args by navArgs<FavoriteDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val units = sharedPreferences.getString(
            getString(R.string.temperature_unit_key),
            TemperatureUnits.Celsius.unit
        ) ?: TemperatureUnits.Celsius.unit

        binding.setCurrentWeather(units)

    }

    private fun FragmentFavoriteDetailBinding.setCurrentWeather(units: String) {

        viewModel.currentWeatherDetail(args.currentWeatherId).observe(viewLifecycleOwner) {

            cityDetail.text = it.cityName
            descriptionDetail.text = it.description
            temperatureDetail.text = getString(
                R.string.temperature,
                temperatureUnits(it.temperature, units).toString()
            )
            feelLikeDetail.text =
                getString(R.string.feel_like, temperatureUnits(it.feelLike, units).toString())
            windDetail.text = getString(R.string.wind, it.windSpeed.toString())
            humidityDetail.text = getString(R.string.humidity, it.humidity.toString())
            prussureDetail.text = getString(R.string.pressure, it.pressure.toString())

        }

    }


}