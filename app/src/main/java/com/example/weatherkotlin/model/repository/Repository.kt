package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.entites.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}