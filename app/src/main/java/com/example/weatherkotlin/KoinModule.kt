package com.example.weatherkotlin

import com.example.weatherkotlin.model.repository.Repository
import com.example.weatherkotlin.model.repository.RepositoryImpl
import com.example.weatherkotlin.ui.main.MainViewModel
import com.example.weatherkotlin.ui.main.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}