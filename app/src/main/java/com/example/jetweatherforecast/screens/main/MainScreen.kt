package com.example.jetweatherforecast.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bawp.jetweatherforecast.screens.main.MainViewModel
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
   val weatherData = produceState<DataOrException<Forecast, Boolean, Exception>>(
      initialValue = DataOrException(loading = true),
      producer = {
         value = mainViewModel.getWeatherData(latquery = 37.7749, lonquery = -122.4194)
      }
   ).value

   if(weatherData.loading == true){
      CircularProgressIndicator()
   } else if (weatherData.data != null) {
      MainScaffold(forecast = weatherData.data!!, navController = navController)
   } else {
      Text(text = "Error: ${weatherData.e?.message}")
   }
}

@Composable
fun MainScaffold(forecast: Forecast, navController: NavController) {
   Scaffold (
      topBar = {
         WeatherAppBar(
            title = "${forecast.city.name}, ${forecast.city.country}",
            navController = navController
         )
      }
   )
   { innerPadding ->
      MainContent(forecast = forecast, modifier = Modifier.padding(innerPadding))
   }
}

@Composable
fun MainContent(forecast: Forecast, modifier: Modifier = Modifier) {
   Column(
      modifier
         .fillMaxWidth()
         .padding(horizontal = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(
         text = "Forecast for ${forecast.list.first().dt_txt}",
         fontWeight = FontWeight.Medium,
         modifier = Modifier.padding(10.dp)
      )
      Surface(
         modifier = Modifier
            .padding(10.dp)
            .size(200.dp),
         shape = CircleShape,
         color = Color(0xFFFFD23B),
         shadowElevation = 4.dp
      ) {
         Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
         ) {
//            Icon()
            Text(
               text = "${forecast.list.first().main.temp.toInt()}°C",
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(10.dp)
            )
            Text(
               text = forecast.list.first().weather.first().main,
               fontSize = 20.sp,
               modifier = Modifier.padding(10.dp)
            )
         }
      }
   }
}