package com.bawp.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

   suspend fun getWeatherData(latquery: Double, lonquery: Double)
   : DataOrException<Forecast, Boolean, Exception> {
      return repository.getWeather(latQuery = latquery, lonquery = lonquery)
   }


}