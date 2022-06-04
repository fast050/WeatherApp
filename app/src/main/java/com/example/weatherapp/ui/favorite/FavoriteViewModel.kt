package com.example.weatherapp.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {


}