package com.example.weatherkotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherkotlin.model.AppState
import com.example.weatherkotlin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {
    private val localLiveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState> get() = localLiveData

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(false)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        localLiveData.value = AppState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            localLiveData.postValue(
                if (isRussian) {
                    AppState.Success(repository.getWeatherFromLocalStorageRus())
                } else {
                    AppState.Success(repository.getWeatherFromLocalStorageWorld())
                }
            )
        }
    }
}
