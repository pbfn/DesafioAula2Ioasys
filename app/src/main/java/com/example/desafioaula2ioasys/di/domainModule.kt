package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.domain.usecase.GetBookListUseCase
import com.example.desafioaula2ioasys.domain.usecase.LoginUseCase
import com.example.desafioaula2ioasys.domain.usecase.SearchBookListUseCase
import com.example.desafioaula2ioasys.domain.usecase.utils.SaveBooksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    single {
        CoroutineScope(Dispatchers.IO)
    }
    factory { LoginUseCase(get(), get()) }
    factory { GetBookListUseCase(get(), get()) }
    factory { SaveBooksUseCase(get(), get()) }
    factory { SearchBookListUseCase(get(), get()) }
}