package com.example.weatherkotlin.model.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    var city: String,
    val lat: Double,
    val lon: Double
) : Parcelable

