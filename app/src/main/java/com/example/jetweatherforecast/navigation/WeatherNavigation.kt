package com.example.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bawp.jetweatherforecast.screens.main.MainViewModel
import com.example.jetweatherforecast.screens.about.AboutScreen
import com.example.jetweatherforecast.screens.main.MainScreen
import com.example.jetweatherforecast.screens.search.SearchScreen
import com.example.jetweatherforecast.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
   val navController = rememberNavController()
   NavHost(
      navController = navController,
      startDestination = WeatherScreens.SplashScreen.name
   ) {
      composable(WeatherScreens.SplashScreen.name) {
         SplashScreen(navController)
      }
      val route = WeatherScreens.MainScreen.name
      composable(
         route = "$route/{city}",
         arguments = listOf(navArgument(name = "city"){type = NavType.StringType})
      ) { navback ->
         navback.arguments?.getString("city").let{ city ->
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel = viewModel, city = city)
         }
      }
      composable(WeatherScreens.SearchScreen.name) {
         SearchScreen(navController = navController)
      }
      composable(WeatherScreens.AboutScreen.name){
         AboutScreen(navController = navController)
      }
   }
}