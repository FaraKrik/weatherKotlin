package com.example.weatherkotlin.model.entites.rest_entites

import com.google.gson.annotations.SerializedName

data class FactDTO(
    val temp: Int?,
    @SerializedName("feels_like") val feelsLike: Int?,
    val condition: String?
)