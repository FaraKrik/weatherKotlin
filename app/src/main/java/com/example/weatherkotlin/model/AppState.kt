package com.example.weatherkotlin.model

import com.example.weatherkotlin.model.entites.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
