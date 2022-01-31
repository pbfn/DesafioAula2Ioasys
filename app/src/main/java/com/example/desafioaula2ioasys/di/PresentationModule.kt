package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.presentation.viewmodel.LoginViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val presentationModule = module {
    viewModel { LoginViewModel(get()) }
    //viewModel { BookListViewModel(get()) }
}