package com.example.jetweatherforecast.model

data class Recording(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: MainDetails,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherOverview>,
    val wind: Wind
)