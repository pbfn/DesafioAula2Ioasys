package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.data.repositories.BooksRepositoryImpl
import com.example.desafioaula2ioasys.data.repositories.LoginRepositoryImpl
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import org.koin.dsl.module

val dataModule = module {
    single<LoginRepository> {
        LoginRepositoryImpl(get(),get())
    }

    single<BooksRepository> {
        BooksRepositoryImpl(get(),get())
    }

}