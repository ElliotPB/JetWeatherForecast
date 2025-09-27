package com.example.jetweatherforecast.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import com.example.jetweatherforecast.data.City
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.data.getCityList
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.navigation.WeatherScreens
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.widgets.FiveDayForecastList
import com.example.jetweatherforecast.widgets.HumidityWindPressureRow
import com.example.jetweatherforecast.widgets.SunsetSunriseRow
import com.example.jetweatherforecast.widgets.WeatherAppBar
import com.example.jetweatherforecast.widgets.WeatherStateImage

@Composable
fun MainScreen(
   navController: NavController,
   mainViewModel: MainViewModel = hiltViewModel(),
   city: String?
) {
   var currCity = city ?: "London" // Default city if null
   val cityList = getCityList()
   var weatherData: DataOrException<Forecast, Boolean, Exception> =
      DataOrException(loading = true)

   for (item in cityList) {
      if (item.name.uppercase() == currCity.uppercase()) {
         weatherData = produceState<DataOrException<Forecast, Boolean, Exception>>(
            initialValue = DataOrException(loading = true),
            producer = {
               value = mainViewModel.getWeatherData(latquery = item.lat, lonquery = item.lon)
            }
         ).value
         break
      }
   }

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
            navController = navController,
            onAddActionClicked = {
               navController.navigate(WeatherScreens.SearchScreen.name)
            }
         )
      }
   )
   { innerPadding ->
      MainContent(forecast = forecast, modifier = Modifier.padding(innerPadding))
   }
}

@Composable
fun MainContent(forecast: Forecast, modifier: Modifier = Modifier) {
   val imageUrl
      = "https://openweathermap.org/img/wn/${forecast.list.firstOrNull()?.weather?.firstOrNull()?.icon}.png"
   
   Column(
      modifier
         .fillMaxWidth()
         .padding(horizontal = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(
         text = "Forecast for ${formatDate(forecast.list.first().dt)}",
         fontWeight = FontWeight.Medium,
         modifier = Modifier.padding(10.dp)
      )
      Surface(
         modifier = Modifier
            .padding(10.dp)
            .size(180.dp),
         shape = CircleShape,
         color = Color(0xFFFFD23B),
         shadowElevation = 4.dp
      ) {
         Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
         ) {
            WeatherStateImage(imageUrl, size = 80.dp)
            Text(
               text = "${forecast.list.first().main.temp.toInt()}°C",
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold,
            )
            Text(
               text = forecast.list.first().weather.first().main,
               fontSize = 19.sp,
            )
         }
      }
      HumidityWindPressureRow(forecast = forecast)
      HorizontalDivider()
      SunsetSunriseRow(forecast = forecast)
      FiveDayForecastList(forecast = forecast)
   }
}
