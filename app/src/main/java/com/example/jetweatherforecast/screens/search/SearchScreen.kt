package com.example.jetweatherforecast.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.jetweatherforecast.navigation.WeatherScreens
import com.example.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun SearchScreen(navController: NavController/*, viewModel: ViewModel*/) {
   Scaffold(
      topBar = {
         WeatherAppBar(
            title = "Search",
            navController = navController,
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            isMainScreen = false,
         ) {
            navController.popBackStack()
         }
      }
   ) { innerPadding ->
      Column(Modifier.padding(innerPadding)) {
         CustomSearchBar(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) { city ->
            navController.navigate(WeatherScreens.MainScreen.name + "/$city")
         }

      }

   }
}

@Composable
fun CustomSearchBar(
   modifier: Modifier = Modifier,
   onSearch: (String) -> Unit = {}
) {
   val searchQueryState = rememberSaveable { mutableStateOf("") }
   val keyboardController = LocalSoftwareKeyboardController.current
   val valid = remember(searchQueryState.value) {
      searchQueryState.value.trim().isNotEmpty()
   }
   Column {
      CommonTextField(
         valueState = searchQueryState,
         placeholder = "Search for a city",
         onAction = KeyboardActions{
            if (!valid) return@KeyboardActions
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyboardController?.hide()
         },

      )
   }
}

@Composable
fun CommonTextField(
   valueState: MutableState<String>,
   placeholder: String,
   imeAction: ImeAction = ImeAction.Next,
   onAction: KeyboardActions = KeyboardActions.Default,
) {
   OutlinedTextField(
      value = valueState.value,
      onValueChange = { valueState.value = it },
      label = { Text(text = placeholder) },
      singleLine = true,
      keyboardActions = onAction,
      keyboardOptions = KeyboardOptions(
         imeAction = imeAction,
         keyboardType = KeyboardType.Text
      ),
      shape = RoundedCornerShape(15.dp),
      modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
   )
}