package com.example.jetweatherforecast.model

data class WeatherOverview(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)