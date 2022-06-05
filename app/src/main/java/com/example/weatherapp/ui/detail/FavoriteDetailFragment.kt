package com.example.weatherapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentFavoriteBinding
import com.example.weatherapp.databinding.FragmentFavoriteDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteDetailFragment : Fragment() {


    private var _binding :FragmentFavoriteDetailBinding? = null
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

        binding.setCurrentWeather()

    }

   private fun FragmentFavoriteDetailBinding.setCurrentWeather(){

       viewModel.currentWeatherDetail(args.currentWeatherId).observe(viewLifecycleOwner){

           cityDetail.text = it.cityName
           descriptionDetail.text=it.weatherDescription[0].main
           temperatureDetail.text = getString(R.string.temperature,it.weatherProperties.temp.toInt().toString())
           feelLikeDetail.text=getString(R.string.feel_like,it.weatherProperties.feels_like.toInt().toString())
           windDetail.text=getString(R.string.wind,it.wind.speed.toString())
           humidityDetail.text=getString(R.string.humidity,it.weatherProperties.humidity.toString())
           prussureDetail.text=getString(R.string.pressure,it.weatherProperties.pressure.toString())

       }

   }


}