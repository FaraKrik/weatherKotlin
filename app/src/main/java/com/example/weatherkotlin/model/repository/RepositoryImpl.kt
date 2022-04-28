package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.WeatherLoader
import com.example.weatherkotlin.model.entites.Weather
import com.example.weatherkotlin.model.entites.getRussianCities
import com.example.weatherkotlin.model.entites.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        val dto = WeatherLoader.loadWeather(lat, lon)
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = getRussianCities()


    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

}