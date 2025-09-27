package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.utils.formatDateTime

@Composable
fun FiveDayForecastList(forecast: Forecast) {
   Card {
      LazyColumn(
         contentPadding = PaddingValues(bottom = 12.dp),
         verticalArrangement = Arrangement.spacedBy(2.dp),
      ) {
         items(forecast.list) { item ->
            if (formatDateTime(item.dt) == "01:00:PM" && formatDate(item.dt) != formatDate(forecast.list.first().dt)) {
               Card(
                  modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                  colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                  shape = RoundedCornerShape(40.dp)
               ) {
                  Row(
                     modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                     horizontalArrangement = Arrangement.SpaceAround,
                     verticalAlignment = Alignment.CenterVertically
                  ) {
                     Text(
                        text = formatDate(item.dt).split(",")[0],
                        fontSize = 18.sp,
                     )
                     WeatherStateImage(
                        imageUrl = "https://openweathermap.org/img/wn/${item.weather.firstOrNull()?.icon}.png",
                        size = 60.dp
                     )
                     Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD23B)),
                        shape = RoundedCornerShape(10.dp)
                     ) {
                        Text(
                           text = item.weather.first().main,
                           fontSize = 16.sp,
                           modifier = Modifier.padding(horizontal = 6.dp)
                        )
                     }
                     Text(
                        text = "${item.main.temp_max.toInt()}°C",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                     )
                  }

               }
            }
         }
      }
   }
}

@Composable
fun SunsetSunriseRow(forecast: Forecast) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(10.dp),
      horizontalArrangement = Arrangement.SpaceEvenly,
   ) {
      Row (verticalAlignment = Alignment.CenterVertically){
         Icon(
            painter = painterResource(id = R.drawable.sunrise),
            contentDescription = "Sunrise Icon",
            modifier = Modifier.size(34.dp)
         )
         Text(
            text = formatDateTime(forecast.city.sunrise),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
         )
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
         Icon(
            painter = painterResource(id = R.drawable.sunset),
            contentDescription = "Sunset Icon",
            modifier = Modifier.size(34.dp)
         )
         Text(
            text = formatDateTime(forecast.city.sunset),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
         )
      }
   }
}

@Composable
fun HumidityWindPressureRow(forecast: Forecast) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
   ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
         Icon(
            painter = painterResource(id = R.drawable.rain),
            contentDescription = "Humidity Icon",
            modifier = Modifier.size(34.dp)
         )
         Text(
            text = "${forecast.list.first().main.humidity}%",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
         )
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
         Icon(
            painter = painterResource(id = R.drawable.wind),
            contentDescription = "Wind Icon",
            modifier = Modifier.size(34.dp)
         )
         Text(
            text = "${forecast.list.first().wind.speed} m/s",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
         )
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
         Icon(
            painter = painterResource(id = R.drawable.pressure),
            contentDescription = "Pressure Icon",
            modifier = Modifier.size(34.dp)
         )
         Text(
            text = "${forecast.list.first().main.pressure} hPa",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
         )
      }
   }
}

@Composable
fun WeatherStateImage(imageUrl: String, size: Dp) {
   Image(
      painter = rememberAsyncImagePainter(model = imageUrl),
      contentDescription = null,
      modifier = Modifier.size(size)
   )
}