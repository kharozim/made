package com.ozimos.made.di

import com.ozimos.core.domain.usecase.MovieInteractor
import com.ozimos.core.domain.usecase.MovieUseCase
import com.ozimos.made.detail.DetailViewModel
import com.ozimos.made.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    single<MovieUseCase> { MovieInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}