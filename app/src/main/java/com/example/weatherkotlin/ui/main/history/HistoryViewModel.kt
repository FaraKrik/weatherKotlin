package com.example.weatherkotlin.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherkotlin.model.AppState
import com.example.weatherkotlin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: Repository
) : ViewModel() {
    private val historyLiveDataLocal: MutableLiveData<AppState> = MutableLiveData()
    val historyLiveData: LiveData<AppState> get() = historyLiveDataLocal

    fun getAllHistory() {
        historyLiveDataLocal.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            historyLiveDataLocal.postValue(AppState.Success(repository.getAllHistory()))
        }
    }
}