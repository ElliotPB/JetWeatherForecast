package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun WeatherAppBar(
   title: String = "title",
   icon: ImageVector? = null,
   isMainScreen: Boolean = true,
   navController: NavController,
   onAddActionClicked: () -> Unit = {},
   onButtonClicked: () -> Unit = {},
) {
   TopAppBar(
      title = {
         Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
         )
      },
      actions = {
         if (isMainScreen){
            IconButton(onClick = { onAddActionClicked.invoke() }) {
               Icon(
                  imageVector = Icons.Default.Search,
                  contentDescription = "search icon"
               )
            }
            IconButton(onClick = { /*TODO*/ }) {
               Icon(
                  imageVector = Icons.Rounded.MoreVert,
                  contentDescription = "More Icon"
               )
            }
         }else Box {}

      },
      navigationIcon = {
         if (icon != null) {
            IconButton(onClick = { onButtonClicked.invoke() }) {
               Icon(
                  imageVector = icon,
                  contentDescription = "Navigation Icon",
               )
            }
         } else Box {}
      }
   )
}