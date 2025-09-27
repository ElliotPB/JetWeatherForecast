package com.example.jetweatherforecast.data

data class City(
   val name: String,
   val lat: Double,
   val lon: Double,
)

fun getCityList(): List<City> {
   return listOf(
      City("London", 51.5074, -0.1278),
      City("New York", 40.7128, -74.0060),
      City("Tokyo", 35.6762, 139.6503),
      City("Sydney", -33.8688, 151.2093),
      City("Paris", 48.8566, 2.3522),
      City("Berlin", 52.5200, 13.4050),
      City("Moscow", 55.7558, 37.6173),
      City("Dubai", 25.276987, 55.296249),
      City("Cairo", 30.0444, 31.2357),
      City("Rio de Janeiro", -22.9068, -43.1729),
      City("Los Angeles", 34.0522, -118.2437),
      City("Toronto", 43.651070, -79.347015),
      City("Mumbai", 19.0760, 72.8777),
   )
}
