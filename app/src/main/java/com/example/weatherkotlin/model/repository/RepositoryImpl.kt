package com.example.weatherkotlin.model.repository

import com.example.weatherkotlin.model.database.Database
import com.example.weatherkotlin.model.database.HistoryEntity
import com.example.weatherkotlin.model.entites.City
import com.example.weatherkotlin.model.entites.Weather
import com.example.weatherkotlin.model.entites.getRussianCities
import com.example.weatherkotlin.model.entites.getWorldCities
import com.example.weatherkotlin.model.rest.WeatherRepo

class RepositoryImpl(private val db: Database) : Repository {
    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        //val dto = WeatherLoader.loadWeather(lat, lon)
        /*val dto = WeatherRepo.api.getWeather(lat, lon).enqueue(object : Callback<WeatherDTO> {
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                if (response.code() == 401) {
                    //..
                }
                if (response.isSuccessful) {
                    val data = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })*/
        val dto = WeatherRepo.api.getWeather(lat, lon).execute().body()
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

    override fun saveEntity(weather: Weather) {
        db.historyDao().insert(convertWeatherToEntity(weather))
    }

    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(db.historyDao().all())
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }
    }


    private fun convertWeatherToEntity(weather: Weather): HistoryEntity {
        return HistoryEntity(
            0, weather.city.city,
            weather.temperature,
            weather.condition ?: ""
        )
    }
}