package com.example.jetweatherforecast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
   Scaffold(
      topBar = {
         WeatherAppBar(
            title = "About",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            false,
            navController = navController
         ) {
            navController.popBackStack()
         }
      }
   ) { innerPadding ->

         Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
         ) {
            Text(
               text = stringResource(id = R.string.about_app),
               fontWeight = FontWeight.Bold
            )

            Text(
               text = stringResource(id = R.string.api_used),
               fontWeight = FontWeight.Light
            )

         }



   }

}