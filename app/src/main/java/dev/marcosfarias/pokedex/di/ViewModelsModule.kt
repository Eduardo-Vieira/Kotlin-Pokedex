package dev.marcosfarias.pokedex.di

import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.pokedex.ui.generation.GenerationViewModel
import dev.marcosfarias.pokedex.ui.home.HomeViewModel
import dev.marcosfarias.pokedex.ui.pokedex.PokedexViewModel
import dev.marcosfarias.pokedex.ui.pokedex.repository.PokedexRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    single { PokedexRepository(get(), get()) }

    viewModel { DashboardViewModel(get()) }
    viewModel { GenerationViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PokedexViewModel(get()) }

}
