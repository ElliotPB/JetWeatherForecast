package com.example.jetweatherforecast.repository

import android.util.Log
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {

   suspend fun getWeather(latQuery: Double, lonquery: Double)
         :DataOrException<Forecast, Boolean, Exception>  {
      val response = try {
         api.getWeatherForecast(lat = latQuery, lon = lonquery)

      }catch (e: Exception){
         Log.d("REX", "getWeather: $e")
         return DataOrException(e = e)
      }
      Log.d("INSIDE", "getWeather: $response")
      return  DataOrException(data = response)

   }

}