package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.entites.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorage() = Weather()

}