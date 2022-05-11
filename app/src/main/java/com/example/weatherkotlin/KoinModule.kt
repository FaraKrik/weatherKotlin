package com.example.weatherkotlin

import androidx.room.Room
import com.example.weatherkotlin.model.database.Database
import com.example.weatherkotlin.model.repository.Repository
import com.example.weatherkotlin.model.repository.RepositoryImpl
import com.example.weatherkotlin.ui.main.MainViewModel
import com.example.weatherkotlin.ui.main.details.DetailsViewModel
import com.example.weatherkotlin.ui.main.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            "add_database.db"
        ).build()
    }
    single<Repository> { RepositoryImpl(get()) }

    //View models
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}