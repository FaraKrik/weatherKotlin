package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.entites.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}