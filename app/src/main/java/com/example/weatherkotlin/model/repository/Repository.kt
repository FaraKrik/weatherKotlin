package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.entites.Weather

interface Repository {
    fun getWeatherFromServer(lat: Double, lon: Double): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}