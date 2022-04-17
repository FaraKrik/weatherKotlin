package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.entites.Weather
import com.example.weatherkotlin.model.entites.getRussianCities
import com.example.weatherkotlin.model.entites.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()


    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

}