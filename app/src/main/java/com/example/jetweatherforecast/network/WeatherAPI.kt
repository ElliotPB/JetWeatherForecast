package com.example.jetweatherforecast.network

import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
   @GET(value = "forecast")
   suspend fun getWeatherForecast(
      @Query("lat") lat: Double,
      @Query("lon") lon: Double,
      @Query("appid") appId: String = Constants.API_KEY,
      @Query("units") units: String = "metric",
      @Query("lang") lang: String = "en"
   ): Forecast
}